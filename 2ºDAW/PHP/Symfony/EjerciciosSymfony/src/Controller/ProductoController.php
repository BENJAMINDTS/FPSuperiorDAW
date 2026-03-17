<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

/**
 * ProductoController
 *
 * Controlador que gestiona las operaciones relacionadas con productos.
 * Los datos son estáticos (array) ya que este ejercicio no usa base de datos.
 * Cubre los ejercicios 4 y 5 del módulo de Symfony.
 *
 * @author     Benjamín
 * @package    App\Controller
 */
class ProductoController extends AbstractController
{
    /**
     * Ejercicio 4 — Controlador de productos.
     *
     * Devuelve un listado de productos definidos como array estático
     * y los pasa a la vista para su representación.
     * Ruta: GET /productos
     *
     * @return Response Renderiza la vista productos.html.twig con el array $productos
     */
    #[Route('/productos', name: 'productos')]
    public function listar(): Response
    {
        // Array estático de productos (sin base de datos en este ejercicio)
        // Cada producto tiene: id, nombre y precio
        $productos = [
            ['id' => 1, 'nombre' => 'Teclado mecánico',  'precio' => 89],
            ['id' => 2, 'nombre' => 'Monitor 27"',        'precio' => 320],
            ['id' => 3, 'nombre' => 'Ratón inalámbrico',  'precio' => 45],
            ['id' => 4, 'nombre' => 'Auriculares gaming', 'precio' => 75],
            ['id' => 5, 'nombre' => 'Webcam HD',          'precio' => 60],
        ];

        return $this->render('productos.html.twig', ['productos' => $productos]);
    }

    /**
     * Ejercicio 5 — Mostrar producto por ID.
     *
     * Recibe un ID por la URL y lo pasa a la vista para mostrar
     * el detalle del producto correspondiente.
     * Ruta: GET /producto/{id}
     * Ejemplo: /producto/3 → "Mostrando producto con ID: 3"
     *
     * @param int $id Identificador del producto recibido como parámetro de ruta
     * @return Response Renderiza la vista producto_detalle.html.twig con el $id
     */
    #[Route('/producto/{id}', name: 'producto_mostrar')]
    public function mostrar(int $id): Response
    {
        return $this->render('producto_detalle.html.twig', ['id' => $id]);
    }
}
