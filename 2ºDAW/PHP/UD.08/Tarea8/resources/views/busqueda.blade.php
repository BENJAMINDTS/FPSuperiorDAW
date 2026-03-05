<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Buscador de Libros - BenjaminDTS</title>

  <link rel="stylesheet" href="{{ asset('css/estilos.css') }}">
</head>

<body>

  <div class="container">
    <h1>Buscador de Libros</h1>

    <div class="form-group">
      <label for="texto">Buscar por título (AJAX + jQuery):</label>

      <input type="text" id="texto" name="texto" placeholder="Escribe para buscar..." autocomplete="off">

      <div id="error-msg" class="error">Por favor, introduce solo caracteres alfabéticos.</div>
    </div>

    <div id="resultados" class="results"></div>
  </div>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

  <script src="{{ asset('js/buscador.js') }}"></script>

</body>

</html>