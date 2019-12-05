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