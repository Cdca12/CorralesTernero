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


--Insertar Crias Test
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 35, 7, 1, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 35, 10, 1, 1);

--GrasaCobertura2
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 35, 15, 1, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 35, 25, 1, 1);

--
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 12, 1, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 15, 1, 1);

--GrasaCobertura2
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 23, 1, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 27, 1, 1);

--
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 75, 15, 1, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 75, 24, 1, 1);

--GrasaCobertura2
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 75, 35, 1, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 75, 40, 1, 1);


--Carnes Blancas

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 7, 2, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 10, 2, 1);

--GrasaCobertura2
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 15, 2, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 55, 25, 2, 1);


--
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 73, 12, 2, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 73, 15, 2, 1);

--GrasaCobertura2
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES
	(1, 73, 23, 2, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 73, 27, 2, 1);

--
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 94, 15, 2, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 94, 24, 2, 1);

--GrasaCobertura2
INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 94, 35, 2, 1);

INSERT INTO Crias (CorralID, Peso, Grasa, MusculoID, DietaID)
VALUES 
	(1, 94, 40, 2, 1);



	-- Tests:
	-- Sensores se inserta cuando una cría se clasifica con grasa cobertura 2
	-- SeñalesSensores se inserta desde la app, simulando el envío de señales
	-- TrasladosCrias se inserta cuando se añade una nueva cria o se meuve de Corral (Proceso de enfermarse)