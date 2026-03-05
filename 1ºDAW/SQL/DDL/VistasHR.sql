Create view visual ompvu60 as select employee_id, last_name, salary
from employees 
when departament_id=60;
describe ompvu60;

Create view visual empvu60 as select employee_id, last_name, salary
from employees 
when departament_id=60;
describe empvu60;

Create view visual empvu80 as select employee_id, last_name, salary
from employees 
when departament_id=60;
describe empvu80;

Create view empvu60 as select employee_id, last_name, salary
from employees 
when departament_id=60;
describe empvu60;

Create view empvu60 as select employee_id, last_name, salary
from employees 
when department_id=60;
describe empvu60;

Create view empvu60 as select employee_id, last_name, salary
from employees 
where department_id=60;
describe empvu60;

CREATE VIEW salvu50
as SELECT employee_id ID_NUMBER, last_name NAME,
salary*12 ANN_SALARY
from employees
where department_id=50;

CREATE OR REPLACE VIEW empvu60
AS SELECT employee_id, last_name, salary
FROM employees
WHERE department_id=60
WITH READ ONLY;

DROP VIEW empvu60;

CREATE VIEW EMPLOYEES_VU as SELECT
EMPLOYEE_ID, FIRST_NAME, DEPARTMENT_ID
FROM EMPLOYEES;

CREATE VIEW DEPT50 as SELECT
EMPLOYEE_ID EMPNO, LAST_NAME EMPLOYEE, DEPARTMENT_ID DEPTNO
FROM EMPLOYEES;

CREATE VIEW DEPT50 as SELECT
EMPLOYEE_ID EMPNO, LAST_NAME EMPLOYEE, DEPARTMENT_ID DEPTNO
FROM EMPLOYEES WHERE department_id=50
WITH CHECK OPTION;

