--Procesar salidas de crias
CREATE PROCEDURE spProcesarSalidasCrias
	@CriaID int
AS

	BEGIN TRY

		BEGIN TRAN

		--A�adimos fecha de egreso a esas cr�as
		--Candado escritura impl�cito, no permite lecturas
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE CriaID = @CriaID

		--Actualizamos datos de la cria, 
		UPDATE Crias
		SET CorralID = NULL,	--Quitamos del Corral
			EstadoCriaID = 4	--Dejamos su su EstadoCriaID a Procesado
			--SensorID = NULL		--Quitamos SensorID
		WHERE CriaID = @CriaID

	
		COMMIT TRAN	

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0 
		ROLLBACK TRANSACTION

	END CATCH