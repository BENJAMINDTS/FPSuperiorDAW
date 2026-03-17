{{-- @author BenjaminDTS --}}
@extends('layouts.app')

@section('content')
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h2 class="text-primary mb-4 text-center">Panel de Pruebas CRUD (Libros)</h2>

            <div class="card shadow-sm mb-3">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="mb-0">Ejercicio 11: Insertar Datos</h5>
                        <small class="text-muted">Creará "El Quijote" por 20€</small>
                    </div>
                    {{-- Formulario POST: El @csrf es obligatorio por seguridad en Laravel --}}
                    <form action="/libros" method="POST">
                        @csrf
                        <button type="submit" class="btn btn-success">Insertar Libro (POST)</button>
                    </form>
                </div>
            </div>

            <div class="card shadow-sm mb-3">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="mb-0">Ejercicio 12: Mostrar Datos</h5>
                        <small class="text-muted">Ver todos los libros actuales</small>
                    </div>
                    <a href="/libros" target="_blank" class="btn btn-primary">Ver Libros (GET)</a>
                </div>
            </div>

            <div class="card shadow-sm mb-3">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="mb-0">Ejercicio 13: Actualizar Datos</h5>
                        <small class="text-muted">Cambiará el precio del libro ID 1 a 25€</small>
                    </div>
                    {{-- Formulario PUT: Usamos @method('PUT') para falsificar el verbo HTTP --}}
                    <form action="/libros/1" method="POST">
                        @csrf
                        @method('PUT')
                        <button type="submit" class="btn btn-warning">Actualizar Precio (PUT)</button>
                    </form>
                </div>
            </div>

            <div class="card shadow-sm">
                <div class="card-body d-flex justify-content-between align-items-center">
                    <div>
                        <h5 class="mb-0">Ejercicio 14: Eliminar Datos</h5>
                        <small class="text-muted">Borrará el libro con ID 1</small>
                    </div>
                    {{-- Formulario DELETE: Usamos @method('DELETE') --}}
                    <form action="/libros/1" method="POST">
                        @csrf
                        @method('DELETE')
                        <button type="submit" class="btn btn-danger">Eliminar Libro (DELETE)</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
@endsection
