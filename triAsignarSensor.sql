ALTER TRIGGER triAsignarSensor
ON Crias

AFTER INSERT
AS
	BEGIN
		DECLARE @GrasaCoberturaCria int = (SELECT GrasaCoberturaID FROM inserted);
		DECLARE @CriasID int = (SELECT CriasID FROM inserted)
		DECLARE @SensorID int = @CriasID
	
		-- Regla de negocio, si la grasa cobertura es 2 se le añade un sensor para monitoreo
		IF @GrasaCoberturaCria = 2 
			BEGIN				
					INSERT INTO Sensores
					VALUES (@CriasID)

					SET @SensorID = (SELECT MAX(SensorID) FROM Sensores )

					UPDATE Crias
					SET SensorID = @SensorID
					WHERE CriasID = @CriasID

			END
	END