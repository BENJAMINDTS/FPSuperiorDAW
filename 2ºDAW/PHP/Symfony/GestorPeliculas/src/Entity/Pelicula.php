<?php

namespace App\Entity;

use App\Repository\PeliculaRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * Pelicula
 *
 * Entidad que representa una película en la base de datos.
 * Mapeada a la tabla "pelicula" mediante las anotaciones de Doctrine ORM.
 *
 * @author     Benjamín
 * @package    App\Entity
 */
#[ORM\Entity(repositoryClass: PeliculaRepository::class)]
class Pelicula
{
    /**
     * Identificador único de la película.
     * Generado automáticamente por la base de datos (AUTO_INCREMENT).
     *
     * @var int|null
     */
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    /**
     * Título de la película.
     * Máximo 255 caracteres.
     *
     * @var string|null
     */
    #[ORM\Column(length: 255)]
    private ?string $titulo = null;

    /**
     * Nombre del director de la película.
     * Máximo 255 caracteres.
     *
     * @var string|null
     */
    #[ORM\Column(length: 255)]
    private ?string $director = null;

    /**
     * Año de estreno de la película.
     *
     * @var int|null
     */
    #[ORM\Column]
    private ?int $anio = null;

    /**
     * Devuelve el ID de la película.
     *
     * @return int|null ID o null si aún no ha sido persistida
     */
    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * Devuelve el título de la película.
     *
     * @return string|null Título o null si no está establecido
     */
    public function getTitulo(): ?string
    {
        return $this->titulo;
    }

    /**
     * Establece el título de la película.
     *
     * @param string $titulo Nuevo título
     * @return static Fluent interface
     */
    public function setTitulo(string $titulo): static
    {
        $this->titulo = $titulo;
        return $this;
    }

    /**
     * Devuelve el director de la película.
     *
     * @return string|null Nombre del director o null si no está establecido
     */
    public function getDirector(): ?string
    {
        return $this->director;
    }

    /**
     * Establece el director de la película.
     *
     * @param string $director Nombre del director
     * @return static Fluent interface
     */
    public function setDirector(string $director): static
    {
        $this->director = $director;
        return $this;
    }

    /**
     * Devuelve el año de estreno de la película.
     *
     * @return int|null Año o null si no está establecido
     */
    public function getAnio(): ?int
    {
        return $this->anio;
    }

    /**
     * Establece el año de estreno de la película.
     *
     * @param int $anio Año de estreno
     * @return static Fluent interface
     */
    public function setAnio(int $anio): static
    {
        $this->anio = $anio;
        return $this;
    }
}
