SELECT first_name as "Nombre", last_name as "Apellido", salary as "Salario"
from employees;

SELECT first_name as "Nombre", last_name as "Apellido", salary as "Salario"
from employees
ORDER BY last_name;

SELECT last_name as "Apellido", commission_pct as "Comision"
FROM employees
where commission_pct >0.25
ORDER BY last_name DESC;

SELECT COUNT(employee_id) as "Cantidad"
from employees
where department_id =100;

SELECT first_name as "Nombre"
FROM employees
WHERE department_id = 110
AND salary = (SELECT MAX(salary) FROM employees WHERE department_id = 110)
ORDER BY salary ASC;

----------------------------------------------------------------------------------------------------------------

SELECT job_id || ', ' || first_name || ', '|| last_name as "Trabajadores por puesto"
from employees;

SELECT first_name as "Nombre", last_name as "Apellido"
from employees
WHERE department_id=80
AND first_name like 'A%';

SELECT last_name as "Apellido", salary as "Salario"
from employees 
where salary BETWEEN 7500 and 12000;

SELECT max(salary) as "Maximo", MIN(salary) as "Minimo", SUM(salary) as "Suma", round(AVG(salary),2) as "Media"
from employees;

SELECT COUNT(DISTINCT manager_id) AS "Numero de Jefes"
FROM employees;

SELECT department_id, COUNT(*) AS "Numero de Empleados"
FROM employees
GROUP BY department_id;

----------------------------------------------------------------------------------------------------------------
