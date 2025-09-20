
ALTER TABLE EMPLOYEES ADD (last_update_date DATE);

CREATE OR REPLACE TRIGGER update_last_update_date
BEFORE UPDATE ON EMPLOYEES
FOR EACH ROW
BEGIN
    :NEW.last_update_date := SYSDATE;
END;
/

SELECT employee_id, last_update_date
FROM EMPLOYEES
WHERE employee_id = 110;

UPDATE EMPLOYEES
SET first_name = 'Benjamin'
WHERE employee_id = 100;

SELECT employee_id, last_update_date
FROM EMPLOYEES
WHERE employee_id = 100;