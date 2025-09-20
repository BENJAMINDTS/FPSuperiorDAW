SET SERVEROUTPUT ON;
DECLARE
    v_nombre_apellidos VARCHAR2(100);
    v_salario_mensual NUMBER;
    v_salario_anual NUMBER;
    v_prima NUMBER;
BEGIN
    v_nombre_apellidos := '&nombre_apellidos';
    
    v_salario_mensual := &salario_mensual;
    
    v_salario_anual := v_salario_mensual * 12;
    
    IF v_salario_anual > 21000 THEN
        v_prima := 3000;
    ELSIF v_salario_anual BETWEEN 12000 AND 21000 THEN
        v_prima := 1800;
    ELSE
        v_prima := 1000;
    END IF;
    
    v_salario_anual := v_salario_anual + v_prima;
    
    DBMS_OUTPUT.PUT_LINE('El sueldo anual para el empleado ' || v_nombre_apellidos || 
                         ' con un sueldo mensual de ' || v_salario_mensual || 
                         ' es de ' || v_salario_anual || '.');
END;
/