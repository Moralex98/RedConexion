CREATE DATABASE redconexion;

USE redconexion; 

SELECT DATABASE();


CREATE TABLE usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    rol VARCHAR(50) NOT NULL,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

--encriptar en codigo con sha2 no es muy buena
INSERT INTO usuarios (username, password_hash, rol) 
VALUES ('admin', '$2a$12$w5JyVO9cgC6eu/zeFhmuqOrHlDnP67TvrO85TCNvlv8GcODztROru', 'admin');

--redconexion: contraseña real

CREATE TABLE productgranel (
    id_gran VARCHAR(25) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE productlit (
    id_lit VARCHAR(25) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    total DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE detalle_ventas (
    venta_id INT NOT NULL,
    id INT NOT NULL AUTO_INCREMENT,  -- AUTO_INCREMENT como clave primaria
    id_lit VARCHAR(25) NOT NULL,
    cantidad INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES ventas(id) ON DELETE CASCADE,
    FOREIGN KEY (id_lit) REFERENCES productlit(id_lit) ON DELETE CASCADE,
    PRIMARY KEY (id),  -- Clave primaria simple
    UNIQUE KEY (venta_id, id)  -- Restricción única para garantizar unicidad
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
