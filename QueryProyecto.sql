--Creación Base de Datos
CREATE DATABASE CorralesTernero;
GO

USE CorralesTernero;
GO


CREATE TABLE EstadoCria (
	EstadoCriaID int PRIMARY KEY,
	Descripcion varchar(255) NOT NULL
);

CREATE TABLE GrasaCobertura (
	GrasaCoberturaID int PRIMARY KEY,
	Tipo varchar(255) NOT NULL,
	Descripcion varchar(255) NOT NULL
);

CREATE TABLE Musculo (
	MusculoID int PRIMARY KEY,
	ColorMusculo varchar(255) NOT NULL
);

CREATE TABLE Alimentos (
	AlimentoID int IDENTITY PRIMARY KEY,
	Nombre varchar(255) NOT NULL,
	Cantidad int NOT NULL
);

CREATE TABLE Dietas (
	DietaID int IDENTITY PRIMARY KEY,
	DiasTratamiento int NOT NULL,
	AlimentoID int FOREIGN KEY REFERENCES Alimentos
);

CREATE TABLE TipoCorral (
	TipoCorralID int PRIMARY KEY,
	Descripcion varchar(50) NOT NULL
);

CREATE TABLE Estados (
	EstadoID char(2) PRIMARY KEY,
	Nombre varchar(255) NOT NULL
);

CREATE TABLE Corrales (
	CorralID int PRIMARY KEY,
	EstadoID char(2) FOREIGN KEY REFERENCES Estados NOT NULL,
	TipoCorralID int FOREIGN KEY REFERENCES TipoCorral NOT NULL,
);

CREATE TABLE Sensores (
	SensorID int IDENTITY PRIMARY KEY,
	CriaID int NOT NULL,
);

CREATE TABLE SeñalesSensores (
	Señal int IDENTITY PRIMARY KEY,
	SensorID int FOREIGN KEY REFERENCES Sensores,
	PresionArterial int,
	Respiracion int,
	Pulso int,
	Temperatura int
);

CREATE TABLE Crias (
	CriaID int IDENTITY PRIMARY KEY,
	CorralID int FOREIGN KEY REFERENCES Corrales, 
	Peso int NOT NULL,
	Grasa int NOT NULL,
	GrasaCoberturaID int FOREIGN KEY REFERENCES GrasaCobertura NULL,
	MusculoID int FOREIGN KEY REFERENCES Musculo NOT NULL,
	SensorID int FOREIGN KEY REFERENCES Sensores NULL,
	DietaID int FOREIGN KEY REFERENCES Dietas DEFAULT 1,
	EstadoCriaID int FOREIGN KEY REFERENCES EstadoCria DEFAULT 1,
	DiasEdad int NOT NULL DEFAULT 0
);

CREATE TABLE TrasladosCrias (
	Transaccion int IDENTITY PRIMARY KEY,
	CorralID int FOREIGN KEY REFERENCES Corrales INDEX IX_CorralID NONCLUSTERED,
	CriaID int FOREIGN KEY REFERENCES Crias INDEX IX_CriaID NONCLUSTERED,
	FechaIngreso date NOT NULL DEFAULT CAST(GETDATE() as date),
	FechaEgreso date, --Default NULL
	DiasEnCorral int NOT NULL DEFAULT 0
);

GO


--Creación de sp
CREATE PROCEDURE spAsignarSensor
	@CriaID int
AS
	BEGIN

	DECLARE @SensorID int

	INSERT INTO Sensores
	VALUES (@CriaID)
			
	SET @SensorID = (SELECT TOP(1) SensorID FROM Sensores WHERE CriaID = @CriaID)

	UPDATE Crias
	SET SensorID = @SensorID
	WHERE CriaID = @CriaID

	END
GO

CREATE PROCEDURE spAñadirCuarentena

