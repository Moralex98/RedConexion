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
    id_gran INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE productliter (
    id_lit VARCHAR(25) PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    stock INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE detalle_ventas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    venta_id INT NOT NULL,
    id_lit VARCHAR(25) NOT NULL,
    cantidad INT NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (venta_id) REFERENCES ventas(id) ON DELETE CASCADE,
    FOREIGN KEY (id_lit) REFERENCES productliter(id_lit) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
