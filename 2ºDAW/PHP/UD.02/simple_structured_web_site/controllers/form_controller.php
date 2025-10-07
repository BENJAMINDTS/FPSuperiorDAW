<?php
if ($_SERVER["REQUEST_METHOD"] == "POST" && isset($_POST['name'])) {
    $_SESSION['page_name'] = htmlspecialchars($_POST['name']);
}
$name = isset($_SESSION['page_name']) ? $_SESSION['page_name'] : "My Simple Structured Web Site";
?>