--Creación Base de Datos
--USE master
--GO
--CREATE DATABASE CorralesTernero;
--GO

--USE CorralesTernero;
--GO


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
	EstadoID varchar(10) PRIMARY KEY,
	Nombre varchar(255) NOT NULL
);

CREATE TABLE Corrales (
	CorralID int IDENTITY PRIMARY KEY ,
	EstadoID varchar(10) FOREIGN KEY REFERENCES Estados NOT NULL,
	TipoCorralID int FOREIGN KEY REFERENCES TipoCorral NOT NULL,
);

CREATE TABLE Sensores (
	SensorID int IDENTITY PRIMARY KEY,
	Marca varchar(255),
	CriaID int NULL
)

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


CREATE TABLE SeñalesSensores (
	Señal int IDENTITY PRIMARY KEY,
	SensorID int FOREIGN KEY REFERENCES Sensores,
	PresionArterial int,
	Respiracion int,
	Pulso int,
	Temperatura int
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
	@GrasaCoberturaID int,
	@SensorID int

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

	BEGIN TRY
		BEGIN TRAN
		--Actualizar GrasaCobertura
		UPDATE Crias
		SET GrasaCoberturaID = @GrasaCoberturaID
		WHERE CriaID = @CriaID

		--Asignar Sensor en base a la GrasaCobertura obtenida
		IF @GrasaCoberturaID = 2
		EXECUTE spAsignarSensor @CriaID


		COMMIT TRAN

	END TRY
	BEGIN CATCH
		IF @@TRANCOUNT > 0
		ROLLBACK TRAN
	END CATCH
GO



--Creación de sp
CREATE PROCEDURE spAsignarSensor
	@CriaID int
AS

	DECLARE @SensorID int

	BEGIN TRY
		BEGIN TRAN

		--Obtenemos un Sensor disponible, poniendo un candado para
		--que nadie más trate de asignarlo al mismo tiempo
		SET @SensorID = (
			SELECT TOP(1) SensorID 
			FROM Sensores  WITH (UPDLOCK)
			WHERE CriaID IS NULL
		)

		--Asignamos Cria al sensor en el catálogo de Sensores
		UPDATE Sensores
		SET CriaID = @CriaID
		WHERE SensorID = @SensorID

		--Asignamos el sensor obtenido a la Cria
		UPDATE Crias 
		SET SensorID = @SensorID
		WHERE CriaID = @CriaID

		COMMIT TRAN

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0 
		ROLLBACK TRANSACTION

	END CATCH
GO

CREATE PROCEDURE spAñadirCuarentena
	@CriaID int

AS
	BEGIN TRY
		BEGIN TRAN
	

		--Por el momento te manda al 2do corral, de Enfermos
		DECLARE @CorralID int = 2

		--Bloqueamos lectura de la tabla, por si alguien en algún reporte quiere leer, no obtener algún dato incorrecto
		DECLARE @Transaccion int = (
			SELECT Transaccion FROM TrasladosCrias WITH(updlock)
			WHERE CriaID = @CriaID
			AND FechaEgreso IS NULL
		)

		--Actualizamos nuevo estado de la cria
		UPDATE Crias
		SET 
			CorralID = @CorralID,   --Cambia a corral de enfermos
			EstadoCriaID = 2		--Pasa a un estado de En Tratamiento
		WHERE CriaID = @CriaID

		--Registramos la salida del corral actual (historial)
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE Transaccion = @Transaccion


		--Registramos la entrada al nuevo corral (historial)
		INSERT INTO TrasladosCrias(CorralID, CriaID)
		VALUES (@CorralID, @CriaID)

		--Obtener info de la Cria, no es necesario el control de concurrencia
		DECLARE @DietaID int = (SELECT DietaID FROM Crias WHERE CriaID = @CriaID)
		DECLARE @AlimentoID int = (SELECT AlimentoID FROM Dietas WHERE DietaID = @DietaID)
		DECLARE @NombreAlimentoDieta varchar(max) = (SELECT Nombre FROM Alimentos WHERE AlimentoID = @AlimentoID)
		DECLARE @CantidadAlimentoDieta int = (SELECT Cantidad FROM Alimentos WHERE AlimentoID = @AlimentoID)
	
		--Creamos un nuevo alimento compuesto del anterior + medicinas
		INSERT INTO Alimentos WITH (UPDLOCK)	--Bloquea la tabla para asegurar que el MAX acceda a la última tupla
		VALUES (@NombreAlimentoDieta + ' con medicinas', @CantidadAlimentoDieta)

		--Obtenemos nuevo alimento de la nueva dieta
		SET @AlimentoID = (SELECT MAX(AlimentoID) FROM Alimentos)
	
		--Creamos una nueva dieta apartir del nuevo alimento
		INSERT INTO Dietas WITH (UPDLOCK)		--Bloquea la tabla para asegurar que el MAX acceda a la última tupla
		VALUES (40, @AlimentoID)

		SET @DietaID = (SELECT MAX(DietaID) FROM Dietas)

		--Actualizamos la Cria con su nueva dieta
		UPDATE Crias
		SET DietaID = @DietaID
		WHERE CriaID = @CriaID


		COMMIT TRAN
	
	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH
GO

CREATE PROCEDURE spAñadirCuarentenaAll

AS
	BEGIN TRY
		BEGIN TRAN
	
		DECLARE @CriaID int
		DECLARE @Transaccion int

		DECLARE @ListaCriaID TABLE (
			RowID int IDENTITY,
			CriaID int
		)

		INSERT INTO @ListaCriaID (CriaID)
		SELECT DISTINCT CriaID FROM ReporteCriasPropensaEnfermarseView

		--Por el momento te manda al 2do corral, de Enfermos
		DECLARE @CorralID int = 2

		--Ciclo
		DECLARE @i int = 0
		DECLARE @length int = (SELECT MAX(RowID) FROM @ListaCriaID)

		WHILE (@i < @length)
		BEGIN
		
			SET @CriaID = (SELECT CriaID FROM @ListaCriaID WHERE RowID = (@i + 1))
			SET @Transaccion = (
				SELECT Transaccion FROM TrasladosCrias WITH(updlock)
				WHERE CriaID = @CriaID
				AND FechaEgreso IS NULL
			)

			--Actualizamos nuevo estado de la cria
			UPDATE Crias
			SET 
				CorralID = @CorralID,   --Cambia a corral de enfermos
				EstadoCriaID = 2		--Pasa a un estado de En Tratamiento
			WHERE CriaID = @CriaID

			--Registramos la salida del corral actual (historial)
			UPDATE TrasladosCrias
			SET FechaEgreso = GETDATE()
			WHERE Transaccion = @Transaccion


			--Registramos la entrada al nuevo corral (historial)
			INSERT INTO TrasladosCrias(CorralID, CriaID)
			VALUES (@CorralID, @CriaID)

			--Obtener info de la Cria, no es necesario el control de concurrencia
			DECLARE @DietaID int = (SELECT DietaID FROM Crias WHERE CriaID = @CriaID)
			DECLARE @AlimentoID int = (SELECT AlimentoID FROM Dietas WHERE DietaID = @DietaID)
			DECLARE @NombreAlimentoDieta varchar(max) = (SELECT Nombre FROM Alimentos WHERE AlimentoID = @AlimentoID)
			DECLARE @CantidadAlimentoDieta int = (SELECT Cantidad FROM Alimentos WHERE AlimentoID = @AlimentoID)
	
			--Creamos un nuevo alimento compuesto del anterior + medicinas
			INSERT INTO Alimentos WITH (UPDLOCK)	--Bloquea la tabla para asegurar que el MAX acceda a la última tupla
			VALUES (@NombreAlimentoDieta + ' con medicinas', @CantidadAlimentoDieta)

			--Obtenemos nuevo alimento de la nueva dieta
			SET @AlimentoID = (SELECT MAX(AlimentoID) FROM Alimentos)
	
			--Creamos una nueva dieta apartir del nuevo alimento
			INSERT INTO Dietas WITH (UPDLOCK)		--Bloquea la tabla para asegurar que el MAX acceda a la última tupla
			VALUES (40, @AlimentoID)

			SET @DietaID = (SELECT MAX(DietaID) FROM Dietas)

			--Actualizamos la Cria con su nueva dieta
			UPDATE Crias
			SET DietaID = @DietaID
			WHERE CriaID = @CriaID


			SET @i = @i + 1
		END

		COMMIT TRAN
	
	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH
GO

CREATE VIEW ConsultarCriasASacrificarView
AS
	SELECT * FROM TrasladosCrias
	WHERE CorralID IN (
		SELECT CorralID FROM Corrales
		WHERE TipoCorralID = 2 --Corrales Enfermos
	)
	AND FechaEgreso IS NULL
	AND DiasEnCorral >= 40

GO


--Procesar salidas de crias
CREATE PROCEDURE spProcesarSalidasCrias
	@CriaID int
AS

	BEGIN TRY

		BEGIN TRAN

		--Añadimos fecha de egreso a esas crías
		--Candado escritura implícito, no permite lecturas
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE CriaID = @CriaID

		--Actualizamos datos de la cria, poniendo su EstadoCriaID a Procesado
		UPDATE Crias
		SET CorralID = NULL, EstadoCriaID = 4
		WHERE CriaID = @CriaID
	
		COMMIT TRAN	

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0 
		ROLLBACK TRANSACTION

	END CATCH
GO

CREATE PROCEDURE spProcesarSalidasCriasAll
	
AS

	IF OBJECT_ID('tempdb.dbo.#CriasAProcesar') IS NOT NULL DROP TABLE #CriasAProcesar

		CREATE TABLE #CriasAProcesar (
			CriaID int
		)

		BEGIN TRY

			BEGIN TRAN

			--Obtenemos crías listas para procesar su salida
			INSERT INTO #CriasAProcesar
			SELECT CriaID FROM ReporteCriasProcesarSalidaView

			--Añadimos fecha de egreso a esas crías
			UPDATE TrasladosCrias
			SET FechaEgreso = GETDATE()
			WHERE CriaID IN (
				SELECT CriaID FROM #CriasAProcesar
			)

			--Actualizamos datos de la cria, poniendo su EstadoCriaID a Procesado
			UPDATE Crias
			SET CorralID = NULL, EstadoCriaID = 4
			WHERE CriaID IN (
				SELECT CriaID FROM #CriasAProcesar
			)
	
			COMMIT TRAN	

		END TRY
		BEGIN CATCH

			IF @@TRANCOUNT > 0 
			ROLLBACK TRANSACTION

		END CATCH
