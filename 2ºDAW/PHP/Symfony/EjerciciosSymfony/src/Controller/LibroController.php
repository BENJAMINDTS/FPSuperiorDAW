<?php

namespace App\Controller;

use App\Entity\Libro;
use App\Repository\LibroRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

/**
 * LibroController
 *
 * Controlador que gestiona el CRUD completo de la entidad Libro
 * usando Doctrine ORM como capa de persistencia.
 * Cubre los ejercicios 10, 11, 12, 13 y 14 del módulo de Symfony.
 *
 * @author     Benjamín
 * @package    App\Controller
 */
class LibroController extends AbstractController
{
    /**
     * Ejercicio 10 — Insertar un libro en la base de datos.
     *
     * Crea una instancia de la entidad Libro con datos de ejemplo,
     * la persiste con Doctrine y confirma la transacción con flush().
     * Ruta: GET /libro/insertar
     *
     * @param EntityManagerInterface $em Gestor de entidades de Doctrine (inyección automática)
     * @return Response Renderiza libro_mensaje.html.twig con confirmación de inserción
     */
    #[Route('/libro/insertar', name: 'libro_insertar')]
    public function insertar(EntityManagerInterface $em): Response
    {
        // Se crea una nueva instancia de la entidad Libro
        $libro = new Libro();
        $libro->setTitulo('Don Quijote');
        $libro->setAutor('Cervantes');
        $libro->setPrecio(18);

        // persist() marca la entidad para ser guardada en la BD
        $em->persist($libro);
        // flush() ejecuta el INSERT en la base de datos
        $em->flush();

        return $this->render('libro_mensaje.html.twig', [
            'tipo'    => 'exito',
            'titulo'  => '¡Libro insertado!',
            'mensaje' => 'Don Quijote de Cervantes ha sido añadido a la base de datos.',
            'icono'   => '📗',
        ]);
    }

    /**
     * Ejercicio 11 — Mostrar todos los libros.
     *
     * Recupera todos los registros de la tabla libro mediante el repositorio
     * y los pasa a la vista para mostrarlos en un listado.
     * Ruta: GET /libros
     *
     * @param LibroRepository $repo Repositorio de Libro (inyección automática)
     * @return Response Renderiza libros.html.twig con el array de entidades $libros
     */
    #[Route('/libros', name: 'libros_listar')]
    public function listar(LibroRepository $repo): Response
    {
        // findAll() devuelve un array con todos los registros de la tabla
        $libros = $repo->findAll();

        return $this->render('libros.html.twig', ['libros' => $libros]);
    }

    /**
     * Ejercicio 12 — Mostrar un libro por su ID.
     *
     * Busca un libro en la base de datos por su clave primaria.
     * Si no existe, devuelve una vista de error con código 404.
     * Ruta: GET /libro/{id}  (solo acepta valores numéricos)
     * Ejemplo: /libro/1
     *
     * @param int             $id   ID del libro recibido como parámetro de ruta
     * @param LibroRepository $repo Repositorio de Libro (inyección automática)
     * @return Response Renderiza libro_detalle.html.twig o libro_mensaje.html.twig (404)
     */
    #[Route('/libro/{id}', name: 'libro_mostrar', requirements: ['id' => '\d+'])]
    public function mostrar(int $id, LibroRepository $repo): Response
    {
        // find() busca por clave primaria; devuelve null si no existe
        $libro = $repo->find($id);

        // Si no se encuentra el libro se muestra un mensaje de error
        if (!$libro) {
            return $this->render('libro_mensaje.html.twig', [
                'tipo'    => 'error',
                'titulo'  => 'Libro no encontrado',
                'mensaje' => "No existe ningún libro con ID $id.",
                'icono'   => '🔍',
            ], new Response('', 404));
        }

        return $this->render('libro_detalle.html.twig', ['libro' => $libro]);
    }

    /**
     * Ejercicio 13 — Actualizar el precio de un libro.
     *
     * Recupera el libro por ID, modifica su precio a 22€ y persiste
     * el cambio. Doctrine detecta automáticamente la modificación
     * (Unit of Work) y ejecuta el UPDATE al llamar a flush().
     * Ruta: GET /libro/{id}/actualizar
     * Ejemplo: /libro/1/actualizar
     *
     * @param int                    $id   ID del libro a actualizar
     * @param LibroRepository        $repo Repositorio de Libro (inyección automática)
     * @param EntityManagerInterface $em   Gestor de entidades de Doctrine (inyección automática)
     * @return Response Renderiza libro_mensaje.html.twig con confirmación o error
     */
    #[Route('/libro/{id}/actualizar', name: 'libro_actualizar')]
    public function actualizar(int $id, LibroRepository $repo, EntityManagerInterface $em): Response
    {
        $libro = $repo->find($id);

        // Comprobación: si el libro no existe se informa del error
        if (!$libro) {
            return $this->render('libro_mensaje.html.twig', [
                'tipo'    => 'error',
                'titulo'  => 'Libro no encontrado',
                'mensaje' => "No existe ningún libro con ID $id.",
                'icono'   => '🔍',
            ]);
        }

        // Se guarda el precio anterior para mostrarlo en el mensaje de confirmación
        $precioAnterior = $libro->getPrecio();

        // Se actualiza el precio; Doctrine lo detecta como entidad "sucia" (dirty)
        $libro->setPrecio(22);

        // flush() ejecuta el UPDATE en la base de datos
        $em->flush();

        return $this->render('libro_mensaje.html.twig', [
            'tipo'    => 'actualizar',
            'titulo'  => '¡Precio actualizado!',
            'mensaje' => 'El precio de «' . $libro->getTitulo() . '» ha cambiado de ' . $precioAnterior . '€ a 22€.',
            'icono'   => '✏️',
        ]);
    }

    /**
     * Ejercicio 14 — Eliminar un libro.
     *
     * Recupera el libro por ID, lo marca para eliminación con remove()
     * y confirma la operación con flush(), lo que ejecuta el DELETE en la BD.
     * Ruta: GET /libro/{id}/eliminar
     * Ejemplo: /libro/1/eliminar
     *
     * @param int                    $id   ID del libro a eliminar
     * @param LibroRepository        $repo Repositorio de Libro (inyección automática)
     * @param EntityManagerInterface $em   Gestor de entidades de Doctrine (inyección automática)
     * @return Response Renderiza libro_mensaje.html.twig con confirmación o error
     */
    #[Route('/libro/{id}/eliminar', name: 'libro_eliminar')]
    public function eliminar(int $id, LibroRepository $repo, EntityManagerInterface $em): Response
    {
        $libro = $repo->find($id);

        // Comprobación: si el libro no existe se informa del error
        if (!$libro) {
            return $this->render('libro_mensaje.html.twig', [
                'tipo'    => 'error',
                'titulo'  => 'Libro no encontrado',
                'mensaje' => "No existe ningún libro con ID $id.",
                'icono'   => '🔍',
            ]);
        }

        // Se guarda el título antes de eliminar, ya que tras remove() la entidad
        // queda en estado "removed" y no se puede acceder a sus datos de forma segura
        $titulo = $libro->getTitulo();

        // remove() marca la entidad para eliminación
        $em->remove($libro);
        // flush() ejecuta el DELETE en la base de datos
        $em->flush();

        return $this->render('libro_mensaje.html.twig', [
            'tipo'    => 'eliminar',
            'titulo'  => '¡Libro eliminado!',
            'mensaje' => "«{$titulo}» ha sido eliminado de la base de datos.",
            'icono'   => '🗑️',
        ]);
    }
}
