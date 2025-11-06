-- ============================================================
-- DATOS INICIALES PARA DEMON SLAYER
-- ============================================================

-- Insertar Clanes
INSERT INTO clanes (nombre, region, descripcion, eliminado) VALUES
('Kamado', 'Montañas del Norte', 'Familia humilde dedicada al carbón', false),
('Agatsuma', 'Región Este', 'Clan conocido por sus nervios pero gran potencial', false),
('Hashibira', 'Bosque del Oeste', 'Familia de cazadores salvajes', false),
('Tomioka', 'Región Central', 'Clan de cazadores de élite', false),
('Uzui', 'Región del Sonido', 'Clan de ninjas con técnicas llamativas', false);

-- Insertar Respiraciones
INSERT INTO respiraciones (nombre, tipo, creador, descripcion, eliminado) VALUES
('Respiración del Agua', 'Agua', 'Urokodaki Sakonji', 'Fluidez y adaptabilidad en combate', false),
('Respiración del Rayo', 'Rayo', 'Jigoro Kuwajima', 'Velocidad extrema en ataques', false),
('Respiración de la Bestia', 'Bestia', 'Hashibira Inosuke', 'Técnicas salvajes inspiradas en animales', false),
('Respiración del Sol', 'Sol', 'Yoriichi Tsugikuni', 'Respiración original y más poderosa', false),
('Respiración del Sonido', 'Sonido', 'Tengen Uzui', 'Técnicas basadas en ritmo y explosiones', false),
('Respiración de la Llama', 'Fuego', 'Kyojuro Rengoku', 'Técnicas ardientes y poderosas', false);

-- Insertar Rangos
INSERT INTO rangos (nombre, nivel, descripcion, eliminado) VALUES
('Cazador Novato', 1, 'Recién ingresado al cuerpo de cazadores', false),
('Cazador Intermedio', 2, 'Con experiencia en misiones', false),
('Cazador Avanzado', 3, 'Cazador experimentado con múltiples misiones', false),
('Hashira', 4, 'Pilar o cazador de élite', false),
('Ex-Hashira', 3, 'Antiguo pilar retirado', false);

-- Insertar Personajes
INSERT INTO personajes (nombre, apellido, edad, genero, es_demonio, clan_id, rango_id, respiracion_id, fecha_ingreso, estado, eliminado) VALUES
('Tanjiro', 'Kamado', 15, 'Masculino', false, 1, 2, 1, '2020-01-01', 'Vivo', false),
('Nezuko', 'Kamado', 14, 'Femenino', true, 1, null, null, '2020-01-01', 'Vivo', false),
('Zenitsu', 'Agatsuma', 16, 'Masculino', false, 2, 2, 2, '2020-02-15', 'Vivo', false),
('Inosuke', 'Hashibira', 16, 'Masculino', false, 3, 2, 3, '2020-03-10', 'Vivo', false),
('Giyu', 'Tomioka', 21, 'Masculino', false, 4, 4, 1, '2015-05-05', 'Vivo', false),
('Kyojuro', 'Rengoku', 20, 'Masculino', false, null, 4, 6, '2016-08-12', 'Muerto', false),
('Tengen', 'Uzui', 23, 'Masculino', false, 5, 5, 5, '2014-03-20', 'Retirado', false),
('Kanao', 'Tsuyuri', 16, 'Femenino', false, null, 2, 1, '2019-06-10', 'Vivo', false);

-- Insertar Demonios
INSERT INTO demonios (nombre, rango_luna, poder_especial, creador, nivel_peligro, eliminado) VALUES
('Muzan Kibutsuji', 'Luna Superior 0', 'Transformar humanos en demonios', 'N/A', 10, false),
('Kokushibo', 'Luna Superior 1', 'Respiración de la Luna', 'Muzan Kibutsuji', 10, false),
('Doma', 'Luna Superior 2', 'Técnicas de hielo', 'Muzan Kibutsuji', 9, false),
('Akaza', 'Luna Superior 3', 'Arte de Destrucción Total', 'Muzan Kibutsuji', 9, false),
('Hantengu', 'Luna Superior 4', 'División en múltiples personalidades', 'Muzan Kibutsuji', 8, false),
('Gyokko', 'Luna Superior 5', 'Manipulación de jarrones', 'Muzan Kibutsuji', 8, false),
('Daki', 'Luna Superior 6', 'Cinturones demoníacos', 'Muzan Kibutsuji', 7, false),
('Gyutaro', 'Luna Superior 6', 'Veneno letal', 'Muzan Kibutsuji', 8, false);

