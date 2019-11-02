CREATE TRIGGER triAsignarSensor
ON Crias

AFTER INSERT
AS
	BEGIN
		DECLARE 
			@GrasaCoberturaCria int,
			@CriasID int,
			@SensorID int 

		SELECT @GrasaCoberturaCria = GrasaCoberturaID, @CriasID = CriasID, @SensorID = CriasID FROM inserted
	
		-- Regla de negocio, si la grasa cobertura es 2 se le añade un sensor para monitoreo
		IF @GrasaCoberturaCria = 2 
			BEGIN				
			--TODO: Hacer transaccion para atomizar el INSERT con el UPDATE
					INSERT INTO Sensores
					VALUES (@CriasID)

					SET @SensorID = (SELECT MAX(SensorID) FROM Sensores)

					UPDATE Crias
					SET SensorID = @SensorID
					WHERE CriasID = @CriasID

			END
	END