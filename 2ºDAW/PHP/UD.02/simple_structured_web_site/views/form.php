<?php
include '../controllers/form_controller.php';
include '../includes/header.php';
include '../includes/nav_bar.php';
?>

<h1>Form to change the page name</h1>
<form method="POST" action="">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="<?php echo $name; ?>" required>
    <input type="submit" value="Submit">
</form>

<?php include '../includes/footer.php'; ?>