GO

CREATE PROCEDURE spSacrificarCrias
	@CriaID int
AS

	BEGIN TRY
		BEGIN TRAN

		DECLARE @Transaccion int = (
			SELECT Transaccion FROM ConsultarCriasASacrificarView
			WHERE CriaID = @CriaID
		)
		
		--Actualiza estado de la Cria
		UPDATE Crias
		SET EstadoCriaID = 3
		WHERE CriaID = @CriaID

		--Actualiza historial
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE Transaccion = @Transaccion

		COMMIT TRAN

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH
GO

CREATE PROCEDURE spProcesarSalidasCriasAll
	
AS

	IF OBJECT_ID('tempdb.dbo.#CriasAProcesar') IS NOT NULL DROP TABLE #CriasAProcesar

		CREATE TABLE #CriasAProcesar (
			CriaID int
		)

		BEGIN TRY

			BEGIN TRAN

			--Obtenemos crías listas para procesar su salida
			INSERT INTO #CriasAProcesar
			SELECT CriaID FROM ReporteCriasProcesarSalidaView

			--Añadimos fecha de egreso a esas crías
			UPDATE TrasladosCrias
			SET FechaEgreso = GETDATE()
			WHERE CriaID IN (
				SELECT CriaID FROM #CriasAProcesar
			)

			--Actualizamos datos de la cria, poniendo su EstadoCriaID a Procesado
			UPDATE Crias
			SET CorralID = NULL, EstadoCriaID = 4
			WHERE CriaID IN (
				SELECT CriaID FROM #CriasAProcesar
			)
	
			COMMIT TRAN	

		END TRY
		BEGIN CATCH

			IF @@TRANCOUNT > 0 
			ROLLBACK TRANSACTION

		END CATCH
