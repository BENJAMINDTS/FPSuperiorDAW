<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\JsonResponse;
use App\Models\Libro;

/**
 * Clase LibroSearchController
 *
 * Controlador de Laravel encargado de gestionar la búsqueda dinámica de libros 
 * mediante peticiones AJAX, devolviendo los resultados en formato JSON.
 *
 * @package App\Http\Controllers
 * @author BenjaminDTS
 */
class LibroSearchController extends Controller
{
    /**
     * Muestra la página principal con el formulario de búsqueda de libros.
     *
     * @return \Illuminate\View\View|\Illuminate\Contracts\View\Factory Retorna la instancia de la vista 'busqueda'.
     */
    public function index()
    {
        return view('busqueda');
    }

    /**
     * Busca libros en la base de datos basándose en una cadena de texto (título).
     *
     * Valida de forma estricta que la entrada contenga exclusivamente caracteres 
     * alfabéticos y espacios antes de realizar la consulta con Eloquent.
     *
     * @param Request $request El objeto de la petición HTTP que contiene el parámetro 'texto'.
     * @return JsonResponse Retorna una respuesta JSON con una colección de libros (id, titulo, autor).
     * @throws \Illuminate\Validation\ValidationException Si el campo 'texto' no cumple con las reglas de validación.
     */
    public function search(Request $request): JsonResponse
    {
        // RA8_e: Validación estricta (solo letras y espacios)
        $request->validate([
            'texto' => 'required|string|regex:/^[a-zA-Z\s]+$/',
        ]);

        $query = $request->input('texto');

        // RA8_f: Búsqueda dinámica por coincidencia parcial en el título
        $libros = Libro::where('titulo', 'LIKE', "%{$query}%")
            ->get(['id', 'titulo', 'autor']);

        return response()->json($libros);
    }
}
