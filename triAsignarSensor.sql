CREATE TRIGGER triAsignarSensor
ON Crias

AFTER INSERT
AS
	BEGIN
		DECLARE 
			@GrasaCoberturaCria int,
			@CriaID int,
			@SensorID int 

		SELECT @GrasaCoberturaCria = GrasaCoberturaID, @CriaID = CriaID, @SensorID = CriaID FROM inserted
	
		-- Regla de negocio, si la grasa cobertura es 2 se le añade un sensor para monitoreo
		IF @GrasaCoberturaCria = 2 
			BEGIN				
			--TODO: Hacer transaccion para atomizar el INSERT con el UPDATE
					INSERT INTO Sensores
					VALUES (@CriaID)
					
					--Hacer otro trigger o sp para controlar la concurrencia de este SELECT y asegurar el valor en el UPDATE siguiente
					SET @SensorID = (SELECT MAX(SensorID) FROM Sensores)

					UPDATE Crias
					SET SensorID = @SensorID
					WHERE CriaID = @CriaID

			END
	END