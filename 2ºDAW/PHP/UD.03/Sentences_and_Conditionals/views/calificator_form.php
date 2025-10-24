<div id="calificator_form">
    <form onsubmit="enviarCalificacion(event)">
        <label for="grade">Calificación (0-10):</label>
        <input type="number" id="grade" name="grade" min="0" max="10" required><br><br>
        <input type="submit" value="Enviar">
    </form>
    <div id="resultado-calificacion"></div>
</div>

