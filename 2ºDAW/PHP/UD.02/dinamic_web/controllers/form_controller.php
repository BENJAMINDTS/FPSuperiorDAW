<?php
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['name'])) {
    $_SESSION['my_name'] = htmlspecialchars($_POST['name']);
}
$name = isset($_SESSION['my_name']) ? $_SESSION['my_name'] : "";
?>