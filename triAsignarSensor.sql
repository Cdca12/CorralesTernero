CREATE TRIGGER triAsignarSensor
ON Crias

AFTER INSERT
AS
	BEGIN
		DECLARE @GrasaCoberturaCria int = (SELECT GrasaCoberturaID FROM inserted);
	
		-- Regla de negocio, si la grasa cobertura es 2 se le añade un sensor para monitoreo
		IF @GrasaCoberturaCria = 2 
			BEGIN
				
				INSERT INTO Sensores
				VALUES ((SELECT CriasID FROM inserted), NULL, NULL, NULL, NULL)

			END
	END