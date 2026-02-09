<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Illuminate\Http\JsonResponse;
use App\Models\Libro;

/**
 * Class LibroSearchController
 *
 * Controlador encargado de gestionar la búsqueda de libros mediante AJAX.
 *
 * @author BenjaminDTS
 * @package App\Http\Controllers
 */
class LibroSearchController extends Controller
{
    /**
     * Muestra la página principal con el formulario de búsqueda.
     *
     * @author BenjaminDTS
     * @return \Illuminate\View\View Retorna la vista con el formulario.
     */
    public function index()
    {
        return view('busqueda');
    }

    /**
     * Busca libros en la base de datos basándose en una cadena de texto.
     *
     * Valida que la entrada contenga solo caracteres alfabéticos
     * y retorna una respuesta JSON.
     *
     * @param Request $request La petición HTTP entrante con el campo 'texto'.
     * @return JsonResponse Retorna un JSON con los libros encontrados.
     */
    public function search(Request $request): JsonResponse
    {
        // RA8_e: Validación estricta (solo letras)
        $request->validate([
            'texto' => 'required|string|regex:/^[a-zA-Z\s]+$/',
        ]);

        $query = $request->input('texto');

        // RA8_f: Búsqueda dinámica
        $libros = Libro::where('titulo', 'LIKE', "%{$query}%")
            ->get(['id', 'titulo', 'autor']);

        return response()->json($libros);
    }
}
