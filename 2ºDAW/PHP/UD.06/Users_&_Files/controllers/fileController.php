<?php
require_once 'models/fileModel.php';

class FileController
{
  private function conectar()
  {
    require 'config/database.php';
    return new fileModel($connection);
  }

  public function subirArchivo()
  {
    $mensaje = "";

    if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_FILES['archivo'])) {
      $usuario_id = $_SESSION['usuario']['id'] ?? null;

      if (!$usuario_id) {
        header("Location: index.php?accion=login");
        exit();
      }

      $nombre_original = $_FILES['archivo']['name'];
      $ruta_temporal  = $_FILES['archivo']['tmp_name'];
      $error_archivo   = $_FILES['archivo']['error'];

      if ($error_archivo === UPLOAD_ERR_OK) {
        $directorio_subida = 'uploads/';
        if (!is_dir($directorio_subida)) {
          mkdir($directorio_subida, 0777, true);
        }

        // Generar nombre único para el archivo físico (evita sobreescritura)
        $nombre_fisico = time() . "_" . basename($nombre_original);
        $ruta_destino  = $directorio_subida . $nombre_fisico;

        if (move_uploaded_file($ruta_temporal, $ruta_destino)) {
          $model = $this->conectar();
          // Guardamos el nombre original en BD para la vista del usuario
          if ($model->agregarArchivo($nombre_original, $ruta_destino, $usuario_id)) {
            $mensaje = "Archivo subido y registrado en BD correctamente.";
          } else {
            unlink($ruta_destino); // Borrar archivo si falla la BD
            $mensaje = "Error al registrar en la base de datos.";
          }
        } else {
          $mensaje = "Error al mover el archivo al servidor.";
        }
      } else {
        $mensaje = "Error en la subida: " . $this->obtenerMensajeErrorSubida($error_archivo);
      }
    }
    require_once 'views/files/upload.php';
  }

  public function verArchivos()
  {
    $usuario_id = $_SESSION['usuario']['id'] ?? null;
    if (!$usuario_id) {
      header("Location: index.php?accion=login");
      exit();
    }

    $model = $this->conectar();
    $lista_archivos = $model->obtenerArchivosPorUsuario($usuario_id);

    require_once 'views/files/list.php';
  }

  public function eliminarArchivo()
  {
    $archivo_id = $_GET['id'] ?? null;
    $usuario_id = $_SESSION['usuario']['id'] ?? null;

    if ($archivo_id && $usuario_id) {
      $model = $this->conectar();
      $archivo = $model->obtenerArchivoPorId($archivo_id);

      // Verificación de seguridad: solo el dueño puede borrar su archivo
      if ($archivo && $archivo['usuario_id'] == $usuario_id) {
        if (file_exists($archivo['ruta'])) {
          unlink($archivo['ruta']); // Borrar archivo físico
        }
        $model->eliminarArchivo($archivo_id); // Borrar registro BD
      }
    }
    header("Location: index.php?accion=verArchivos");
    exit();
  }

  private function obtenerMensajeErrorSubida($codigoError)
  {
    switch ($codigoError) {
      case UPLOAD_ERR_INI_SIZE:
        return "Excede el tamaño permitido por PHP.";
      case UPLOAD_ERR_FORM_SIZE:
        return "Excede el tamaño permitido por el formulario.";
      case UPLOAD_ERR_PARTIAL:
        return "Subida parcial.";
      case UPLOAD_ERR_NO_FILE:
        return "No se seleccionó archivo.";
      case UPLOAD_ERR_NO_TMP_DIR:
        return "No hay carpeta temporal.";
      case UPLOAD_ERR_CANT_WRITE:
        return "Error al escribir en disco.";
      default:
        return "Error desconocido.";
    }
  }
}
