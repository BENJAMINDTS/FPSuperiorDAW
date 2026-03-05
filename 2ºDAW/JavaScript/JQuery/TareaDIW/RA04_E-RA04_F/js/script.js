$(document).ready(function() {
    console.log("Documento listo - jQuery cargado");
    
    // Verificar que las imágenes se cargan correctamente
    $('img').on('load', function() {
        console.log('Imagen cargada correctamente:', this.src);
    }).on('error', function() {
        console.error('Error cargando imagen:', this.src);
        // Crear un placeholder si la imagen no se carga
        $(this).replaceWith('<div style="width:200px;height:150px;background:#ccc;display:flex;align-items:center;justify-content:center;border-radius:8px;color:#666;">Imagen no encontrada</div>');
    });

    // Función para mostrar las imágenes de izquierda a derecha
    $("#mostrar").click(function(e) {
        e.preventDefault();
        console.log("Botón Mostrar clickeado");
        
        // Ocultar el botón Mostrar mientras se ejecuta la animación
        $(this).css('opacity', '0.6').css('pointer-events', 'none');
        $("#ocultar").css('opacity', '0.6').css('pointer-events', 'none');
        
        // Mostrar imágenes de izquierda a derecha con fadeIn
        $("#galeria li").each(function(index) {
            var $li = $(this);
            setTimeout(function() {
                $li.fadeIn(400); // fadeIn con duración de 400ms
            }, index * 600); // Espacio de 600ms entre cada imagen
        });
        
        // Restaurar botones después de que termine la animación
        var totalTime = ($("#galeria li").length * 600) + 400;
        setTimeout(function() {
            $("#mostrar").css('opacity', '1').css('pointer-events', 'auto');
            $("#ocultar").css('opacity', '1').css('pointer-events', 'auto');
            console.log("Animación de mostrar completada");
        }, totalTime);
    });

    // Función para ocultar las imágenes de derecha a izquierda
    $("#ocultar").click(function(e) {
        e.preventDefault();
        console.log("Botón Ocultar clickeado");
        
        // Ocultar el botón Ocultar mientras se ejecuta la animación
        $(this).css('opacity', '0.6').css('pointer-events', 'none');
        $("#mostrar").css('opacity', '0.6').css('pointer-events', 'none');
        
        // Obtener todos los elementos li en orden inverso (de derecha a izquierda)
        var $lis = $("#galeria li");
        var total = $lis.length;
        
        // Ocultar imágenes de derecha a izquierda con fadeOut
        $lis.each(function(index) {
            var $li = $(this);
            setTimeout(function() {
                $li.fadeOut(400); // fadeOut con duración de 400ms
            }, (total - 1 - index) * 600); // Espacio de 600ms entre cada imagen (orden inverso)
        });
        
        // Restaurar botones después de que termine la animación
        var totalTime = (total * 600) + 400;
        setTimeout(function() {
            $("#mostrar").css('opacity', '1').css('pointer-events', 'auto');
            $("#ocultar").css('opacity', '1').css('pointer-events', 'auto');
            console.log("Animación de ocultar completada");
        }, totalTime);
    });
});