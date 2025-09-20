SELECT fabricante.nombre, producto.nombre
FROM fabricante 
LEFT JOIN producto
ON fabricante.codigo =producto.codigo_fabricante;

SELECT fabricante.nombre, producto.nombre
FROM fabricante 
LEFT JOIN producto
ON fabricante.codigo =producto.codigo_fabricante
WHERE producto.codigo IS NULL;