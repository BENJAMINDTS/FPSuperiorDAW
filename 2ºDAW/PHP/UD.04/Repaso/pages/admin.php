<?php
require_once '../config/config.php';
requireAdmin(); // Requiere rol de administrador

$users = $GLOBALS['users'];
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Panel de Administración - Sistema de Autenticación</title>
    <link rel="stylesheet" href="../css/style.css">
    <link rel="stylesheet" href="../css/admin.css">
</head>
<body>
    <div class="admin-header">
        <div class="container">
            <h1>⚙️ Panel de Administración</h1>
            <p>Gestión completa del sistema de usuarios y seguridad</p>
        </div>
    </div>
    
    <div class="container">
        <!-- Quick Stats -->
        <div class="quick-stats">
            <div class="quick-stat">
                <span class="number"><?php echo count($users); ?></span>
                <span class="label">Usuarios Totales</span>
            </div>
            <div class="quick-stat">
                <span class="number"><?php echo count(array_filter($users, fn($user) => $user['role'] === 'admin')); ?></span>
                <span class="label">Administradores</span>
            </div>
            <div class="quick-stat">
                <span class="number"><?php echo count(array_filter($users, fn($user) => $user['role'] === 'user')); ?></span>
                <span class="label">Usuarios Regulares</span>
            </div>
            <div class="quick-stat">
                <span class="number"><?php echo $_SESSION['visit_count'] ?? 1; ?></span>
                <span class="label">Visitas en Sesión</span>
            </div>
        </div>

        <!-- Admin Panel -->
        <div class="admin-panel">
            <h2>Bienvenido, Administrador</h2>
            <p>Esta es el área exclusiva para administradores del sistema. Desde aquí puedes gestionar usuarios, ver estadísticas del sistema y configurar opciones avanzadas.</p>
            
            <div class="health-status mt-3">
                <div class="status-indicator"></div>
                <span><strong>Estado del Sistema:</strong> Todo funciona correctamente</span>
            </div>
        </div>

        <!-- Gestión de Usuarios -->
        <div class="card">
            <h2>👥 Gestión de Usuarios</h2>
            <div class="table-container">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Usuario</th>
                            <th>Nombre</th>
                            <th>Email</th>
                            <th>Rol</th>
                            <th>Estado</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>
                    <tbody>
                        <?php foreach ($users as $username => $user): ?>
                        <tr>
                            <td>
                                <strong><?php echo htmlspecialchars($username); ?></strong>
                                <?php if ($username === $_SESSION['user_id']): ?>
                                    <span class="badge badge-primary">Tú</span>
                                <?php endif; ?>
                            </td>
                            <td><?php echo htmlspecialchars($user['name']); ?></td>
                            <td><?php echo htmlspecialchars($user['email']); ?></td>
                            <td>
                                <span class="badge <?php echo $user['role'] === 'admin' ? 'badge-warning' : 'badge-primary'; ?>">
                                    <?php echo htmlspecialchars($user['role']); ?>
                                </span>
                            </td>
                            <td>
                                <span class="badge badge-success">Activo</span>
                            </td>
                            <td>
                                <div class="user-actions">
                                    <button class="btn btn-primary btn-sm action-btn">✏️ Editar</button>
                                    <?php if ($username !== $_SESSION['user_id']): ?>
                                        <button class="btn btn-danger btn-sm action-btn">🗑️ Eliminar</button>
                                    <?php endif; ?>
                                </div>
                            </td>
                        </tr>
                        <?php endforeach; ?>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Estadísticas del Sistema -->
        <div class="card">
            <h2>📊 Estadísticas del Sistema</h2>
            <div class="grid grid-3">
                <div class="stat-card">
                    <h3>Usuarios Registrados</h3>
                    <p><?php echo count($users); ?></p>
                </div>
                <div class="stat-card">
                    <h3>Administradores</h3>
                    <p><?php echo count(array_filter($users, fn($user) => $user['role'] === 'admin')); ?></p>
                </div>
                <div class="stat-card">
                    <h3>Usuarios Regulares</h3>
                    <p><?php echo count(array_filter($users, fn($user) => $user['role'] === 'user')); ?></p>
                </div>
            </div>
        </div>

        <!-- Herramientas de Administración -->
        <div class="card">
            <h2>🛠️ Herramientas de Administración</h2>
            <div class="grid grid-2">
                <div>
                    <h4>Gestión de Usuarios</h4>
                    <div style="display: flex; flex-direction: column; gap: 0.5rem; margin-top: 1rem;">
                        <button class="btn btn-primary">➕ Agregar Usuario</button>
                        <button class="btn btn-warning">📧 Enviar Notificación Masiva</button>
                        <button class="btn btn-success">📋 Generar Reporte</button>
                    </div>
                </div>
                <div>
                    <h4>Configuración del Sistema</h4>
                    <div style="display: flex; flex-direction: column; gap: 0.5rem; margin-top: 1rem;">
                        <button class="btn btn-outline">⚙️ Configuración General</button>
                        <button class="btn btn-outline">🔐 Políticas de Seguridad</button>
                        <button class="btn btn-outline">📊 Logs del Sistema</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- System Health -->
        <div class="system-health">
            <h3>🩺 Estado del Sistema</h3>
            <div class="health-metrics">
                <div class="health-metric">
                    <div class="value">100%</div>
                    <div class="label">Disponibilidad</div>
                </div>
                <div class="health-metric">
                    <div class="value">0</div>
                    <div class="label">Errores</div>
                </div>
                <div class="health-metric">
                    <div class="value"><?php echo count($users); ?></div>
                    <div class="label">Usuarios Activos</div>
                </div>
                <div class="health-metric">
                    <div class="value"><?php echo round(memory_get_usage() / 1024 / 1024, 2); ?> MB</div>
                    <div class="label">Uso de Memoria</div>
                </div>
            </div>
        </div>

        <div class="text-center mt-4">
            <a href="index.php" class="btn btn-primary">🏠 Volver al Inicio</a>
            <a href="protected.php" class="btn btn-success">🔒 Página Protegida</a>
        </div>
    </div>
</body>
</html>