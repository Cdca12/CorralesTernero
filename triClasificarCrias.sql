CREATE TRIGGER triClasificarCrias
ON Crias

AFTER INSERT
AS
	
DECLARE
	@CriaID int,
	@Peso int,
	@Grasa int,
	@MusculoID int,
	@GrasaCoberturaID int

	SELECT @CriaID = CriaID, @Peso = Peso, @Grasa = Grasa, @MusculoID = MusculoID FROM inserted
	
	--Clasificar

	IF @MusculoID = 1 --Carnes Rojas
	BEGIN
		IF @Peso < 50
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 8 THEN 0
				WHEN @Grasa BETWEEN 9 AND 11 THEN 1
				WHEN @Grasa BETWEEN 12 AND 20 THEN 2
				WHEN @Grasa > 20 THEN 3
			END 
		END
		
		IF @Peso BETWEEN 50 AND 70
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 13 THEN 0
				WHEN @Grasa BETWEEN 14 AND 16 THEN 1
				WHEN @Grasa BETWEEN 17 AND 25 THEN 2
				WHEN @Grasa > 25 THEN 3
			END
		END
		IF @Peso > 70
		BEGIN	
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 16 THEN 0
				WHEN @Grasa BETWEEN 17 AND 25 THEN 1
				WHEN @Grasa BETWEEN 26 AND 35 THEN 2
				WHEN @Grasa > 35 THEN 3
			END
		END
	END

	ELSE --Carnes Blancas
	BEGIN

		IF @Peso < 60
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 8 THEN 0
				WHEN @Grasa BETWEEN 9 AND 11 THEN 1
				WHEN @Grasa BETWEEN 12 AND 20 THEN 2
				WHEN @Grasa > 20 THEN 3
			END 
		END

		IF @Peso BETWEEN 60 AND 80
		BEGIN
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 13 THEN 0
				WHEN @Grasa BETWEEN 14 AND 16 THEN 1
				WHEN @Grasa BETWEEN 17 AND 25 THEN 2
				WHEN @Grasa > 25 THEN 3
			END
		END

		IF @Peso > 80
		BEGIN	
			SELECT @GrasaCoberturaID =
			CASE
				WHEN @Grasa < 16 THEN 0
				WHEN @Grasa BETWEEN 17 AND 25 THEN 1
				WHEN @Grasa BETWEEN 26 AND 35 THEN 2
				WHEN @Grasa > 35 THEN 3
			END
		END

	END

		
	--Actualizar GrasaCobertura
	UPDATE Crias
	SET GrasaCoberturaID = @GrasaCoberturaID
	WHERE CriaID = @CriaID

	--Asignar Sensor en base a la GrasaCobertura actualizada
	IF @GrasaCoberturaID = 2
	EXECUTE spAsignarSensor @CriaID
