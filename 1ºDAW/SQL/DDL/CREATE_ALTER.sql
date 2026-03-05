CREATE TABLE Profesor (
dni VARCHAR2 (9) PRIMARY KEY,
nombre VARCHAR2(30));

CREATE TABLE Departamento (
id_departamento NUMBER (10) PRIMARY KEY,
nombre VARCHAR2(30));

CREATE TABLE Facultad (
id_facultad NUMBER (10) PRIMARY KEY,
nombre VARCHAR2(30));

CREATE TABLE Asignatura (
cod_asignatura NUMBER (10) PRIMARY KEY,
nombre VARCHAR2(30));

CREATE TABLE Alumno (
id_alumno NUMBER (10) PRIMARY KEY,
nombre VARCHAR2(30));

Alter TABLE Profesor add
id_departamento NUMBER(10);

Alter TABLE Profesor add
id_facultad NUMBER(10);

ALTER TABLE Profesor
ADD CONSTRAINT fk_id_departamento FOREIGN KEY (id_departamento)
REFERENCES Departamento (id_departamento);

alter table departamento add id_facultad NUMBER(10);
ALTER TABLE departamento 
ADD CONSTRAINT fk_departamento_facultad 
FOREIGN KEY (id_facultad) 
REFERENCES Facultad (id_facultad);

alter TABLE Profesor DROP COLUMN id_departamento;

ALTER TABLE departamento 
DROP CONSTRAINT SYS_C0010556;

alter table Profesor add id_departamento NUMBER(10);
alter TABLE Profesor 
add CONSTRAINT fk_id_departamento
FOREIGN KEY (id_departamento) 
REFERENCES Departamento (id_departamento);

alter table Departamento
add CONSTRAINT pk_id_departamento_id_facultad
PRIMARY KEY (id_departamento, id_facultad);

alter table Departamento
DROP CONSTRAINT pk_id_departamento_id_facultad;

alter table Departamento
add CONSTRAINT pk_id_departamento
PRIMARY KEY (id_departamento);

alter TABLE Profesor 
add CONSTRAINT fk_id_departamento
FOREIGN KEY (id_departamento) 
REFERENCES Departamento (id_departamento);

alter TABLE Asignatura add id_departamento NUMBER(10);

alter TABLE Asignatura 
add CONSTRAINT fk_id_departamento_to_asignatura
FOREIGN KEY (id_departamento) 
REFERENCES Departamento (id_departamento);

create table Matricula (
COD_asignatura number(10), 
id_alumno number (10)

)

ALTER TABLE Matricula 
ADD CONSTRAINT pk_matricula 
PRIMARY KEY (cod_asignatura, id_alumno);

CREATE TABLE examen(
nota NUMBER(2) CONSTRAINT nn_examen NOT NULL,
dni VARCHAR2(9) CONSTRAINT pk_examen PRIMARY KEY,
fecha DATE DEFAULT SYSDATE
);

ALTER TABLE EXAMEN add (
First_Name VARCHAR2(40)
);


