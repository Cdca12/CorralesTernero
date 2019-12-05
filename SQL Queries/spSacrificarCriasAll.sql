CREATE PROCEDURE spSacrificarCriasAll
AS

	DECLARE @CriasIDASacrificar TABLE (
		CriaID int,
		Transaccion int
	)

	INSERT INTO @CriasIDASacrificar 
	SELECT CriaID, Transaccion FROM ConsultarCriasASacrificarView

	DECLARE @SensoresID TABLE (
		SensorID int
	)

	--Obtenemos Sensores de esas Crias
	INSERT INTO @SensoresID
	SELECT SensorID FROM Sensores
	WHERE CriaID IN (
		SELECT CriaID FROM @CriasIDASacrificar
	)

	
	BEGIN TRY
		BEGIN TRAN

		--Poner CorralID null o dejar historial del ultimo corral (?
		UPDATE Crias
		SET CorralID = NULL,	--Quitamos del Corral
			EstadoCriaID = 3,	--Dejamos su su EstadoCriaID a Sacrificado
			SensorID = NULL		--Quitamos SensorID
		WHERE CriaID IN (
			SELECT CriaID FROM @CriasIDASacrificar
		)

		--UPDATE Crias
		--SET EstadoCriaID = 3
		--WHERE CriaID IN (
		--	SELECT CriaID FROM @CriasIDASacrificar
		--)

		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE Transaccion IN (
			SELECT Transaccion FROM @CriasIDASacrificar
		)

		--Quitamos el Sensor, lo dejamos disponible
		UPDATE Sensores
		SET CriaID = NULL
		WHERE SensorID IN (
			SELECT SensorID FROM @SensoresID
		)

		COMMIT TRAN

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH

