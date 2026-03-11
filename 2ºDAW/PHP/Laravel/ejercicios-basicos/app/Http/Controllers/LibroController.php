<?php

namespace App\Http\Controllers;

use App\Models\Libro;
use Illuminate\Http\JsonResponse;
use Illuminate\Support\Facades\Log;
use Exception;

/** @author BenjaminDTS */
class LibroController extends Controller
{
    /**
     * Lista todos los libros formateados.
     */
    public function index(): JsonResponse
    {
        try {
            $libros = Libro::all()->map(fn($l) => "{$l->titulo} - {$l->autor} - {$l->precio}€");
            return response()->json(['success' => true, 'data' => $libros, 'message' => 'Libros listados']);
        } catch (Exception $e) {
            Log::error("Error listar libros: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Error interno'], 500);
        }
    }

    /**
     * Inserta un libro con datos fijos.
     */
    public function store(): JsonResponse
    {
        try {
            $libro = Libro::create(['titulo' => 'El Quijote', 'autor' => 'Cervantes', 'precio' => 20]);
            return response()->json(['success' => true, 'data' => $libro, 'message' => 'Insertado'], 201);
        } catch (Exception $e) {
            Log::error("Error guardar libro: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Error al guardar'], 500);
        }
    }

    /**
     * Actualiza el precio de un libro por su ID.
     */
    public function update(int $id): JsonResponse
    {
        try {
            $libro = Libro::findOrFail($id);
            $libro->update(['precio' => 25]);
            return response()->json(['success' => true, 'data' => $libro, 'message' => 'Precio actualizado']);
        } catch (Exception $e) {
            Log::error("Error actualizar libro {$id}: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Error al actualizar'], 500);
        }
    }

    /**
     * Elimina un libro por su ID.
     */
    public function destroy(int $id): JsonResponse
    {
        try {
            Libro::findOrFail($id)->delete();
            return response()->json(['success' => true, 'data' => null, 'message' => 'Libro eliminado']);
        } catch (Exception $e) {
            Log::error("Error eliminar libro {$id}: " . $e->getMessage());
            return response()->json(['success' => false, 'data' => null, 'message' => 'Error al eliminar'], 500);
        }
    }
}
