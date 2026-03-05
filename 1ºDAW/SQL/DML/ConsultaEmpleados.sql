SELECT empleado.*
FROM empleado
JOIN departamento 
ON empleado.id_departamento = departamento.id;

SELECT empleado.*
FROM empleado
JOIN departamento 
ON empleado.id_departamento = departamento.id
ORDER BY departamento.nombre, apellido1, empleado.nombre;

SELECT DISTINCT departamento.id, departamento.nombre
FROM departamento
JOIN empleado
ON (empleado.id_departamento = departamento.id);

SELECT DISTINCT departamento.id, departamento.nombre, (presupuesto - gastos) AS 'Presupuesto actual'
FROM departamento
JOIN empleado
ON (empleado.id_departamento = departamento.id);

SELECT departamento.nombre 
FROM departamento 
JOIN empleado
ON empleado.id_departamento= departamento.id
WHERE empleado.nif='38382980M'; 

SELECT departamento.nombre 
FROM departamento 
JOIN empleado
ON empleado.id_departamento= departamento.id
WHERE CONCAT_WS(' ' ,empleado.nombre, apellido1, apellido2)= 'Pepe Ruiz Santana';

SELECT empleado.*
FROM empleado
JOIN departamento
ON empleado.id_departamento = departamento.id
WHERE departamento.nombre ='I+D' 
ORDER BY empleado.nombre, apellido1;

SELECT empleado.*
FROM empleado
JOIN departamento
ON empleado.id_departamento = departamento.id
WHERE departamento.nombre 
IN('I+D', 'sistemas', 'contabilidad')  
ORDER BY empleado.nombre, apellido1;

SELECT empleado.*
FROM empleado
JOIN departamento
ON empleado.id_departamento = departamento.id
WHERE departamento.presupuesto 
NOT BETWEEN 100000 AND 200000 
ORDER BY empleado.nombre, apellido1;

SELECT departamento.nombre
FROM empleado
JOIN departamento
ON empleado.id_departamento = departamento.id
WHERE apellido2 IS NULL;

