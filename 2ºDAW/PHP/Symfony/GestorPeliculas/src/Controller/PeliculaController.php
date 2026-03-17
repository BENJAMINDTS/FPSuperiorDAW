<?php

namespace App\Controller;

use App\Entity\Pelicula;
use App\Form\PeliculaType;
use App\Repository\PeliculaRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Attribute\Route;

/**
 * PeliculaController
 *
 * Controlador que gestiona el CRUD completo de la entidad Pelicula
 * usando Doctrine ORM y formularios Symfony (PeliculaType).
 *
 * Operaciones disponibles:
 *   - Insertar (formulario) → GET/POST /pelicula/insertar
 *   - Listar todas          → GET /peliculas
 *   - Mostrar por ID        → GET /pelicula/{id}
 *   - Actualizar (form)     → GET/POST /pelicula/{id}/editar
 *   - Eliminar              → GET /pelicula/{id}/eliminar
 *
 * @author     Benjamín
 * @package    App\Controller
 */
class PeliculaController extends AbstractController
{
    /**
     * Muestra el formulario para insertar una película y procesa el envío.
     *
     * - GET:  muestra el formulario vacío.
     * - POST: valida los datos, persiste la entidad y redirige al listado.
     *
     * Ruta: GET|POST /pelicula/insertar
     *
     * @param Request                $request Objeto HTTP con los datos del formulario
     * @param EntityManagerInterface $em      Gestor de entidades de Doctrine
     * @return Response Formulario o redirección tras inserción exitosa
     */
    #[Route('/pelicula/insertar', name: 'pelicula_insertar')]
    public function insertar(Request $request, EntityManagerInterface $em): Response
    {
        // Se crea una entidad vacía que el formulario rellenará
        $pelicula = new Pelicula();

        // Se construye el formulario vinculado a la entidad
        $form = $this->createForm(PeliculaType::class, $pelicula, [
            'boton_label' => 'Insertar película',
        ]);

        // handleRequest() lee los datos POST y los mapea a la entidad
        $form->handleRequest($request);

        // isSubmitted() → el form fue enviado
        // isValid() → pasó todas las restricciones de validación
        if ($form->isSubmitted() && $form->isValid()) {
            $em->persist($pelicula);
            $em->flush();

            // Tras insertar se redirige al listado
            return $this->redirectToRoute('peliculas_listar');
        }

        return $this->render('pelicula_form.html.twig', [
            'form'   => $form,
            'titulo' => 'Insertar película',
            'modo'   => 'insertar',
        ]);
    }

    /**
     * Muestra un listado con todas las películas almacenadas.
     *
     * Ruta: GET /peliculas
     *
     * @param PeliculaRepository $repo Repositorio de Pelicula
     * @return Response Renderiza peliculas.html.twig con el array de entidades
     */
    #[Route('/peliculas', name: 'peliculas_listar')]
    public function listar(PeliculaRepository $repo): Response
    {
        // findAll() devuelve todos los registros de la tabla
        $peliculas = $repo->findAll();

        return $this->render('peliculas.html.twig', ['peliculas' => $peliculas]);
    }

    /**
     * Muestra el detalle de una película buscada por su ID.
     *
     * Ruta: GET /pelicula/{id}
     * Ejemplo: /pelicula/1
     *
     * @param int                $id   ID de la película
     * @param PeliculaRepository $repo Repositorio de Pelicula
     * @return Response Vista de detalle o mensaje de error 404
     */
    #[Route('/pelicula/{id}', name: 'pelicula_mostrar', requirements: ['id' => '\d+'])]
    public function mostrar(int $id, PeliculaRepository $repo): Response
    {
        $pelicula = $repo->find($id);

        if (!$pelicula) {
            return $this->render('pelicula_mensaje.html.twig', [
                'tipo'    => 'error',
                'titulo'  => 'Película no encontrada',
                'mensaje' => "No existe ninguna película con ID $id.",
                'icono'   => '🔍',
            ], new Response('', 404));
        }

        return $this->render('pelicula_detalle.html.twig', ['pelicula' => $pelicula]);
    }

    /**
     * Muestra el formulario de edición y procesa la actualización de una película.
     *
     * - GET:  carga el formulario pre-rellenado con los datos actuales.
     * - POST: valida, aplica los cambios y redirige al detalle.
     *
     * Ruta: GET|POST /pelicula/{id}/editar
     * Ejemplo: /pelicula/1/editar
     *
     * @param int                    $id      ID de la película a editar
     * @param Request                $request Objeto HTTP con los datos del formulario
     * @param PeliculaRepository     $repo    Repositorio de Pelicula
     * @param EntityManagerInterface $em      Gestor de entidades de Doctrine
     * @return Response Formulario de edición o redirección tras actualización
     */
    #[Route('/pelicula/{id}/editar', name: 'pelicula_editar')]
    public function editar(int $id, Request $request, PeliculaRepository $repo, EntityManagerInterface $em): Response
    {
        $pelicula = $repo->find($id);

        if (!$pelicula) {
            return $this->render('pelicula_mensaje.html.twig', [
                'tipo'    => 'error',
                'titulo'  => 'Película no encontrada',
                'mensaje' => "No existe ninguna película con ID $id.",
                'icono'   => '🔍',
            ]);
        }

        // El formulario se vincula a la entidad ya cargada: los campos aparecen pre-rellenados
        $form = $this->createForm(PeliculaType::class, $pelicula, [
            'boton_label' => 'Guardar cambios',
        ]);

        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // No hace falta persist() porque la entidad ya está gestionada por Doctrine
            // flush() detecta los cambios y ejecuta el UPDATE automáticamente
            $em->flush();

            return $this->redirectToRoute('pelicula_mostrar', ['id' => $id]);
        }

        return $this->render('pelicula_form.html.twig', [
            'form'     => $form,
            'titulo'   => 'Editar película',
            'modo'     => 'editar',
            'pelicula' => $pelicula,
        ]);
    }

    /**
     * Elimina una película de la base de datos.
     *
     * Ruta: GET /pelicula/{id}/eliminar
     * Ejemplo: /pelicula/1/eliminar
     *
     * @param int                    $id   ID de la película a eliminar
     * @param PeliculaRepository     $repo Repositorio de Pelicula
     * @param EntityManagerInterface $em   Gestor de entidades de Doctrine
     * @return Response Mensaje de confirmación o error
     */
    #[Route('/pelicula/{id}/eliminar', name: 'pelicula_eliminar')]
    public function eliminar(int $id, PeliculaRepository $repo, EntityManagerInterface $em): Response
    {
        $pelicula = $repo->find($id);

        if (!$pelicula) {
            return $this->render('pelicula_mensaje.html.twig', [
                'tipo'    => 'error',
                'titulo'  => 'Película no encontrada',
                'mensaje' => "No existe ninguna película con ID $id.",
                'icono'   => '🔍',
            ]);
        }

        // Se guarda el título antes de remove() para usarlo en el mensaje
        $titulo = $pelicula->getTitulo();

        $em->remove($pelicula);
        $em->flush();

        return $this->render('pelicula_mensaje.html.twig', [
            'tipo'    => 'eliminar',
            'titulo'  => '¡Película eliminada!',
            'mensaje' => "«{$titulo}» ha sido eliminada de la base de datos.",
            'icono'   => '🗑️',
        ]);
    }
}
