--Procesar salidas de crias
CREATE PROCEDURE spProcesarSalidasCriasAll
	
AS

	IF OBJECT_ID('tempdb.dbo.#CriasAProcesar') IS NOT NULL DROP TABLE #CriasAProcesar

		CREATE TABLE #CriasAProcesar (
			CriaID int,
		)

		--Obtenemos crías listas para procesar su salida
		INSERT INTO #CriasAProcesar
		SELECT CriaID FROM ReporteCriasProcesarSalidaView


		BEGIN TRY

			BEGIN TRAN
			
			--Añadimos fecha de egreso a esas crías
			--Candado escritura implícito, no permite lecturas
			UPDATE TrasladosCrias
			SET FechaEgreso = GETDATE()
			WHERE CriaID IN (
				SELECT CriaID FROM #CriasAProcesar
			)
			

			--Actualizamos datos de la cria, poniendo su EstadoCriaID a Procesado
			UPDATE Crias
			SET CorralID = NULL,	--Quitamos del Corral
				EstadoCriaID = 4	--Dejamos su su EstadoCriaID a Procesado
				--SensorID = NULL		--Quitamos SensorID
			WHERE CriaID IN (
				SELECT CriaID FROM #CriasAProcesar
			)
	
			COMMIT TRAN	

		END TRY
		BEGIN CATCH

			IF @@TRANCOUNT > 0 
			ROLLBACK TRANSACTION

		END CATCH