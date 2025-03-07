SELECT nif, CONCAT_WS(' ',empleado.nombre, apellido1, apellido2) AS 'Empleado', departamento.*
FROM empleado
LEFT JOIN departamento ON departamento.id = id_departamento;

SELECT CONCAT_WS(' ',empleado.nombre, apellido1, apellido2) AS 'Empleado'
FROM empleado
LEFT JOIN departamento ON departamento.id = id_departamento
WHERE id_departamento IS NULL;


