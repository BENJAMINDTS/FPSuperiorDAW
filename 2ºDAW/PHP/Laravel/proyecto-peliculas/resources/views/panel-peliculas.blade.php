{{-- @author BenjaminDTS --}}
@extends('layouts.app')

@section('content')
    <div class="row">
        {{-- Columna Izquierda: Lectura --}}
        <div class="col-md-6">
            <h4 class="text-primary mb-3">Lectura (GET)</h4>

            <div class="card shadow-sm mb-3 border-0">
                <div class="card-body">
                    <h5>Listar Todas</h5>
                    <p class="text-muted small">Muestra todas las películas de la base de datos.</p>
                    <a href="/peliculas" target="_blank" class="btn btn-outline-primary w-100">Ver JSON (/peliculas)</a>
                </div>
            </div>

            <div class="card shadow-sm mb-3 border-0">
                <div class="card-body">
                    <h5>Buscar por ID (Ej: ID 1)</h5>
                    <p class="text-muted small">Busca una película específica.</p>
                    <a href="/peliculas/1" target="_blank" class="btn btn-outline-info w-100">Ver Película 1</a>
                </div>
            </div>
        </div>

        {{-- Columna Derecha: Escritura y Modificación --}}
        <div class="col-md-6">
            <h4 class="text-success mb-3">Escritura (POST, PUT, DELETE)</h4>

            {{-- Insertar --}}
            <div class="card shadow-sm mb-3 border-0">
                <div class="card-body border-start border-success border-4">
                    <h5>Insertar Película (POST)</h5>
                    <form action="/peliculas" method="POST" class="mt-3">
                        @csrf
                        <div class="row g-2 mb-2">
                            <div class="col-12"><input type="text" name="titulo" class="form-control form-control-sm"
                                    placeholder="Título" required></div>
                            <div class="col-8"><input type="text" name="director" class="form-control form-control-sm"
                                    placeholder="Director" required></div>
                            <div class="col-4"><input type="number" name="anio" class="form-control form-control-sm"
                                    placeholder="Año" required></div>
                        </div>
                        <button type="submit" class="btn btn-success btn-sm w-100">Guardar</button>
                    </form>
                </div>
            </div>

            {{-- Actualizar --}}
            <div class="card shadow-sm mb-3 border-0">
                <div class="card-body border-start border-warning border-4">
                    <h5>Actualizar Título (PUT) - ID: 1</h5>
                    <form action="/peliculas/1" method="POST" class="mt-3">
                        @csrf
                        @method('PUT')
                        <div class="input-group input-group-sm mb-2">
                            <input type="text" name="titulo" class="form-control" placeholder="Nuevo Título" required>
                            <button type="submit" class="btn btn-warning text-dark fw-bold">Actualizar</button>
                        </div>
                    </form>
                </div>
            </div>

            {{-- Eliminar --}}
            <div class="card shadow-sm mb-3 border-0">
                <div class="card-body border-start border-danger border-4">
                    <h5>Eliminar Película (DELETE) - ID: 1</h5>
                    <form action="/peliculas/1" method="POST" class="mt-2">
                        @csrf
                        @method('DELETE')
                        <button type="submit" class="btn btn-danger btn-sm w-100">Eliminar Definitivamente</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
@endsection
