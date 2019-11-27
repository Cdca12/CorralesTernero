--Procesar salidas de crias
CREATE PROCEDURE spProcesarSalidasCrias
	
AS

	IF OBJECT_ID('tempdb.dbo.#CriasAProcesar') IS NOT NULL DROP TABLE #CriasAProcesar

		CREATE TABLE #CriasAProcesar (
			CriaID int
		)

		BEGIN TRY

			BEGIN TRAN

			--Obtenemos crías listas para procesar su salida
			INSERT INTO #CriasAProcesar
			SELECT CriaID FROM Crias
			WHERE EstadoCriaID = 1
			AND DiasEdad >= 150

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