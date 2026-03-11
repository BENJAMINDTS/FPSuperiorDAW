<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

/**
 * Clase Libro
 *
 * Modelo de Eloquent que representa los libros almacenados en la base de datos.
 * Permite gestionar la información de los libros, incluyendo su título y autor.
 *
 * @package App\Models
 * @author BenjaminDTS
 */
class Libro extends Model
{
    /** * @use HasFactory<\Database\Factories\LibroFactory> 
     */
    use HasFactory;

    /**
     * Los atributos que son asignables en masa (mass assignable).
     *
     * Permite que los campos 'titulo' y 'autor' puedan ser guardados directamente
     * en la base de datos mediante métodos como Libro::create() o al actualizar.
     *
     * @var list<string>
     */
    protected $fillable = [
        'titulo',
        'autor'
    ];
}
