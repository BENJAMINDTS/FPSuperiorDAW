<?php
/**
 * Vista: Formulario de Creación de Producto

 * Formulario para crear nuevos productos en el sistema

 * @package Views
 * @category Producto
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
    die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

$titulo = 'Nuevo Producto';
$icono = 'box-open';
$breadcrumb = 'Crear Producto';
?>
<!-- Contenido específico de la página -->
<div class="row justify-content-center">
    <div class="col-lg-8">
        <div class="card">
            <div class="card-header bg-primary text-white">
                <h5 class="card-title mb-0">
                    <i class="fas fa-box-open me-2"></i>Nuevo Producto
                </h5>
            </div>
            <div class="card-body">
                <form action="/daw/producto/guardar" method="POST">
                    <!-- Información básica del producto -->
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre del producto *</label>
                            <input type="text" class="form-control" id="nombre" name="nombre" 
                                   required maxlength="100" 
                                   placeholder="Ej: Laptop HP Pavilion 15">
                            <div class="form-text">Nombre descriptivo del producto</div>
                        </div>
                        
                        <div class="col-md-6 mb-3">
                            <label for="categoria" class="form-label">Categoría *</label>
                            <select class="form-select" id="categoria" name="categoria" required>
                                <option value="" selected disabled>Seleccione una categoría</option>
                                <option value="Electrónica">Electrónica</option>
                                <option value="Informática">Informática</option>
                                <option value="Hogar">Hogar</option>
                                <option value="Ropa">Ropa</option>
                                <option value="Deportes">Deportes</option>
                                <option value="Libros">Libros</option>
                                <option value="Juguetes">Juguetes</option>
                                <option value="Otros">Otros</option>
                            </select>
                            <div class="form-text">Seleccione la categoría principal</div>
                        </div>
                    </div>
                    
                    <!-- Descripción -->
                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción *</label>
                        <textarea class="form-control" id="descripcion" name="descripcion" 
                                  rows="4" required 
                                  placeholder="Describa las características del producto..."></textarea>
                        <div class="form-text">Describa el producto detalladamente</div>
                    </div>
                    
                    <!-- Precio y Stock -->
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="precio" class="form-label">Precio (€) *</label>
                            <div class="input-group">
                                <input type="number" class="form-control" id="precio" name="precio" 
                                       step="0.01" min="0.01" max="999999.99" required 
                                       placeholder="0.00">
                                <span class="input-group-text">€</span>
                            </div>
                            <div class="form-text">Precio unitario en euros</div>
                        </div>
                        
                        <div class="col-md-6 mb-3">
                            <label for="stock" class="form-label">Stock *</label>
                            <input type="number" class="form-control" id="stock" name="stock" 
                                   min="0" max="99999" required value="0">
                            <div class="form-text">Cantidad disponible en inventario</div>
                        </div>
                    </div>
                    
                    <!-- Información adicional -->
                    <div class="mb-3">
                        <label class="form-label">Información Adicional (Opcional)</label>
                        <div class="row">
                            <div class="col-md-4 mb-2">
                                <label class="form-label small">Marca</label>
                                <input type="text" class="form-control" 
                                       name="marca" placeholder="Ej: HP, Samsung, Nike">
                            </div>
                            <div class="col-md-4 mb-2">
                                <label class="form-label small">Modelo</label>
                                <input type="text" class="form-control" 
                                       name="modelo" placeholder="Ej: Pavilion 15-dw1000">
                            </div>
                            <div class="col-md-4 mb-2">
                                <label class="form-label small">Color</label>
                                <input type="text" class="form-control" 
                                       name="color" placeholder="Ej: Negro, Plateado">
                            </div>
                        </div>
                    </div>
                    
                    <!-- Estado del producto -->
                    <div class="mb-3">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="activo" name="activo" checked>
                            <label class="form-check-label" for="activo">
                                Producto activo (disponible para venta)
                            </label>
                        </div>
                    </div>
                    
                    <!-- Notas -->
                    <div class="alert alert-info">
                        <i class="fas fa-info-circle me-2"></i>
                        <strong>Nota:</strong> Los campos marcados con * son obligatorios.
                    </div>
                    
                    <!-- Botones de acción -->
                    <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                        <a href="/daw/index.php?url=producto" class="btn btn-secondary me-md-2">
                            <i class="fas fa-times me-1"></i> Cancelar
                        </a>
                        <button type="reset" class="btn btn-outline-secondary me-md-2">
                            <i class="fas fa-eraser me-1"></i> Limpiar
                        </button>
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-1"></i> Guardar Producto
                        </button>
                    </div>
                </form>
            </div>
            
            <div class="card-footer text-muted">
                <small>
                    <i class="fas fa-lightbulb me-1"></i>
                    Consejo: Complete todos los campos para una mejor gestión del inventario.
                </small>
            </div>
        </div>
    </div>
</div>


