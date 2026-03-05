<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

use App\Http\Controllers\LibroSearchController;

Route::get('/buscador', [LibroSearchController::class, 'index']);
Route::get('/buscador/search', [LibroSearchController::class, 'search']);
