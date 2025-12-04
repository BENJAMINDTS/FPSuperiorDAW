$(document).ready(function() {
    // 1. Ocultar todos los párrafos al hacer clic
    $("#hide-paragraphs").click(function() {
        $(".container:nth-child(2) p").hide();
    });
    
    // Mostrar párrafos nuevamente
    $("#show-paragraphs").click(function() {
        $(".container:nth-child(2) p").show();
    });
    
    // 2. Mostrar imagen al pasar el ratón sobre el texto
    $("#hover-text").hover(
        function() {
            $("#hover-image").fadeIn(500);
        },
        function() {
            $("#hover-image").fadeOut(500);
        }
    );
    
    // 3. Cambiar color de fondo a aleatorio
    $("#random-color").click(function() {
        // Generar un color hexadecimal aleatorio
        const randomColor = "#" + Math.floor(Math.random()*16777215).toString(16);
        $("body").css("background", randomColor);
    });
    
    // 4. Mostrar alerta con contenido del campo de texto
    $("#show-text").click(function() {
        const textValue = $("#text-input").val();
        if (textValue.trim() === "") {
            alert("Por favor, escribe algo en el campo de texto.");
        } else {
            alert("El texto ingresado es: " + textValue);
        }
    });
    
    // 5. Cambiar color de fondo con botones específicos
    $("#red-btn").click(function() {
        $("body").css("background", "#ff0000ff");
    });
    
    $("#blue-btn").click(function() {
        $("body").css("background", "#0011ffff");
    });
    
    $("#green-btn").click(function() {
        $("body").css("background", "#00ff15ff");
    });
    
    $("#reset-color").click(function() {
        $("body").css("background", "linear-gradient(135deg, #667eea 0%, #764ba2 100%)");
    });

    // 6. Cambiar color de fondo al pasar el ratón sobre el texto
    $("#color-hover-text").hover(
        function() {
            const randomColor = "#" + Math.floor(Math.random()*16777215).toString(16);
            $(this).css("background-color", randomColor); // Color al pasar el ratón
        },
        function() {
            $(this).css("background-color", ""); // Restaurar color original
        }
    );

    //Ocultar un elemnto li
    $("#hide-li").hover(function() {
        $("li:nth-child(3)").hide(10000);
        //recupearar el elemento li
    }, function() {
        $("li:nth-child(3)").show(10000);
    });
});