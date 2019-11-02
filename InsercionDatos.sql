--Inserción de datos para trabajar
USE CorralesTernero;

INSERT INTO EstadoCria
VALUES
	(1, 'Saludable'),
	(2, 'En Tratamiento'),
	(3, 'Sacrificado');

INSERT INTO GrasaCobertura
VALUES
	(0, 'Grasa de cobertura 0', 'No existe prácticamente grasa de cobertura'),
	(1, 'Grasa de cobertura 1', 'La grasa de cobertura, siendo escaso su espesor, cubre la mayor parte de la canal'),
	(2, 'Grasa de cobertura 2', 'Grasa de cobertura es abundante, sin ser excesiva, que no forma cúmulos y cubre prácticamente toda la canal'),
	(3, 'Grasa de cobertura 3', 'Grasa de cobertura es excesivamente abundante');

INSERT INTO Musculo
VALUES
	(1, 'Carnes Rojas'),
	(2, 'Carnes Blancas');

INSERT INTO Alimentos
VALUES
	('Pasto', 10),
	('Bledo', 20),
	('Vegetales', 30);

INSERT INTO Dietas
VALUES
	(150, (SELECT AlimentoID FROM Alimentos WHERE Nombre = 'Pasto')),
	(200, (SELECT AlimentoID FROM Alimentos WHERE Nombre = 'Bledo')),
	(250, (SELECT AlimentoID FROM Alimentos WHERE Nombre = 'Vegetales'));


INSERT INTO TipoCorral
VALUES
	(1, 'Sanos'),
	(2, 'Enfermos');

INSERT INTO Estados
VALUES
	('SL', 'Sinaloa'),
	('DF', 'Ciudad de México');

	
INSERT INTO Corrales
VALUES
	(1, (SELECT EstadoID FROM Estados WHERE Nombre = 'Sinaloa'), (SELECT TipoCorralID FROM TipoCorral WHERE Descripcion = 'Sanos')),
	(2, (SELECT EstadoID FROM Estados WHERE Nombre = 'Ciudad de México'), (SELECT TipoCorralID FROM TipoCorral WHERE Descripcion = 'Enfermos'));


--Insertar Crias
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 50, 25, 1, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 30, 2, 2);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 60, 35, 1, 3);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 45, 20, 2, 1);

	-- Tests:
	-- Sensores se inserta cuando una cría se clasifica con grasa cobertura 2
	-- SeñalesSensores se inserta desde la app, simulando el envío de señales
	-- TrasladosCrias se inserta cuando se añade una nueva cria

--Pendiente: Acomodar a la Cria, para insertar en la tabla TrasladosCrias