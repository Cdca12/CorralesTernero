--Procesar salidas de crias
CREATE PROCEDURE spProcesarSalidasCrias
	
AS
	BEGIN

	if OBJECT_ID('tempdb.dbo.#CriasAProcesar') IS NOT NULL DROP TABLE #CriasAProcesar

		CREATE TABLE #CriasAProcesar (
			CriasID int
		)

		INSERT INTO #CriasAProcesar
		SELECT CriasID FROM TrasladosCrias
		WHERE DiasEnCorral >= 150
		
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE CriasID IN (
			SELECT CriasID FROM #CriasAProcesar
		)

		UPDATE Crias
		SET CorralID = NULL
		WHERE CriasID IN (
			SELECT CriasID FROM #CriasAProcesar
		)
	
	END