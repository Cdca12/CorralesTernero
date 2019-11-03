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