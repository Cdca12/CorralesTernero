--Inserción de datos para trabajar

INSERT INTO EstadoCria
VALUES
	(1, 'Saludable'),
	(2, 'En tratamiento'),
	(3, 'Sacrificado');

INSERT INTO Peso
VALUES
	(1, 'Flacas'),
	(2, 'Límite'),
	(3, 'Moderadas'),
	(4, 'Gordas');

INSERT INTO GrasaCobertura
VALUES
	(0, 'Grasa de cobertura 0', 'No existe prácticamente grasa de cobertura'),
	(1, 'Grasa de cobertura 1', 'La grasa de cobertura, siendo escaso su espesor, cubre la mayor parte de la canal'),
	(2, 'Grasa de cobertura 2', 'Grasa de cobertura es abundante, sin ser excesiva, que no forma cúmulos y cubre prácticamente toda la canal'),
	(3, 'Grasa de cobertura 3', 'Grasa de cobertura es excesivamente abundante');

INSERT INTO Musculo
VALUES
	(1, 'Carnes rojas'),
	(2, 'Carnes blancas');

INSERT INTO Alimentos
VALUES
	('Pasto', 10);

INSERT INTO Dietas
VALUES
	(150, (SELECT AlimentoID FROM Alimentos WHERE Nombre = 'Pasto'));

--Nos saltamos Sensores


INSERT INTO TipoCorral
VALUES
	(1, 'Sanos'),
	(2, 'Cuarentena');

INSERT INTO Estados
VALUES
	('SL', 'Sinaloa'),
	('DF', 'Ciudad de México');

	
INSERT INTO Corrales
VALUES
	(1, (SELECT EstadoID FROM Estados WHERE Nombre = 'Sinaloa'), (SELECT TipoCorralID FROM TipoCorral WHERE Descripcion = 'Sanos')),
	(2, (SELECT EstadoID FROM Estados WHERE Nombre = 'Ciudad de México'), (SELECT TipoCorralID FROM TipoCorral WHERE Descripcion = 'Cuarentena'));


--Pendiente: Acomodar a la Cria, para insertar en la tabla TrasladosCrias