-- Insertar Misiones
INSERT INTO misiones (nombre, ubicacion, nivel_dificultad, descripcion, fecha_inicio, fecha_fin, eliminado) VALUES
('Misión del Monte Natagumo', 'Monte Natagumo', 7, 'Derrotar a la familia de demonios araña', '2020-06-01', '2020-06-05', false),
('Misión del Tren Infinito', 'Tren Mugen', 8, 'Investigar desapariciones misteriosas en el tren', '2021-01-10', '2021-01-13', false),
('Misión del Distrito Rojo', 'Distrito de Entretenimiento Yoshiwara', 9, 'Infiltrarse y eliminar demonios en el distrito', '2021-06-15', '2021-06-20', false),
('Misión de la Villa del Herrero', 'Villa de los Herreros', 6, 'Proteger la villa de ataques demoníacos', '2021-09-01', '2021-09-10', false);

-- Insertar Participaciones en Misiones
INSERT INTO misiones_personajes (personaje_id, mision_id, rol, resultado) VALUES
(1, 1, 'Líder', 'Éxito'),
(2, 1, 'Apoyo', 'Éxito'),
(3, 1, 'Apoyo', 'Éxito'),
(4, 1, 'Apoyo', 'Éxito'),
(1, 2, 'Apoyo', 'Éxito'),
(3, 2, 'Apoyo', 'Éxito'),
(6, 2, 'Líder', 'Sacrificio'),
(1, 3, 'Apoyo', 'Éxito'),
(3, 3, 'Apoyo', 'Éxito'),
(4, 3, 'Apoyo', 'Éxito'),
(7, 3, 'Líder', 'Éxito');

-- Insertar Peleas
INSERT INTO peleas (cazador_id, demonio_id, lugar, fecha, resultado, eliminado) VALUES
(1, 8, 'Monte Natagumo', '2020-06-03', 'Victoria', false),
(1, 4, 'Tren Infinito', '2021-01-12', 'Victoria', false),
(6, 4, 'Tren Infinito', '2021-01-12', 'Derrota', false),
(5, 4, 'Tren Infinito', '2021-01-12', 'Victoria', false),
(7, 7, 'Distrito Yoshiwara', '2021-06-18', 'Victoria', false),
(7, 8, 'Distrito Yoshiwara', '2021-06-18', 'Victoria', false);

-- Insertar Técnicas
INSERT INTO tecnicas (nombre, respiracion_id, descripcion, nivel_dificultad, eliminado) VALUES
('Primera Forma: Corte de Agua Superficial', 1, 'Ataque fluido que corta limpiamente', 3, false),
('Segunda Forma: Rueda de Agua', 1, 'Ataque giratorio vertical', 4, false),
('Sexta Forma: Torbellino de Agua', 1, 'Ataque giratorio de alta presión', 5, false),
('Décima Forma: Dragón de Cambio Constante', 1, 'Técnica fluida y adaptable', 7, false),
('Primera Forma: Flash de Dios del Trueno', 2, 'Ataque de velocidad extrema en línea recta', 7, false),
('Segunda Forma: Garra de Arroz del Rayo', 2, 'Múltiples cortes rápidos', 6, false),
('Primera Forma: Corte Desgarrador', 3, 'Ataque salvaje con doble espada', 4, false),
('Segunda Forma: Corte Desgarrador Extendido', 3, 'Versión extendida del primer corte', 5, false),
('Primera Forma: Disco Solar Ardiente', 4, 'Corte circular ardiente', 8, false),
('Décima Forma: Danza del Dragón Solar', 4, 'Técnica suprema de la respiración del sol', 10, false),
('Primera Forma: Rugido', 5, 'Ataque explosivo con ondas sonoras', 6, false),
('Cuarta Forma: Sinfonía Constante', 5, 'Ataques rítmicos continuos', 7, false);