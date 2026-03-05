<!DOCTYPE html>
<html lang="es">

<head>
  <meta charset="UTF-8">
  <title>GET Básico</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
  <h1>Solicitar datos de un servidor</h1>
  <button id="btnCargar">Cargar Datos</button>
  <div id="resultado"></div>

  <script>
    $('#btnCargar').click(function() {
      $.ajax({
        url: 'https://jsonplaceholder.typicode.com/posts/1',
        method: 'GET',
        success: function(respuesta) {
          $('#resultado').html('<h3>' + respuesta.title + '</h3><p>' + respuesta.body + '</p>');
        }
      });
    });
  </script>
</body>

</html>