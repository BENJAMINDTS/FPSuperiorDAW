SELECT cliente.nombre_cliente AS 'Cliente' ,CONCAT_WS(' ',empleado.nombre,empleado.apellido1, empleado.apellido2) AS 'Empleado'
FROM cliente
JOIN empleado
ON empleado.codigo_empleado = cliente.codigo_empleado_rep_ventas;


SELECT DISTINCT cliente.codigo_cliente, cliente.nombre_cliente AS 'Cliente',
CONCAT_WS(' ',empleado.nombre,empleado.apellido1, empleado.apellido2) AS 'Empleado'
FROM cliente
JOIN empleado
ON empleado.codigo_empleado = cliente.codigo_empleado_rep_ventas
JOIN pago
ON cliente.codigo_cliente = pago.codigo_cliente;

SELECT DISTINCT cliente.codigo_cliente, cliente.nombre_cliente AS 'Cliente',
CONCAT_WS(' ',empleado.nombre,empleado.apellido1, empleado.apellido2) AS 'Empleado'
FROM cliente
JOIN empleado
ON empleado.codigo_empleado = cliente.codigo_empleado_rep_ventas
LEFT JOIN pago
ON cliente.codigo_cliente = pago.codigo_cliente
WHERE pago.codigo_cliente IS NULL;

SELECT DISTINCT oficina.ciudad, cliente.nombre_cliente AS 'Cliente',
CONCAT_WS(' ',empleado.nombre,empleado.apellido1, empleado.apellido2) AS 'Empleado'
FROM cliente
JOIN empleado
ON empleado.codigo_empleado = cliente.codigo_empleado_rep_ventas
JOIN pago
ON cliente.codigo_cliente = pago.codigo_cliente
JOIN oficina
ON oficina.codigo_oficina=empleado.codigo_oficina;

SELECT DISTINCT oficina.ciudad, cliente.nombre_cliente AS 'Cliente',
CONCAT_WS(' ', empleado.nombre, empleado.apellido1, empleado.apellido2) AS 'Empleado'
FROM cliente
JOIN empleado 
ON empleado.codigo_empleado = cliente.codigo_empleado_rep_ventas
JOIN oficina 
ON oficina.codigo_oficina = empleado.codigo_oficina
LEFT JOIN pago 
ON cliente.codigo_cliente = pago.codigo_cliente
WHERE pago.codigo_cliente IS NULL;

SELECT DISTINCT CONCAT_WS(' ',oficina.linea_direccion1, oficina.linea_direccion2) AS 'Direccion', oficina.region, oficina.pais, oficina.ciudad 
FROM oficina
JOIN empleado
ON empleado.codigo_oficina=oficina.codigo_oficina
JOIN cliente
ON cliente.codigo_empleado_rep_ventas= empleado.codigo_empleado
WHERE cliente.ciudad='Fuenlabrada';

SELECT DISTINCT cliente.nombre_cliente AS 'Cliente',
CONCAT_WS(' ',empleado.nombre,empleado.apellido1, empleado.apellido2) AS 'Empleado',
oficina.ciudad
FROM cliente
JOIN empleado
ON cliente.codigo_empleado_rep_ventas= empleado.codigo_empleado
JOIN oficina
ON empleado.codigo_oficina=oficina.codigo_oficina;

SELECT empleado.nombre AS 'Empleado', jefe.nombre AS 'Jefe'
FROM empleado
LEFT JOIN 
		(SELECT codigo_empleado, nombre, apellido1, apellido2 
     	FROM empleado) AS jefe 
ON empleado.codigo_jefe = jefe.codigo_empleado;

SELECT CONCAT(e.nombre, ' ', e.apellido1, ' ', e.apellido2) AS 'Empleado', 
CONCAT(j.nombre, ' ', j.apellido1, ' ', j.apellido2) AS 'Jefe'
FROM empleado e1 
INNER JOIN empleado j1 
ON e1.codigo_jefe = j1.codigo_empleado;
jardineria
SELECT CONCAT(e1.nombre, ' ', e1.apellido1, ' ', e1.apellido2) AS 'Empleado',
       CONCAT(j1.nombre, ' ', j1.apellido1, ' ', j1.apellido2) AS 'Jefe',
       CONCAT(j2.nombre, ' ', j2.apellido1, ' ', j2.apellido2) AS 'Jefe del Jefe'
