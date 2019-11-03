CREATE PROCEDURE spAsignarSensor
	@CriaID int
AS
	BEGIN

	DECLARE @SensorID int

	--TODO: Hacer transaccion para atomizar el INSERT con el UPDATE
	INSERT INTO Sensores
	VALUES (@CriaID)
			
	SET @SensorID = (SELECT TOP(1) SensorID FROM Sensores WHERE CriaID = @CriaID)

	UPDATE Crias
	SET SensorID = @SensorID
	WHERE CriaID = @CriaID

	END

