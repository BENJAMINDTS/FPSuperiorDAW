<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Representa la entidad Pelicula y gestiona su interacción con la base de datos.
 * @author BenjaminDTS
 */
class Pelicula extends Model
{
    /**
     * @var string Define explícitamente el nombre de la tabla (plural minúsculas).
     */
    protected $table = 'peliculas';

    /**
     * @var array<int, string> Lista blanca de campos que permiten asignación masiva.
     */
    protected $fillable = [
        'titulo',
        'director',
        'anio'
    ];
}
