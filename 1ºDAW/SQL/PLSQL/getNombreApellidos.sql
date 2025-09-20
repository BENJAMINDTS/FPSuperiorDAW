CREATE OR REPLACE FUNCTION getNombreApellidos(p_employee_id IN NUMBER) RETURN VARCHAR2 IS
    v_nombre EMPLOYEES.FIRST_NAME%TYPE;
    v_apellido EMPLOYEES.LAST_NAME%TYPE;
    v_nombrecompleto VARCHAR2(100);
    e_falta_empleado EXCEPTION;
BEGIN

    SELECT first_name, last_name INTO v_nombre, v_apellido
    FROM EMPLOYEES
    WHERE employee_id = p_employee_id;

    v_nombrecompleto := v_nombre || ' ' || v_apellido;
    RETURN v_nombrecompleto;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        RETURN 'No existe un empleado con id ' || p_employee_id;
END getNombreApellidos;
/

DECLARE
    v_resultado VARCHAR2(100);
    
BEGIN

    v_resultado := getNombreApellidos(105);
    DBMS_OUTPUT.PUT_LINE('Resultado para id: 105 ' || v_resultado);

    v_resultado := getNombreApellidos(201);
    DBMS_OUTPUT.PUT_LINE('Resultado para id 201: ' || v_resultado);

    v_resultado := getNombreApellidos(1111);
    DBMS_OUTPUT.PUT_LINE('Resultado para id 1111: ' || v_resultado);

    FOR r IN (SELECT employee_id FROM EMPLOYEES WHERE department_id = (SELECT department_id FROM DEPARTMENTS WHERE department_name = 'Finance')) LOOP
        v_resultado := getNombreApellidos(r.employee_id);
        DBMS_OUTPUT.PUT_LINE('Resultado para id ' || r.employee_id || ': ' || v_resultado);
    END LOOP;
END;
/