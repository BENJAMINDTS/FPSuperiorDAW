<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ProductoController;
use App\Http\Controllers\LibroController;

/**
 * @author BenjaminDTS
 * Definición de rutas para los ejercicios 1 al 14.
 * Se centralizan las respuestas para simular una API REST.
 */
// Redirige la ruta principal a la vista de bienvenida
Route::get('/', function () {
    return redirect('/bienvenida'); // O '/peliculas' si estás en el proyecto 2
});
// Ejercicios 1, 2 y 3
Route::get('/inicio', function () {
    return response()->json(['success' => true, 'data' => null, 'message' => 'Bienvenido a mi primera aplicación en Laravel']);
});

Route::get('/saludo/{nombre}', function (string $nombre) {
    return response()->json(['success' => true, 'data' => compact('nombre'), 'message' => "Hola {$nombre}, bienvenido a Laravel"]);
});

Route::get('/suma/{num1}/{num2}', function (int $num1, int $num2) {
    return response()->json(['success' => true, 'data' => $num1 + $num2, 'message' => 'Suma completada']);
});

// Ejercicios 4 y 5
Route::get('/productos', [ProductoController::class, 'index']);
Route::get('/producto/{id}', [ProductoController::class, 'mostrar']);

// Ejercicios 6, 7 y 8
Route::get('/bienvenida', function () {
    return view('bienvenida');
});
Route::get('/saludo-vista', function () {
    return view('saludo', ['nombre' => 'Carlos']);
});
Route::get('/frutas', function () {
    return view('frutas', ['frutas' => ["manzana", "pera", "plátano", "naranja"]]);
});

// Ejercicios 11 al 14 (CRUD Libros)
Route::post('/libros', [LibroController::class, 'store']);
Route::get('/libros', [LibroController::class, 'index']);
Route::put('/libros/{id}', [LibroController::class, 'update']);
Route::delete('/libros/{id}', [LibroController::class, 'destroy']);

// Ruta para renderizar la interfaz de pruebas del CRUD de libros
Route::get('/panel-libros', function () {
    return view('panel-libros');
});
