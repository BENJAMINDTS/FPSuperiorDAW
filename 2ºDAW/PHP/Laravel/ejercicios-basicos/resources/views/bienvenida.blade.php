{{-- @author BenjaminDTS --}}
@extends('layouts.app')

@section('content')
    <h1 class="text-primary text-center mb-5">Índice de Ejercicios</h1>

    <div class="row g-4">
        {{-- Bloque: Rutas Básicas --}}
        <div class="col-md-4">
            <div class="card h-100 shadow-sm border-0">
                <div class="card-header bg-dark text-white fw-bold">Rutas Básicas</div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a href="/inicio" class="text-decoration-none">Ej 1: Inicio (JSON)</a></li>
                    <li class="list-group-item"><a href="/saludo/Benjamin" class="text-decoration-none">Ej 2: Saludo con
                            parámetro</a></li>
                    <li class="list-group-item"><a href="/suma/5/7" class="text-decoration-none">Ej 3: Sumar 5 + 7</a></li>
                </ul>
            </div>
        </div>

        {{-- Bloque: Controladores --}}
        <div class="col-md-4">
            <div class="card h-100 shadow-sm border-0">
                <div class="card-header bg-success text-white fw-bold">Controladores</div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item"><a href="/productos" class="text-decoration-none">Ej 4: Listado de
                            Productos</a></li>
                    <li class="list-group-item"><a href="/producto/99" class="text-decoration-none">Ej 5: Mostrar Producto
                            (ID: 99)</a></li>
                </ul>
            </div>
        </div>

        {{-- Bloque: Vistas Blade --}}
        <div class="col-md-4">
            <div class="card h-100 shadow-sm border-0">
                <div class="card-header bg-info text-dark fw-bold">Vistas Blade</div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item text-muted">Ej 6: Bienvenida (Estás aquí)</li>
                    <li class="list-group-item"><a href="/saludo-vista" class="text-decoration-none">Ej 7: Vista Saludo
                            (Variable)</a></li>
                    <li class="list-group-item"><a href="/frutas" class="text-decoration-none">Ej 8: Lista de Frutas
                            (Foreach)</a></li>
                </ul>
            </div>
        </div>

        {{-- Bloque: Base de Datos (Libros) ACTUALIZADO --}}
        <div class="col-md-12 mt-4">
            <div class="card shadow-sm border-primary">
                <div class="card-header bg-primary text-white fw-bold">Base de Datos: Libros (Ej 11-14)</div>
                <div class="card-body">
                    <p class="card-text text-muted">
                        Hemos creado un panel interactivo para probar fácilmente los métodos POST (Insertar), PUT
                        (Actualizar) y DELETE (Eliminar) sin necesidad de usar herramientas externas.
                    </p>
                    <div class="d-flex gap-2">
                        <a href="/libros" target="_blank" class="btn btn-outline-primary">Ver Lista JSON (GET)</a>
                        <a href="/panel-libros" class="btn btn-primary">Ir al Panel de Pruebas CRUD</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
@endsection
