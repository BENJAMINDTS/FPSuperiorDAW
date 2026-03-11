<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;

/**
 * Clase User
 *
 * Modelo de Eloquent que representa a los usuarios registrados en el sistema.
 * Extiende de Authenticatable para integrar la lógica de inicio de sesión
 * y utiliza traits para la gestión de factorías y notificaciones.
 *
 * @package App\Models
 * @author BenjaminDTS
 */
class User extends Authenticatable
{
    /** * @use HasFactory<\Database\Factories\UserFactory> 
     */
    use HasFactory, Notifiable;

    /**
     * Los atributos que son asignables en masa (mass assignable).
     *
     * Define qué campos pueden ser rellenados directamente usando 
     * métodos como User::create() o $user->update().
     *
     * @var list<string>
     */
    protected $fillable = [
        'name',
        'email',
        'password',
    ];

    /**
     * Los atributos que deben estar ocultos para la serialización.
     *
     * Evita que datos sensibles como la contraseña o el token de sesión 
     * se expongan cuando el modelo se convierte a un arreglo o formato JSON.
     *
     * @var list<string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * Obtiene los atributos que deben ser convertidos (casteados) a tipos nativos.
     *
     * Permite a Laravel transformar automáticamente el tipo de dato al leer
     * o escribir en la base de datos (por ejemplo, asegurando que la contraseña
     * esté hasheada y que la fecha de verificación sea un objeto Carbon).
     *
     * @return array<string, string> Arreglo con la configuración de casteo de atributos.
     */
    protected function casts(): array
    {
        return [
            'email_verified_at' => 'datetime',
            'password' => 'hashed',
        ];
    }
}