GO



CREATE PROCEDURE spAvanzarDias
	@Dias int
AS
	BEGIN TRY
		BEGIN TRAN

		DECLARE @ListaCriasActivas TABLE (CriaID int)

		--Obtiene Crias vivas y pone candado para que los demás procesos no obtengan una Edad desactualizada
		INSERT INTO @ListaCriasActivas
		SELECT CriaID FROM Crias WITH (UPDLOCK)
		WHERE EstadoCriaID = 1 OR EstadoCriaID = 2

		--Aumenta edad en días a las Crias vivas
		UPDATE Crias
		SET DiasEdad = DiasEdad + @Dias
		WHERE CriaID IN (
			SELECT CriaID FROM @ListaCriasActivas
		)

		--Aumenta días en corral, ya sea sanos o enfermos
		UPDATE TrasladosCrias
		SET DiasEnCorral = DiasEnCorral + @Dias
		WHERE FechaEgreso IS NULL

		COMMIT TRAN

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH
GO


CREATE TRIGGER triAsignarCorral
ON Crias

AFTER INSERT
AS

	INSERT INTO TrasladosCrias (CorralID, CriaID)
	SELECT CorralID, CriaID FROM inserted
GO

--Creación de vistas
CREATE VIEW ConsultarCriasView
AS
	SELECT C.CriaID, C.CorralID, C.Peso, C.Grasa, G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	C.SensorID, C.DietaID, E.Descripcion as EstadoCria, C.DiasEdad FROM Crias C
	INNER JOIN GrasaCobertura G ON C.GrasaCoberturaID = G.GrasaCoberturaID
	INNER JOIN Musculo M ON C.MusculoID = M.MusculoID
	INNER JOIN EstadoCria E ON C.EstadoCriaID = E.EstadoCriaID
