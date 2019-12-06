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