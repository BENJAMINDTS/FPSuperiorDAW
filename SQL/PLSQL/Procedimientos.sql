create or replace PROCEDURE imprimirCadenaAlReves(p_cadena IN VARCHAR2) IS
    v_cadena_reves VARCHAR2(32767);
BEGIN
    FOR i IN REVERSE 1..LENGTH(p_cadena) LOOP
        v_cadena_reves := v_cadena_reves || SUBSTR(p_cadena, i, 1);
    END LOOP;
    DBMS_OUTPUT.PUT_LINE(v_cadena_reves);
END;

create or replace PROCEDURE cambiarLocalidadDept(p_id_departamento IN NUMBER, id_localidad IN VARCHAR2) IS
BEGIN
    UPDATE DEPARTMENTS SET DEPARTMENTS.LOCATION_ID = id_localidad WHERE DEPARTMENT_ID = p_id_departamento;
    COMMIT;
END;

create or replace PROCEDURE borrarEmpleado(p_id_empleado IN NUMBER) IS
BEGIN
    DELETE FROM EMPLOYEES WHERE EMPLOYEE_ID = p_id_empleado;
    COMMIT;
END;