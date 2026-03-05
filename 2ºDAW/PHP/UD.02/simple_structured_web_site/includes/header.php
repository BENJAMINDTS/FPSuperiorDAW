<?php
$page_name = isset($_SESSION['page_name']) ? $_SESSION['page_name'] : "My Simple Structured Web Site";
?>
<!DOCTYPE html>
<html>
<head>
    <title><?php echo $page_name; ?></title>
    <link rel="stylesheet" type="text/css" href="../styles.css">
</head>
<body>