GO

CREATE VIEW ReporteCriasPropensaEnfermarseView
AS
	SELECT SS.*, C.CriaID FROM
	(SELECT * FROM SeñalesSensores 
	WHERE Temperatura >= 40
	OR PresionArterial >= 120
	OR Pulso >= 90) AS SS
	INNER JOIN (SELECT * FROM Crias WHERE EstadoCriaID = 1) AS C ON SS.SensorID = C.SensorID
GO

--CREATE VIEW ReporteCriasEnfermasView
--AS
--	SELECT C.CriaID, C.CorralID, C.Peso, C.Grasa, G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
--	E.Descripcion as EstadoCria, D.DietaID, C.SensorID, C.DiasEdad 
--	FROM (SELECT * FROM Crias WHERE EstadoCriaID = 2) as C --Filtro las crias en subconsulta para hacer menos joins
--	INNER JOIN GrasaCobertura G ON C.GrasaCoberturaID = G.GrasaCoberturaID
--	INNER JOIN Musculo M ON C.MusculoID = M.MusculoID
--	INNER JOIN EstadoCria E ON C.EstadoCriaID = E.EstadoCriaID
--	INNER JOIN Dietas D ON C.DietaID = D.DietaID
--GO

CREATE VIEW ReporteCriasEnfermasView
AS
	SELECT C.CriaID, C.CorralID, C.Peso, G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	E.Descripcion as EstadoCria, D.DietaID, C.SensorID, C.DiasEdad 
	FROM (SELECT * FROM Crias WHERE EstadoCriaID = 2) as C --Filtro las crias en subconsulta para hacer menos joins
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

INSERT INTO Estados (Nombre, EstadoID)
VALUES
	('Aguascalientes', 'Ags'),
	('Baja California', 'BC'),
	('Baja California Sur', 'BCS'),
	('Campeche', 'Camp'),
	('Chiapas', 'Chis'),
	('Chihuahua', 'Chih'),
	('Ciudad de México', 'CDMX'),
	('Coahuila de Zaragoza', 'Coah'),
	('Colima', 'Col'),
	('Durango', 'Dgo'),
	('Guanajuato', 'Gto'),
	('Guerrero', 'Gro'),
	('Hidalgo', 'Hgo'),
	('Jalisco', 'Jal'),
	('México', 'Méx'),
	('Michoacán de Ocampo', 'Mich'),
	('Morelos', 'Mor'),
	('Nayarit', 'Nay'),
	('Nuevo León', 'NL'),
	('Oaxaca', 'Oax'),
	('Puebla', 'Pue'),
	('Querétaro', 'Qro'),
	('Quintana Roo', 'QR'),
	('San Luis Potosí', 'SLP'),
	('Sinaloa', 'Sin'),
	('Sonora', 'Son'),
	('Tabasco', 'Tab'),
	('Tamaulipas', 'Tamps'),
	('Tlaxcala', 'Tlax'),
	('Veracruz de Ignacio de la Llave', 'Ver'),
	('Yucatán', 'Yuc'),
	('Zacatecas', 'Zac');


	
