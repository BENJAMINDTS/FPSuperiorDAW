<?php

namespace App\Entity;

use App\Repository\LibroRepository;
use Doctrine\ORM\Mapping as ORM;

/**
 * Libro
 *
 * Entidad que representa un libro en la base de datos.
 * Mapeada a la tabla "libro" mediante las anotaciones de Doctrine ORM.
 * Cubre el ejercicio 9 del módulo de Symfony.
 *
 * @author     Benjamín
 * @package    App\Entity
 */
#[ORM\Entity(repositoryClass: LibroRepository::class)]
class Libro
{
    /**
     * Identificador único del libro.
     * Generado automáticamente por la base de datos (AUTO_INCREMENT).
     *
     * @var int|null
     */
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    /**
     * Título del libro.
     * Máximo 255 caracteres.
     *
     * @var string|null
     */
    #[ORM\Column(length: 255)]
    private ?string $titulo = null;

    /**
     * Nombre del autor del libro.
     * Máximo 255 caracteres.
     *
     * @var string|null
     */
    #[ORM\Column(length: 255)]
    private ?string $autor = null;

    /**
     * Precio del libro en euros.
     * Almacenado como DOUBLE en la base de datos.
     *
     * @var float|null
     */
    #[ORM\Column]
    private ?float $precio = null;

    /**
     * Devuelve el ID del libro.
     *
     * @return int|null ID del libro o null si aún no ha sido persistido
     */
    public function getId(): ?int
    {
        return $this->id;
    }

    /**
     * Devuelve el título del libro.
     *
     * @return string|null Título del libro o null si no está establecido
     */
    public function getTitulo(): ?string
    {
        return $this->titulo;
    }

    /**
     * Establece el título del libro.
     *
     * @param string $titulo Nuevo título del libro
     * @return static Devuelve la misma instancia para permitir encadenamiento (fluent interface)
     */
    public function setTitulo(string $titulo): static
    {
        $this->titulo = $titulo;
        return $this;
    }

    /**
     * Devuelve el autor del libro.
     *
     * @return string|null Nombre del autor o null si no está establecido
     */
    public function getAutor(): ?string
    {
        return $this->autor;
    }

    /**
     * Establece el autor del libro.
     *
     * @param string $autor Nombre del autor
     * @return static Devuelve la misma instancia para permitir encadenamiento (fluent interface)
     */
    public function setAutor(string $autor): static
    {
        $this->autor = $autor;
        return $this;
    }

    /**
     * Devuelve el precio del libro.
     *
     * @return float|null Precio en euros o null si no está establecido
     */
    public function getPrecio(): ?float
    {
        return $this->precio;
    }

    /**
     * Establece el precio del libro.
     *
     * @param float $precio Precio en euros
     * @return static Devuelve la misma instancia para permitir encadenamiento (fluent interface)
     */
    public function setPrecio(float $precio): static
    {
        $this->precio = $precio;
        return $this;
    }
}
