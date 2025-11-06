-- INSERTS BÁSICOS PARA DEMON SLAYER API

-- Clanes
INSERT INTO clanes (nombre, region, descripcion, eliminado) VALUES
('Kamado', 'Montañas del Norte', 'Familia humilde dedicada al carbón', false),
('Agatsuma', 'Región Este', 'Clan conocido por sus nervios pero gran potencial', false),
('Hashibira', 'Bosque del Oeste', 'Familia de cazadores salvajes', false);

-- Respiraciones  
INSERT INTO respiraciones (nombre, tipo, creador, descripcion, eliminado) VALUES
('Respiración del Agua', 'Agua', 'Urokodaki Sakonji', 'Fluidez y adaptabilidad en combate', false),
('Respiración del Rayo', 'Rayo', 'Jigoro Kuwajima', 'Velocidad extrema en ataques', false),
('Respiración de la Bestia', 'Bestia', 'Hashibira Inosuke', 'Técnicas salvajes inspiradas en animales', false),
('Respiración del Sol', 'Sol', 'Yoriichi Tsugikuni', 'Respiración original y más poderosa', false);

-- Rangos
INSERT INTO rangos (nombre, nivel, descripcion, eliminado) VALUES
('Cazador Novato', 1, 'Recién ingresado al cuerpo de cazadores', false),
('Cazador Intermedio', 2, 'Con experiencia en misiones', false),
('Hashira', 3, 'Pilar o cazador de élite', false);

-- Personajes
INSERT INTO personajes (nombre, apellido, edad, genero, es_demonio, clan_id, rango_id, respiracion_id, fecha_ingreso, estado, eliminado) VALUES
('Tanjiro', 'Kamado', 15, 'Masculino', false, 1, 2, 1, '2020-01-01', 'Vivo', false),
('Nezuko', 'Kamado', 14, 'Femenino', true, 1, null, null, '2020-01-01', 'Vivo', false),
('Zenitsu', 'Agatsuma', 16, 'Masculino', false, 2, 2, 2, '2020-02-15', 'Vivo', false),
('Inosuke', 'Hashibira', 16, 'Masculino', false, 3, 2, 3, '2020-03-10', 'Vivo', false),
('Giyu', 'Tomioka', 21, 'Masculino', false, null, 3, 1, '2015-05-05', 'Vivo', false);