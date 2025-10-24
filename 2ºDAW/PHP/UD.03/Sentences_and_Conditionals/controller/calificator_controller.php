<?php
if ($_POST) {
    $grade = $_POST['grade'];
    if($grade>=9){
        echo "<p>Sobresaliente</p>";
    }else if($grade>7 && $grade<9){
        echo "<p>Notable</p>";
    } else if($grade>=5 && $grade<=7){
        echo "<p>Aprobado</p>";
    }else if($grade<5){
        echo "<p>Suspenso</p>";
    }else{
        echo "<p>La calificación no es válida.</p>";
    }
}
?>