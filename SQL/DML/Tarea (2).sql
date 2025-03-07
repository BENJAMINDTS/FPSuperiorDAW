START TRANSACTION;
SET AUTOCOMMIT = 0;
SELECT @@AUTOCOMMIT;

INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id)
VALUES (99, 'Benjamin', 'Santiago', 'benjamin@gmail.com', 606060359, '2025-02-10', 'IT_PROG', 25000, 121, 100);

INSERT INTO employees (employee_id, first_name, last_name, email, phone_number, hire_date, job_id, salary, manager_id, department_id)
VALUES (98, 'Juan', 'Marquez', 'juan@gmail.com', 612425999, '2025-02-9', 'IT_PROG', 35000, 121, 100);

SELECT * FROM employees;

SAVEPOINT INSERTADO;

UPDATE employees
SET salary= salary-1000
WHERE salary>20000;

UPDATE employees
SET department_id = 110
WHERE last_name LIKE '%ez';

SELECT * FROM employees;

SAVEPOINT MODIFICADO;

DELETE FROM employees 
WHERE first_name='Benjamin' AND last_name='Santiago';

DELETE FROM employees 
WHERE salary>=30000 AND department_id=110;

SELECT * FROM employees;

SAVEPOINT ELIMINADO;

UPDATE employees e
SET e.salary = (
    SELECT j.max_salary
    FROM jobs j
    WHERE j.job_id = e.job_id
);

SELECT * FROM employees;

SAVEPOINT MODIFICATODO;

ROLLBACK TO SAVEPOINT MODIFICADO;

ROLLBACK TO SAVEPOINT ELIMINADO;

ROLLBACK;

COMMIT;
