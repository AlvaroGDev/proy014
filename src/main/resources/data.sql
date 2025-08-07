-- Inserta dos garajes
INSERT INTO garaje (id, direccion, capacidad_maxima, telefono, propietario) VALUES (1, 'Calle Mayor 99', 10, '600111111', 'Juan Pérez');
INSERT INTO garaje (id, direccion, capacidad_maxima, telefono, propietario) VALUES (2, 'Avenida Central 2', 8, '600222222', 'Ana Gómez');

-- Inserta cinco coches para cada garaje
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (1, '1111AAA', 'Seat Ibiza', 'Rojo', 1);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (2, '2222BBB', 'Renault Clio', 'Azul', 1);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (3, '3333CCC', 'Ford Fiesta', 'Verde', 1);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (4, '4444DDD', 'VW Polo', 'Negro', 1);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (5, '5555EEE', 'Opel Corsa', 'Blanco', 1);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (6, '6666FFF', 'Seat León', 'Gris', 2);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (7, '7777GGG', 'Peugeot 208', 'Rojo', 2);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (8, '8888HHH', 'Citroen C3', 'Azul', 2);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (9, '9999III', 'Toyota Yaris', 'Verde', 2);
INSERT INTO coche (num_plaza, matricula, modelo, color, garaje_id) VALUES (10, '0000JJJ', 'Hyundai i20', 'Negro', 2);

-- Multas para coches de garaje 1
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa leve', 50.0, '2025-08-07', 1);
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa leve', 50.0, '2025-08-07', 2);
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa grave', 150.0, '2025-08-07', 3);
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa muy grave', 300.0, '2025-08-07', 3);

-- Multas para coches de garaje 2
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa leve', 50.0, '2025-08-07', 6);
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa leve', 50.0, '2025-08-07', 7);
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa grave', 150.0, '2025-08-07', 8);
INSERT INTO multa (razon_multa, importe, fecha, coche_num_plaza) VALUES ('Multa muy grave', 300.0, '2025-08-07', 8);
