INSERT INTO oficina (codigo_oficina, ciudad, pais, region, codigo_postal, telefono, linea_direccion1, linea_direccion2) 
VALUE ('AL-ES', 'Almeria', 'España', 'Almeria', 04001, 986457123, 'AV. del Mediterraneo, 6', '5ºB' );

INSERT INTO empleado (codigo_empleado, nombre, apellido1, apellido2, extension, email, codigo_oficina, codigo_jefe, puesto)
VALUE (32, 'Juan', 'Castillo', 'Historia' , 1578, 'juanitoc@gmail.com', 'AL-ES', 7, 'Representante Ventas');

INSERT INTO cliente (codigo_cliente, nombre_cliente, nombre_contacto, apellido_contacto, telefono, fax, linea_direccion1, linea_direccion2, ciudad, region, pais, codigo_postal, codigo_empleado_rep_ventas, limite_credito)
VALUE (39, 'Juan', 'Juanito' , 'Golosina', 666888999, '916549870', 'AV. Andalucia, 99',  '5ºB', 'Granada', 'Granada', 'España', 18009, 32, 5000);

