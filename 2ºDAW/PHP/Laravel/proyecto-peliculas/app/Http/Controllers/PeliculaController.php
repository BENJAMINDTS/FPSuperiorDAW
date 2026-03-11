<?php

namespace App\Http\Controllers;

use App\Models\Pelicula;
use Illuminate\Http\Request;
use Illuminate\Http\JsonResponse;
use Illuminate\Support\Facades\Log;
use Exception;

/**
 * Controlador para la gestión completa de Películas.
 * @author BenjaminDTS
 * Implementa un diseño limpio atrapando excepciones para evitar exponer
 * detalles internos del servidor y devolver respuestas JSON predecibles.
 */
class PeliculaController extends Controller
{
    /**
     * Recupera y devuelve el listado completo de películas.
     */
    public function index(): JsonResponse
    {
        try {
            // En un entorno de producción masivo se usaría Pelicula::paginate()
            $peliculas = Pelicula::all();
            return response()->json(['success' => true, 'data' => $peliculas, 'message' => 'Listado exitoso']);
        } catch (Exception $e) {
            Log::error("Error listar peliculas: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Error interno del servidor'], 500);
        }
    }

    /**
     * Valida y almacena una nueva película en la base de datos.
     */
    public function store(Request $request): JsonResponse
    {
        try {
            // La validación protege contra datos incompletos o maliciosos
            $data = $request->validate([
                'titulo'   => 'required|string',
                'director' => 'required|string',
                'anio'     => 'required|integer'
            ]);

            $pelicula = Pelicula::create($data);
            return response()->json(['success' => true, 'data' => $pelicula, 'message' => 'Película insertada'], 201);
        } catch (Exception $e) {
            Log::error("Error guardar pelicula: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Datos inválidos o error al guardar'], 400);
        }
    }

    /**
     * Busca y devuelve los detalles de una película específica.
     */
    public function show(int $id): JsonResponse
    {
        try {
            // findOrFail lanza una excepción automáticamente si no encuentra el ID
            $pelicula = Pelicula::findOrFail($id);
            return response()->json(['success' => true, 'data' => $pelicula, 'message' => 'Película encontrada']);
        } catch (Exception $e) {
            Log::error("Error buscar pelicula {$id}: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Película no encontrada'], 404);
        }
    }

    /**
     * Actualiza el título de una película existente.
     */
    public function update(Request $request, int $id): JsonResponse
    {
        try {
            // El ejercicio pide específicamente actualizar solo el título
            $data = $request->validate([
                'titulo' => 'required|string'
            ]);

            $pelicula = Pelicula::findOrFail($id);
            $pelicula->update($data);

            return response()->json(['success' => true, 'data' => $pelicula, 'message' => 'Título actualizado']);
        } catch (Exception $e) {
            Log::error("Error actualizar pelicula {$id}: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Error al actualizar registro'], 500);
        }
    }

    /**
     * Elimina el registro de una película de la base de datos.
     */
    public function destroy(int $id): JsonResponse
    {
        try {
            $pelicula = Pelicula::findOrFail($id);
            $pelicula->delete();
            return response()->json(['success' => true, 'data' => null, 'message' => 'Película eliminada']);
        } catch (Exception $e) {
            Log::error("Error eliminar pelicula {$id}: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Error al intentar eliminar'], 500);
        }
    }
}
