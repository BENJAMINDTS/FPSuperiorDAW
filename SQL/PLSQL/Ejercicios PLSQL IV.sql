

DECLARE
    v_name employees.LAST_NAME%TYPE;
    v_emsal employees.salary%TYPE;
BEGIN
    SELECT last_name, salary INTO v_name, v_emsal
    FROM employees
    WHERE employee_id = 100;
    DBMS_OUTPUT.PUT_LINE('Employee name: ' || v_name);
    DBMS_OUTPUT.PUT_LINE('Employee salary: ' || v_emsal);

    Insert into messages (message) values ('Employee name: ' || v_name);
    Insert into messages (message) values ('Employee salary: ' || v_emsal);

    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            Insert into messages (message) values ('Employee not found');
            DBMS_OUTPUT.PUT_LINE('Employee not found');
        WHEN TOO_MANY_ROWS THEN
            Insert into messages (message) values ('More than one employee found');
            DBMS_OUTPUT.PUT_LINE('More than one employee found');
        WHEN OTHERS THEN
            Insert into messages (message) values ('An error occurred');
            DBMS_OUTPUT.PUT_LINE('An error occurred');
    

END;