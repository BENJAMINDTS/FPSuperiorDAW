<?php
if ($_POST) {
    $mes_numero = $_POST['month'];
    
    $meses = [
        1 => 'enero',
        2 => 'febrero',
        3 => 'marzo',
        4 => 'abril',
        5 => 'mayo',
        6 => 'junio',
        7 => 'julio',
        8 => 'agosto',
        9 => 'septiembre',
        10 => 'octubre',
        11 => 'noviembre',
        12 => 'diciembre'
    ];
    
    $mes_nombre = $meses[$mes_numero] ?? 'inválido';
    
    switch ($mes_nombre) {
        case 'enero':
        case 'marzo':
        case 'mayo':
        case 'julio':
        case 'agosto':
        case 'octubre':
        case 'diciembre':
            $dias = 31;
            break;
        case 'abril':
        case 'junio':
        case 'septiembre':
        case 'noviembre':
            $dias = 30;
            break;
        case 'febrero':
            $dias = 28;
            break;
        default:
            $dias = 'inválido';
            break;
    }
    
    echo "<p>El mes de <strong>$mes_nombre</strong> tiene <strong>$dias</strong> días.</p>";
}
?>