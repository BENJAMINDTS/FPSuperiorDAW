SELECT cliente.nombre_cliente
FROM cliente 
WHERE cliente.limite_credito = (SELECT MAX(cliente.limite_credito)           
                          FROM cliente);


SELECT producto.nombre
FROM producto 
WHERE producto.precio_venta = (SELECT MAX(producto.precio_venta) 
                        FROM producto);
            
				            
SELECT producto.nombre
FROM producto 
INNER JOIN detalle_pedido ON producto.codigo_producto = detalle_pedido.codigo_producto
WHERE detalle_pedido.cantidad = (SELECT MAX(detalle_pedido.cantidad)
                     FROM detalle_pedido);


SELECT cliente.Nombre_cliente, cliente.Limite_credito, p.Total
FROM cliente, pago p 
WHERE p.total = (SELECT MAX(p2.total)
                 FROM pago p2
                 WHERE cliente.limite_credito > p2.total);


SELECT producto.nombre
FROM producto
WHERE producto.cantidad_en_stock = (SELECT MAX(producto.cantidad_en_stock) 
                             FROM producto);


SELECT producto.nombre
FROM producto
WHERE producto.cantidad_en_stock = (SELECT MIN(producto.cantidad_en_stock) 
                             FROM producto);


SELECT CONCAT_WS(' ',empleado.nombre, empleado.apellido1, empleado.apellido2) AS 'Empleados a cargo de Alberto Soria'
FROM empleado 
WHERE empleado.codigo_jefe = (SELECT empleado.codigo_empleado 
                       FROM empleado 
                       WHERE empleado.nombre = 'Alberto' AND empleado.apellido1 = 'Soria');
 
                       
SELECT c.nombre_cliente
FROM cliente
WHERE cliente.limite_credito >= ALL(SELECT MAX(cliente.limite_credito) 
                              FROM cliente);


SELECT producto.nombre
FROM producto 
WHERE producto.precio_venta >= ALL(SELECT MAX(producto.precio_venta)
                            FROM producto);


SELECT producto.nombre
FROM producto
WHERE producto.cantidad_en_stock <= ALL(SELECT MIN(producto.cantidad_en_stock)
                                 FROM producto);
                                 

SELECT empleado.nombre, empleado.apellido1, empleado.apellido2
FROM empleado
WHERE empleado.codigo_empleado NOT IN (SELECT cliente.codigo_empleado_rep_ventas
                                FROM cliente);


SELECT cliente.nombre_cliente
FROM cliente
WHERE cliente.codigo_cliente NOT IN (SELECT pago.codigo_cliente 
                               FROM pago);


SELECT cliente.nombre_cliente
FROM cliente
WHERE cliente.codigo_cliente IN (SELECT pago.codigo_cliente 
                           FROM pago);


SELECT producto.nombre
FROM producto
WHERE producto.codigo_producto NOT IN (SELECT detalle_pedido.codigo_producto 
                                FROM detalle_pedido);


SELECT empleado.nombre, empleado.apellido1, empleado.apellido2, empleado.puesto, oficina.telefono
FROM empleado INNER JOIN oficina ON empleado.codigo_oficina = oficina.codigo_oficina
WHERE empleado.codigo_empleado NOT IN (SELECT cliente.codigo_empleado_rep_ventas 
                                FROM cliente);


SELECT *
FROM oficina
WHERE oficina.codigo_oficina NOT IN 
        (SELECT DISTINCT oficina.codigo_oficina 
         	FROM oficina INNER JOIN empleado USING(codigo_oficina)
                  		INNER JOIN cliente ON cliente.codigo_empleado_rep_ventas = empleado.codigo_empleado);


SELECT cliente.nombre_cliente
FROM cliente 
WHERE cliente.codigo_cliente IN (SELECT pedido.codigo_cliente 
                           FROM pedido)  AND 
      cliente.codigo_cliente NOT IN (SELECT pago.codigo_cliente
                               FROM pago); 
   
   
SELECT cliente.nombre_cliente
FROM cliente
WHERE NOT EXISTS (SELECT *
                  FROM pago 
                  WHERE cliente.codigo_cliente = pago.codigo_cliente);
                  

SELECT cliente.nombre_cliente
FROM cliente
WHERE EXISTS (SELECT *
              FROM pago
              WHERE cliente.codigo_cliente = pago.codigo_cliente);
              

SELECT producto.nombre
FROM producto
WHERE NOT EXISTS (
    SELECT *
    FROM detalle_pedido
    WHERE detalle_pedido.codigo_producto = producto.codigo_producto);
              

SELECT producto.nombre
FROM producto
WHERE EXISTS (
    SELECT *
    FROM detalle_pedido
    WHERE detalle_pedido.codigo_producto = producto.codigo_producto);


