<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\PeliculaController;

/** @author BenjaminDTS */

// 1. Mostrar el panel de control interactivo
Route::get('/panel', function () {
    return view('panel-peliculas');
});

// 2. Redirigir la raíz al panel
Route::get('/', function () {
    return redirect('/panel');
});

// 3. API REST de películas (Genera rutas index, store, show, update, destroy)
Route::apiResource('peliculas', PeliculaController::class);
