CREATE PROCEDURE spSacrificarCrias
	@CriaID int
AS


	BEGIN TRY
		BEGIN TRAN

		DECLARE @Transaccion int = (
			SELECT Transaccion FROM ConsultarCriasASacrificarView
			WHERE CriaID = @CriaID
		)

		DECLARE @SensorID int = (
			SELECT SensorID FROM Crias
			WHERE CriaID = @CriaID
		)

		--Actualiza historial
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE Transaccion = @Transaccion
		
		--Actualiza estado de la Cria
		UPDATE Crias
		SET CorralID = NULL,		--Quitamos del Corral
		EstadoCriaID = 3,			--Dejamos su su EstadoCriaID a Sacrificado
		SensorID = NULL			--Quitamos SensorID
		WHERE CriaID = @CriaID
		

		--Quitamos el Sensor, lo dejamos disponible
		UPDATE Sensores
		SET CriaID = NULL
		WHERE SensorID = @SensorID

		COMMIT TRAN

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH