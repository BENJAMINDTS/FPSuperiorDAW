<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

/**
 * InicioController
 *
 * Controlador principal que agrupa los ejercicios básicos de rutas y vistas Twig.
 * Cubre los ejercicios 1, 2, 3, 6, 7 y 8 del módulo de Symfony.
 *
 * @author     Benjamín
 * @package    App\Controller
 */
class InicioController extends AbstractController
{
    /**
     * Ejercicio 1 — Primera ruta.
     *
     * Muestra la página de inicio de la aplicación.
     * Ruta: GET /inicio
     *
     * @return Response Renderiza la vista inicio.html.twig
     */
    #[Route('/inicio', name: 'inicio')]
    public function index(): Response
    {
        return $this->render('inicio.html.twig');
    }

    /**
     * Ejercicio 2 — Ruta con parámetro.
     *
     * Recibe un nombre por la URL y lo pasa a la vista para mostrar un saludo.
     * Ruta: GET /saludo/{nombre}
     * Ejemplo: /saludo/Ana → "Hola Ana, bienvenido a Symfony"
     *
     * @param string $nombre Nombre recibido como parámetro de ruta
     * @return Response Renderiza la vista saludo.html.twig con la variable $nombre
     */
    #[Route('/saludo/{nombre}', name: 'saludo')]
    public function saludo(string $nombre): Response
    {
        return $this->render('saludo.html.twig', ['nombre' => $nombre]);
    }

    /**
     * Ejercicio 3 — Ruta con dos parámetros.
     *
     * Recibe dos números por la URL, los multiplica y pasa el resultado a la vista.
     * Ruta: GET /multiplicar/{num1}/{num2}
     * Ejemplo: /multiplicar/4/5 → "El resultado es: 20"
     *
     * @param int $num1 Primer operando (parámetro de ruta)
     * @param int $num2 Segundo operando (parámetro de ruta)
     * @return Response Renderiza la vista multiplicar.html.twig con num1, num2 y resultado
     */
    #[Route('/multiplicar/{num1}/{num2}', name: 'multiplicar')]
    public function multiplicar(int $num1, int $num2): Response
    {
        // Se calcula el producto de los dos números recibidos por URL
        $resultado = $num1 * $num2;

        return $this->render('multiplicar.html.twig', [
            'num1'      => $num1,
            'num2'      => $num2,
            'resultado' => $resultado,
        ]);
    }

    /**
     * Ejercicio 6 — Crear una vista Twig.
     *
     * Carga una plantilla Twig sin pasar variables al contexto.
     * Ruta: GET /bienvenida
     *
     * @return Response Renderiza la vista bienvenida.html.twig
     */
    #[Route('/bienvenida', name: 'bienvenida')]
    public function bienvenida(): Response
    {
        return $this->render('bienvenida.html.twig');
    }

    /**
     * Ejercicio 7 — Pasar una variable a la vista.
     *
     * Define una variable en el controlador y la envía a la plantilla Twig
     * para que sea accesible mediante {{ nombre }}.
     * Ruta: GET /hola
     *
     * @return Response Renderiza la vista hola.html.twig con la variable $nombre
     */
    #[Route('/hola', name: 'hola')]
    public function hola(): Response
    {
        // Variable definida en el controlador que se pasará a la vista
        $nombre = 'Carlos';

        return $this->render('hola.html.twig', ['nombre' => $nombre]);
    }

    /**
     * Ejercicio 8 — Mostrar una lista con bucle Twig.
     *
     * Crea un array de ciudades en el controlador y lo envía a la vista,
     * donde se recorre con un bucle {% for %} de Twig.
     * Ruta: GET /ciudades
     *
     * @return Response Renderiza la vista ciudades.html.twig con el array $ciudades
     */
    #[Route('/ciudades', name: 'ciudades')]
    public function ciudades(): Response
    {
        // Array de ciudades que se pasará a Twig para iterar con {% for %}
        $ciudades = ['Granada', 'Madrid', 'Sevilla', 'Valencia'];

        return $this->render('ciudades.html.twig', ['ciudades' => $ciudades]);
    }
}
