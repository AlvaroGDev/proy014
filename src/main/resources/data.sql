-- Plazas
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (1, 5, 'A_1', 'Calle A1');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (2, 5, 'A_1', 'Calle A1 bis');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (3, 5, 'A_2', 'Calle A2');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (4, 5, 'A_2', 'Calle A2 bis');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (5, 5, 'B_1', 'Calle B1');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (6, 5, 'B_1', 'Calle B1 bis');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (7, 5, 'B_2', 'Calle B2');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (8, 5, 'B_2', 'Calle B2 bis');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (9, 5, 'C_1', 'Calle C1');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (10, 5, 'C_1', 'Calle C1 bis');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (11, 5, 'C_2', 'Calle C2');
INSERT INTO plaza (id, maximo_plazas, sector, direccion) VALUES (12, 5, 'C_2', 'Calle C2 bis');

-- Vehiculos

INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (1, 'A1-AAA', 'Seat Ibiza', 'Rojo', 1);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (2, 'A1-BBB', 'Renault Clio', 'Azul', 1);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (3, 'A1bis-CCC', 'Ford Fiesta', 'Verde', 2);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (4, 'A1bis-DDD', 'VW Polo', 'Negro', 2);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (5, 'A2-EEE', 'Opel Corsa', 'Blanco', 3);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (6, 'A2-FFF', 'Seat León', 'Gris', 3);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (7, 'A2bis-GGG', 'Peugeot 208', 'Rojo', 4);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (8, 'A2bis-HHH', 'Citroen C3', 'Azul', 4);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (9, 'B1-III', 'Toyota Yaris', 'Verde', 5);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (10, 'B1-JJJ', 'Hyundai i20', 'Negro', 5);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (11, 'B1bis-KKK', 'Seat Ibiza', 'Rojo', 6);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (12, 'B1bis-LLL', 'Renault Clio', 'Azul', 6);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (13, 'B2-MMM', 'Ford Fiesta', 'Verde', 7);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (14, 'B2-NNN', 'VW Polo', 'Negro', 7);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (15, 'B2bis-OOO', 'Opel Corsa', 'Blanco', 8);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (16, 'B2bis-PPP', 'Seat León', 'Gris', 8);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (17, 'C1-QQQ', 'Peugeot 208', 'Rojo', 9);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (18, 'C1-RRR', 'Citroen C3', 'Azul', 9);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (19, 'C1bis-SSS', 'Toyota Yaris', 'Verde', 10);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (20, 'C1bis-TTT', 'Hyundai i20', 'Negro', 10);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (21, 'C2-UUU', 'Seat Ibiza', 'Rojo', 11);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (22, 'C2-VVV', 'Renault Clio', 'Azul', 11);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (23, 'C2bis-WWW', 'Ford Fiesta', 'Verde', 12);
INSERT INTO vehiculo (id, matricula, modelo, color, plaza_id) VALUES (24, 'C2bis-XXX', 'VW Polo', 'Negro', 12);