AS
	BEGIN
	
	DECLARE @CriaID int = (SELECT TOP(1) CriaID FROM ReporteCriasPropensaEnfermarseView)

	--Por el momento te manda al 2do corral, de Enfermos
	DECLARE @CorralID int = 2

	DECLARE @Transaccion int = (SELECT MAX(Transaccion) FROM TrasladosCrias WHERE CriaID = @CriaID)

	
	UPDATE Crias
	SET CorralID = @CorralID,   --Cambia a corral de enfermos
	EstadoCriaID = 2			--Pasa a un estado de En Tratamiento
	WHERE CriaID = @CriaID

	UPDATE TrasladosCrias
	SET FechaEgreso = GETDATE()
	WHERE Transaccion = @Transaccion

	INSERT INTO TrasladosCrias(CorralID, CriaID)
	VALUES (@CorralID, @CriaID)

	DECLARE @DietaID int = (SELECT DietaID FROM Crias WHERE CriaID = @CriaID)
	DECLARE @AlimentoID int = (SELECT AlimentoID FROM Dietas WHERE DietaID = @DietaID)
	DECLARE @NombreAlimentoDieta varchar(max) = (SELECT Nombre FROM Alimentos WHERE AlimentoID = @AlimentoID)
	DECLARE @CantidadAlimentoDieta int = (SELECT Cantidad FROM Alimentos WHERE AlimentoID = @AlimentoID)
	

	INSERT INTO Alimentos
	VALUES (@NombreAlimentoDieta + ' con medicinas', @CantidadAlimentoDieta)
	
	INSERT INTO Dietas
	VALUES (40, @AlimentoID)

	SET @DietaID = (SELECT MAX(DietaID) FROM Dietas)

	UPDATE Crias
	SET DietaID = @DietaID
	WHERE CriaID = @CriaID
	
	END
GO

CREATE PROCEDURE spSacrificarCrias
AS

	DECLARE @CriasIDASacrificar TABLE (
		CriaID int,
		Transaccion int
	)

	INSERT INTO @CriasIDASacrificar SELECT CriaID, Transaccion FROM TrasladosCrias
	WHERE CorralID IN (
		SELECT CorralID FROM Corrales
		WHERE TipoCorralID = 2 --Corrales Enfermos
	)
	AND FechaEgreso IS NULL
	AND DiasEnCorral >= 40
	
	--Poner CorralID null o dejar historial del ultimo corral (?
	UPDATE Crias
	SET EstadoCriaID = 3
	WHERE CriaID IN (
		SELECT CriaID FROM @CriasIDASacrificar
	)

	UPDATE TrasladosCrias
	SET FechaEgreso = GETDATE()
	WHERE Transaccion IN (
		SELECT Transaccion FROM @CriasIDASacrificar
	)
GO

--Procesar salidas de crias
CREATE PROCEDURE spProcesarSalidasCrias
	
AS
	BEGIN

	IF OBJECT_ID('tempdb.dbo.#CriasAProcesar') IS NOT NULL DROP TABLE #CriasAProcesar

		CREATE TABLE #CriasAProcesar (
			CriaID int
		)

		INSERT INTO #CriasAProcesar
		SELECT CriaID FROM Crias
		WHERE EstadoCriaID = 1
		AND DiasEdad >= 150
		

		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE CriaID IN (
			SELECT CriaID FROM #CriasAProcesar
		)

		UPDATE Crias
		SET CorralID = NULL, EstadoCriaID = 4
		WHERE CriaID IN (
			SELECT CriaID FROM #CriasAProcesar
		)
	
	END
GO

--Creación de Triggers
CREATE TRIGGER triClasificarCrias
ON Crias

AFTER INSERT
AS
	
