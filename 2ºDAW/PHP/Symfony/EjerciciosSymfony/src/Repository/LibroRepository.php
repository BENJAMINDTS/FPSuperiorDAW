<?php

namespace App\Repository;

use App\Entity\Libro;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * LibroRepository
 *
 * Repositorio de la entidad Libro. Hereda de ServiceEntityRepository,
 * lo que proporciona automáticamente los métodos básicos de consulta:
 *   - find($id)       → busca por clave primaria
 *   - findAll()       → devuelve todos los registros
 *   - findBy([...])   → busca por criterios
 *   - findOneBy([...])→ devuelve un único resultado por criterios
 *
 * Aquí se pueden añadir métodos de consulta personalizados con DQL o QueryBuilder.
 *
 * @extends ServiceEntityRepository<Libro>
 *
 * @author     Benjamín
 * @package    App\Repository
 */
class LibroRepository extends ServiceEntityRepository
{
    /**
     * Constructor del repositorio.
     *
     * Registra la entidad Libro en el ManagerRegistry de Doctrine
     * para que el repositorio sepa sobre qué tabla operar.
     *
     * @param ManagerRegistry $registry Registro de gestores de Doctrine (inyección automática)
     */
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Libro::class);
    }
}
