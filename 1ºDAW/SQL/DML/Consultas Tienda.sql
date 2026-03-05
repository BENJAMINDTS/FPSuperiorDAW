SELECT COUNT(*) AS 'Cantidad Total' FROM producto;
SELECT COUNT(*) AS 'Cantidad Total' FROM fabricante;
SELECT COUNT(DISTINCT codigo_fabricante) AS 'Cantidad de fabricantes' FROM producto;
SELECT AVG(precio) FROM producto;
SELECT MIN(precio) FROM producto;
SELECT MAX (precio) FROM producto;
SELECT nombre, MIN(precio) AS precio FROM producto GROUP BY nombre ORDER BY precio ASC LIMIT 1;
SELECT nombre, MAX(precio) AS precio FROM producto GROUP BY nombre ORDER BY precio desc LIMIT 1;
SELECT SUM(precio) AS suma_precios FROM producto;
SELECT COUNT(*) FROM fabricante WHERE nombre='Asus';         
SELECT AVG(precio) FROM producto WHERE codigo_fabricante=1;
SELECT MIN(precio) FROM producto WHERE codigo_fabricante=1;
SELECT max(precio) FROM producto WHERE codigo_fabricante=1;
SELECT SUM(precio) AS suma_precios FROM producto WHERE codigo_fabricante=1;
SELECT max(precio) FROM producto WHERE codigo_fabricante=2;
SELECT SUM(precio) AS suma_precios FROM producto WHERE codigo_fabricante=2;