FROM empleado e1 INNER JOIN empleado j1 ON e1.codigo_jefe = j1.codigo_empleado
                INNER JOIN empleado j2 ON j1.codigo_jefe = j2.codigo_empleado;

SELECT cliente.nombre_cliente
FROM cliente
JOIN pedido
ON cliente.codigo_cliente = pedido.codigo_cliente
WHERE pedido.fecha_entrega>pedido.fecha_esperada;


SELECT DISTINCT cliente.nombre_cliente, gama
FROM cliente
JOIN pedido
ON cliente.codigo_cliente = pedido.codigo_cliente
JOIN detalle_pedido
ON detalle_pedido.codigo_producto = detalle_pedido.codigo_producto
JOIN producto
ON detalle_pedido.codigo_producto= producto.codigo_producto;

SELECT cliente.nombre_cliente
FROM cliente
left JOIN pago
ON cliente.codigo_cliente=pago.codigo_cliente
WHERE pago.codigo_cliente IS NULL;


SELECT cliente.nombre_cliente
FROM cliente
left JOIN pedido
ON cliente.codigo_cliente=pedido.codigo_cliente
WHERE pedido.codigo_cliente IS NULL;

SELECT cliente.nombre_cliente
FROM cliente
left JOIN pedido
ON cliente.codigo_cliente=pedido.codigo_cliente
left JOIN pago
ON cliente.codigo_cliente=pago.codigo_cliente
WHERE pago.codigo_cliente IS NULL;

SELECT empleado.nombre
FROM empleado
left JOIN oficina
ON empleado.codigo_oficina=oficina.codigo_oficina
WHERE empleado.codigo_oficina IS NULL;

SELECT empleado.nombre
FROM empleado
LEFT JOIN cliente
ON empleado.codigo_empleado=cliente.codigo_empleado_rep_ventas
WHERE cliente.codigo_empleado_rep_ventas IS NULL;

SELECT empleado.nombre, oficina.*
FROM empleado
LEFT JOIN cliente
ON empleado.codigo_empleado=cliente.codigo_empleado_rep_ventas
JOIN oficina
ON oficina.codigo_oficina=empleado.codigo_oficina
WHERE cliente.codigo_empleado_rep_ventas IS NULL;

SELECT empleado.nombre
FROM empleado
left JOIN cliente
ON empleado.codigo_empleado=cliente.codigo_empleado_rep_ventas
left JOIN oficina
ON empleado.codigo_oficina=oficina.codigo_oficina
WHERE cliente.codigo_empleado_rep_ventas IS NULL AND empleado.codigo_oficina IS NULL;

SELECT producto.nombre
FROM producto
LEFT JOIN detalle_pedido
ON producto.codigo_producto=detalle_pedido.codigo_producto
WHERE detalle_pedido.codigo_producto IS NULL;

SELECT producto.nombre, producto.descripcion
FROM producto
LEFT JOIN detalle_pedido
ON producto.codigo_producto=detalle_pedido.codigo_producto
WHERE detalle_pedido.codigo_producto IS NULL;

SELECT e.nombre, consulta.jefe
FROM empleado e
LEFT JOIN cliente
ON e.codigo_empleado = cliente.codigo_empleado_rep_ventas
JOIN (
    SELECT e2.codigo_empleado, e2.nombre AS Empleado, j.nombre AS Jefe
    FROM empleado e2
    LEFT JOIN (
        SELECT codigo_empleado, nombre, apellido1, apellido2 
        FROM empleado
    ) AS j
    ON e2.codigo_jefe = j.codigo_empleado
)AS consulta
ON e.codigo_empleado = consulta.codigo_empleado
WHERE cliente.codigo_empleado_rep_ventas IS NULL;


