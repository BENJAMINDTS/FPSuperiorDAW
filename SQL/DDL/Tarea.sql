CREATE TABLE Sucursal (
    id_sucursal NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(15),
    direccion VARCHAR2(45)
    );
CREATE TABLE Departamento (
    id_departamento NUMBER(10),
    id_sucursal NUMBER(10),
    nombre VARCHAR2(15),
    direccion VARCHAR2(45),
    PRIMARY KEY (id_departamento, id_sucursal),
    CONSTRAINT fk_dep FOREIGN KEY (id_sucursal) 
    REFERENCES Sucursal(id_sucursal)
    );
CREATE TABLE Comercial (
    dni VARCHAR2(9) PRIMARY KEY,
    nombre VARCHAR2(15),
    apellido VARCHAR2(15),
    fecha_nacimiento DATE,
    bonificacion NUMBER(5) CHECK(bonificacion>= 0),
    titulacion VARCHAR2(25) NOT NULL,
    id_departamento NUMBER(10),
    id_sucursal NUMBER(10),
     CONSTRAINT fk_comercial_dep FOREIGN KEY (id_departamento, id_sucursal) 
     REFERENCES Departamento(id_departamento, id_sucursal)
    );
CREATE TABLE Programador (
    dni VARCHAR2(9) PRIMARY KEY,
    nombre VARCHAR2(15),
    apellido VARCHAR2(15),
    fecha_nacimiento DATE,
    lenguajes VARCHAR2(25) NOT NULL,
    puesto VARCHAR2(25),
    id_departamento NUMBER(10),
    id_sucursal NUMBER(10),
    CONSTRAINT fk_programador_dep FOREIGN KEY (id_departamento, id_sucursal) 
    REFERENCES Departamento(id_departamento, id_sucursal)
    );
CREATE TABLE Proyectos (
    cod_proyecto NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(15),
    descripcion VARCHAR2(50),
    id_departamento NUMBER(10),
    id_sucursal NUMBER(10),
    CONSTRAINT fk_proyectos_dep FOREIGN KEY (id_departamento, id_sucursal) 
    REFERENCES Departamento(id_departamento, id_sucursal)
    );
CREATE TABLE Cliente (
    id_cliente NUMBER(10) PRIMARY KEY,
    nombre VARCHAR2(15),
    tlf VARCHAR2(15) NOT NULL,
    direccion VARCHAR2(45),
    email VARCHAR2(30));
CREATE TABLE Proyectos_Cliente (
    cod_proyecto NUMBER(10),
    id_cliente NUMBER(10),
    PRIMARY KEY(cod_proyecto, id_cliente),
    CONSTRAINT fk_proyectos_cliente FOREIGN KEY (cod_proyecto) 
    REFERENCES Proyectos(cod_proyecto),
    CONSTRAINT fk_cliente_proyecto FOREIGN KEY (id_cliente) 
    REFERENCES Cliente(id_cliente)
    );
CREATE VIEW Vista_Cliente AS
SELECT 
    nombre,
    tlf,
    direccion,
    email
FROM Cliente;

CREATE USER Practica3 IDENTIFIED BY FOC;
GRANT SELECT, INSERT ON Comercial TO Practica3;