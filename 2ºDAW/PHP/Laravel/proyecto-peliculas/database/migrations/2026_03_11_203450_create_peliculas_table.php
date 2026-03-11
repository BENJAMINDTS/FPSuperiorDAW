<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

/**
 * Migración para estructurar la tabla peliculas.
 * @author BenjaminDTS
 */
return new class extends Migration
{
    /**
     * Crea las columnas necesarias en la base de datos.
     * Se usa 'anio' en lugar de 'año' para prevenir errores de codificación.
     */
    public function up(): void
    {
        Schema::create('peliculas', function (Blueprint $table) {
            $table->id();
            $table->string('titulo');
            $table->string('director');
            $table->integer('anio');
            $table->timestamps();
        });
    }

    /**
     * Elimina la tabla si se revierte la migración.
     */
    public function down(): void
    {
        Schema::dropIfExists('peliculas');
    }
};
