{{-- @author BenjaminDTS --}}
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <title>Ejercicios Básicos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">
    {{-- Barra de navegación superior --}}
    <nav class="navbar navbar-dark bg-dark shadow-sm mb-4">
        <div class="container">
            <a class="navbar-brand fw-bold" href="/bienvenida">🏠 Panel de Ejercicios</a>
        </div>
    </nav>

    <div class="container pb-5">
        @yield('content')
    </div>
</body>

</html>
