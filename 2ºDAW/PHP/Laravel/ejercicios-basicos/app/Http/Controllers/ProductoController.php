<?php

namespace App\Http\Controllers;

use Illuminate\Http\JsonResponse;

/** @author BenjaminDTS */
class ProductoController extends Controller
{
    /**
     * Devuelve el listado de productos.
     */
    public function index(): JsonResponse
    {
        return response()->json(['success' => true, 'data' => null, 'message' => 'Listado de productos']);
    }

    /**
     * Muestra el identificador de un producto específico.
     */
    public function mostrar(int $id): JsonResponse
    {
        return response()->json(['success' => true, 'data' => ['id' => $id], 'message' => "Producto con ID: {$id}"]);
    }
}
