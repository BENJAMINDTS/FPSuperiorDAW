<?php

namespace App\Repository;

use App\Entity\Pelicula;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * PeliculaRepository
 *
 * Repositorio de la entidad Pelicula. Hereda de ServiceEntityRepository,
 * proporcionando automáticamente los métodos básicos de consulta:
 *   - find($id)        → busca por clave primaria
 *   - findAll()        → devuelve todos los registros
 *   - findBy([...])    → busca por criterios
 *   - findOneBy([...]) → devuelve un único resultado por criterios
 *
 * @extends ServiceEntityRepository<Pelicula>
 *
 * @author     Benjamín
 * @package    App\Repository
 */
class PeliculaRepository extends ServiceEntityRepository
{
    /**
     * Constructor del repositorio.
     *
     * Registra la entidad Pelicula en el ManagerRegistry de Doctrine.
     *
     * @param ManagerRegistry $registry Registro de gestores de Doctrine (inyección automática)
     */
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Pelicula::class);
    }
}
