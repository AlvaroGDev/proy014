-- Tabla Plaza
CREATE TABLE plaza (
    id BIGSERIAL PRIMARY KEY,
    maximo_plazas BIGINT,
    sector VARCHAR(10),
    ocupada BOOLEAN DEFAULT FALSE,
    direccion VARCHAR(255)
);

-- Tabla Vehiculo
CREATE TABLE vehiculo (
    id BIGSERIAL PRIMARY KEY,
    matricula VARCHAR(20),
    modelo VARCHAR(100),
    color VARCHAR(50),
    aparcado BOOLEAN DEFAULT FALSE,
    plaza_id BIGINT REFERENCES plaza(id)
);

-- Tabla Multa
CREATE TABLE multa (
    id BIGSERIAL PRIMARY KEY,
    razon_multa VARCHAR(255),
    importe NUMERIC(10,2),
    fecha VARCHAR(20),
    vehiculo_id BIGINT REFERENCES vehiculo(id)
);
