<?php
/**
 * Vista: Lista de Productos
 * Muestra todos los productos del catálogo
 * @package Views
 * @category Producto
 * @author Benjamín Santiago González
 * @since 2025
 */
if (!defined('CON_CONTROLADOR')) {
    die("Acceso directo no permitido. Debe acceder a través del controlador.");
}

$titulo = 'Productos';
$icono = 'box';
$accion = [
    'url' => '/daw/producto/crear',
    'icono' => 'plus',
    'texto' => 'Nuevo Producto'
];
?>
<!-- Contenido específico -->
<div class="card">
    <div class="card-header bg-primary text-white">
        <h5 class="card-title mb-0">
            <i class="fas fa-boxes me-2"></i>Catálogo de Productos
        </h5>
    </div>
    <div class="card-body">
        <?php if (empty($productos)): ?>
            <div class="alert alert-info">
                <i class="fas fa-info-circle me-2"></i>
                No hay productos en el catálogo. 
                <a href="/daw/producto/crear" class="alert-link">Crear el primero</a>
            </div>
        <?php else: ?>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Categoría</th>
                            <th>Precio</th>
                            <th>Stock</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($productos as $producto): 
                        $precio_formateado = number_format($producto['precio'], 2, ',', '.') . ' €';
                        $stock_clase = $producto['stock'] > 0 ? 'success' : 'danger';
                        ?>
                        <tr>
                            <td><?php echo $producto['id']; ?></td>
                            <td><?php echo htmlspecialchars($producto['nombre']); ?></td>
                            <td>
                                <span class="badge bg-info"><?php echo htmlspecialchars($producto['categoria']); ?></span>
                            </td>
                            <td class="fw-bold"><?php echo $precio_formateado; ?></td>
                            <td>
                                <span class="badge bg-<?php echo $stock_clase; ?>">
                                    <?php echo $producto['stock']; ?> unidades
                                </span>
                            </td>
                            <td>
                                <a href="#" class="btn btn-sm btn-info" title="Ver">
                                    <i class="fas fa-eye"></i>
                                </a>
                            </td>
                        </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>
            </div>
        <?php endif; ?>
    </div>
    <div class="card-footer text-muted">
        Total: <?php echo count($productos); ?> productos
    </div>
</div>