INSERT INTO Corrales
VALUES
	((SELECT EstadoID FROM Estados WHERE Nombre = 'Sinaloa'), (SELECT TipoCorralID FROM TipoCorral WHERE Descripcion = 'Sanos')),
	((SELECT EstadoID FROM Estados WHERE Nombre = 'Ciudad de México'), (SELECT TipoCorralID FROM TipoCorral WHERE Descripcion = 'Enfermos'));



-- Insertar Sensores
DECLARE @Marca varchar(max)
DECLARE @i int
DECLARE @max int

SET @Marca = 'Kirkland'
SET @i = 0
SET @max = 5
WHILE(@i < @max)
BEGIN
	INSERT INTO Sensores
	VALUES (@Marca, NULL)

	SET @i = @i + 1
END

SET @Marca = 'Google'
SET @i = 0
SET @max = 5
WHILE(@i < @max)
BEGIN
	INSERT INTO Sensores
	VALUES (@Marca, NULL)

	SET @i = @i + 1
END

SET @Marca = 'Steren'
SET @i = 0
SET @max = 5
WHILE(@i < @max)
BEGIN
	INSERT INTO Sensores
	VALUES (@Marca, NULL)

	SET @i = @i + 1
END

SET @Marca = 'Clariti'
SET @i = 0
SET @max = 5
WHILE(@i < @max)
BEGIN
	INSERT INTO Sensores
	VALUES (@Marca, NULL)

	SET @i = @i + 1
END

SET @Marca = 'Microsoft'
SET @i = 0
SET @max = 5
WHILE(@i < @max)
BEGIN
	INSERT INTO Sensores
	VALUES (@Marca, NULL)

	SET @i = @i + 1
END

GO


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


--TEST: Preparar Crias para salida
UPDATE Crias
SET DiasEdad = 150
WHERE CriaID IN (
	1, 5, 6
)

GO
	-- Tests:
	-- SeñalesSensores se inserta desde la app, simulando el envío de señales
	-- TrasladosCrias se inserta automáticamente cuando se añade una nueva cria o se meuve de Corral (Proceso de enfermarse)

--Permisos
 CREATE TABLE Menu (
	MenuID int IDENTITY PRIMARY KEY,
	Name varchar(max)
)

CREATE TABLE MenuItem (
	MenuItemID int IDENTITY PRIMARY KEY,
	Name varchar(max),
	MenuID int FOREIGN KEY REFERENCES Menu
)

CREATE TABLE Usuarios (
	UsuarioID int,
	Username varchar(max),
	MenuItemID int FOREIGN KEY REFERENCES MenuItem,
	PRIMARY KEY (UsuarioID, MenuItemID)
)
GO

INSERT INTO Menu
VALUES
	('Crías'),
	('Corrales'),
	('Alimentos'),
	('Dietas'),
	('Sensores'),
	('Simulaciones');

INSERT INTO MenuItem
VALUES
	('Añadir Crías', 1),
	('Consultar Crías', 1),
	('Reporte Crías Enfermas', 1),
	('Procesar Salidas', 1),
	('Sacrificar Crías', 1),

	('Añadir Corrales', 2),
	('Consultar Corrales', 2),

	('Añadir Alimentos', 3),
	('Consultar Alimentos', 3),

	('Añadir Dietas', 4),
	('Consultar Dietas', 4),

	('Añadir Sensores', 5),
	('Consultar Sensores', 5),
	('Analizar Señales', 5),

	('Simular Señales', 6),
	('Avanzar Días', 6);

GO

--Insertar Usuarios con sus permisos
INSERT INTO Usuarios
SELECT 1, 'usrAdministrador', MenuItemID FROM MenuItem

INSERT INTO Usuarios
SELECT 2, 'usrEncargado', MenuItemID FROM MenuItem
WHERE MenuItemID IN (
	1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
) 

INSERT INTO Usuarios
SELECT 3, 'usrVeterinario', MenuItemID FROM MenuItem
WHERE MenuItemID IN (
	2, 3, 7, 8, 9, 10, 11, 14
)

GO


CREATE VIEW ObtenerConfiguracionView
AS

	SELECT M.MenuID, M.Name as MenuName, T.MenuItemID, T.Name as MenuItemName, T.Username FROM
	(SELECT U.UsuarioID, U.Username, MI.MenuItemID, MI.Name, MI.MenuID FROM Usuarios U
	INNER JOIN MenuItem MI ON U.MenuItemID = MI.MenuItemID) as T
	INNER JOIN Menu M ON T.MenuID = M.MenuID

GO
