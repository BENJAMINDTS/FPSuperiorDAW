/*
DEFINE p_deptno = 50;

DECLARE
    
    deptno NUMBER := &p_deptno;

    CURSOR emp_cursor IS
        SELECT last_name, salary, manager_id
        FROM employees
        WHERE department_id = deptno;

BEGIN
    FOR emp_record IN emp_cursor LOOP
        IF emp_record.salary < 5000 AND (emp_record.manager_id = 101 OR emp_record.manager_id = 124) THEN
            DBMS_OUTPUT.PUT_LINE(emp_record.last_name || ' Debe recibir un aumento.');
        ELSE
            DBMS_OUTPUT.PUT_LINE(emp_record.last_name || ' No debe recibir un aumento.');
        END IF;
    END LOOP;
END;
/
*/
/*
DECLARE
    CURSOR dept_cursor IS
        SELECT department_id, department_name
        FROM departments
        WHERE department_id < 100
        ORDER BY department_id;

    CURSOR emp_cursor(p_deptno departments.department_id%TYPE) IS
        SELECT last_name, job_id, hire_date, salary
        FROM employees
        WHERE employee_id < 120 AND department_id = p_deptno;

    v_dept_id departments.department_id%TYPE;
    v_dept_name departments.department_name%TYPE;
    v_last_name employees.last_name%TYPE;
    v_job_id employees.job_id%TYPE;
    v_hire_date employees.hire_date%TYPE;
    v_salary employees.salary%TYPE;

BEGIN
    OPEN dept_cursor;
    LOOP
        FETCH dept_cursor INTO v_dept_id, v_dept_name;
        EXIT WHEN dept_cursor%NOTFOUND;
        DBMS_OUTPUT.PUT_LINE('Department ID: ' || v_dept_id || ', Department Name: ' || v_dept_name);

        OPEN emp_cursor(v_dept_id);
        LOOP
            FETCH emp_cursor INTO v_last_name, v_job_id, v_hire_date, v_salary;
            EXIT WHEN emp_cursor%NOTFOUND;
            DBMS_OUTPUT.PUT_LINE('Last Name: ' || v_last_name || ', Job ID: ' || v_job_id || ', Hire Date: ' || v_hire_date || ', Salary: ' || v_salary);
        END LOOP;
        IF emp_cursor%ISOPEN THEN
            CLOSE emp_cursor;
        END IF;
        DBMS_OUTPUT.PUT_LINE('-----------------------------');
    END LOOP;
    IF dept_cursor%ISOPEN THEN
        CLOSE dept_cursor;
    END IF;
END;
/
*/