DECLARE
	@CriaID int,
	@Peso int,
	@Grasa int,
	@MusculoID int,
	@GrasaCoberturaID int

	SELECT @CriaID = CriaID, @Peso = Peso, @Grasa = Grasa, @MusculoID = MusculoID FROM inserted
	
	--Clasificar

	IF @MusculoID = 1 --Carnes Rojas
	BEGIN
		IF @Peso < 50
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 8 THEN 0
				WHEN @Grasa BETWEEN 9 AND 11 THEN 1
				WHEN @Grasa BETWEEN 12 AND 20 THEN 2
				WHEN @Grasa > 20 THEN 3
			END 
		END
		
		IF @Peso BETWEEN 50 AND 70
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 13 THEN 0
				WHEN @Grasa BETWEEN 14 AND 16 THEN 1
				WHEN @Grasa BETWEEN 17 AND 25 THEN 2
				WHEN @Grasa > 25 THEN 3
			END
		END
		IF @Peso > 70
		BEGIN	
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 16 THEN 0
				WHEN @Grasa BETWEEN 17 AND 25 THEN 1
				WHEN @Grasa BETWEEN 26 AND 35 THEN 2
				WHEN @Grasa > 35 THEN 3
			END
		END
	END

	ELSE --Carnes Blancas
	BEGIN

		IF @Peso < 60
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 8 THEN 0
				WHEN @Grasa BETWEEN 9 AND 11 THEN 1
				WHEN @Grasa BETWEEN 12 AND 20 THEN 2
				WHEN @Grasa > 20 THEN 3
			END 
		END

		IF @Peso BETWEEN 60 AND 80
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 13 THEN 0
				WHEN @Grasa BETWEEN 14 AND 16 THEN 1
				WHEN @Grasa BETWEEN 17 AND 25 THEN 2
				WHEN @Grasa > 25 THEN 3
			END
		END

		IF @Peso > 80
		BEGIN	
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 16 THEN 0
				WHEN @Grasa BETWEEN 17 AND 25 THEN 1
				WHEN @Grasa BETWEEN 26 AND 35 THEN 2
				WHEN @Grasa > 35 THEN 3
			END
		END

	END

		
	--Actualizar GrasaCobertura
	UPDATE Crias
	SET GrasaCoberturaID = @GrasaCoberturaID
	WHERE CriaID = @CriaID

	--Asignar Sensor en base a la GrasaCobertura actualizada
	IF @GrasaCoberturaID = 2
	EXECUTE spAsignarSensor @CriaID
GO


CREATE TRIGGER triAsignarCorral
ON Crias

AFTER INSERT
AS

DECLARE 
	@CriaID int,
	@CorralID int;
	

SELECT @CriaID = CriaID, @CorralID = CorralID FROM inserted;

	BEGIN
		INSERT INTO TrasladosCrias (CorralID, CriaID)
		VALUES 
			(@CorralID, @CriaID);
	END
GO

	--Creación de vistas
CREATE VIEW ReporteCriasPropensaEnfermarseView
AS
	SELECT SS.*, C.CriaID FROM
	(SELECT * FROM SeñalesSensores 
	WHERE Temperatura >= 40
	OR PresionArterial >= 120
	OR Pulso >= 90) AS SS
	INNER JOIN (SELECT * FROM Crias WHERE EstadoCriaID = 1) AS C ON SS.SensorID = C.SensorID
GO

CREATE VIEW ReporteCriasProcesarSalidaView
AS
	SELECT TC.CriaID, C.CorralID, TC.Peso, TC.Grasa, 
	G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	E.Descripcion as EstadoCria, TC.DiasEdad FROM 
	(SELECT * FROM Crias WHERE EstadoCriaID = 1 AND DiasEdad >= 150) AS TC
	INNER JOIN Corrales C ON TC.CorralID = C.CorralID
	INNER JOIN GrasaCobertura G ON TC.GrasaCoberturaID = G.GrasaCoberturaID
	INNER JOIN Musculo M ON TC.MusculoID = M.MusculoID
	INNER JOIN EstadoCria E ON TC.EstadoCriaID = E.EstadoCriaID
GO

CREATE VIEW ReporteCriasEnfermasView
AS
	SELECT C.CriaID, C.CorralID, C.Peso, G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	E.Descripcion as EstadoCria, D.DietaID, C.SensorID, C.DiasEdad 
	FROM (SELECT * FROM Crias WHERE EstadoCriaID = 2) as C
		INNER JOIN GrasaCobertura G ON C.GrasaCoberturaID = G.GrasaCoberturaID
		INNER JOIN Musculo M ON C.MusculoID = M.MusculoID
		INNER JOIN EstadoCria E ON C.EstadoCriaID = E.EstadoCriaID
		INNER JOIN Dietas D ON C.DietaID = D.DietaID
GO

--Inserción de Datos
--Inserción de datos para trabajar

INSERT INTO EstadoCria
VALUES
	(1, 'Saludable'),
	(2, 'En Tratamiento'),
	(3, 'Sacrificado'),
	(4, 'Procesado');


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
GO


	-- Tests:
	-- Sensores se inserta automáticamente cuando una cría se clasifica con grasa cobertura 2
	-- SeñalesSensores se inserta desde la app, simulando el envío de señales
	-- TrasladosCrias se inserta automáticamente cuando se añade una nueva cria o se meuve de Corral (Proceso de enfermarse)