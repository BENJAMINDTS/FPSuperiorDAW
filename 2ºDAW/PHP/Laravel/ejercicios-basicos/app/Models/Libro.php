<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

/**
 * Modelo para la entidad Libro.
 * @author BenjaminDTS
 * * Se define explícitamente la tabla y los campos rellenables para
 * aplicar el principio de seguridad contra asignaciones masivas (Mass Assignment).
 */
class Libro extends Model
{
    /**
     * @var string Nombre de la tabla en la base de datos (plural y minúsculas).
     */
    protected $table = 'libros';

    /**
     * @var array<int, string> Campos permitidos para inserción masiva (create/update).
     * Esto evita que usuarios malintencionados inyecten columnas no deseadas (ej. id o roles).
     */
    protected $fillable = [
        'titulo',
        'autor',
        'precio'
    ];
}
