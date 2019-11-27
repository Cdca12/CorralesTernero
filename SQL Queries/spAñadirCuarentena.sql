CREATE PROCEDURE spAñadirCuarentena

AS
	BEGIN
	
	DECLARE @CriaID int = (SELECT TOP(1) CriaID FROM ReporteCriasPropensaEnfermarseView)

	--Por el momento te manda al 2do corral, de Enfermos
	DECLARE @CorralID int = 2

	DECLARE @Transaccion int = (SELECT MAX(Transaccion) FROM TrasladosCrias WITH(updlock) WHERE CriaID = @CriaID)

	
	UPDATE Crias
	SET CorralID = @CorralID,   --Cambia a corral de enfermos
	EstadoCriaID = 2			--Pasa a un estado de En Tratamiento
	WHERE CriaID = @CriaID

	UPDATE TrasladosCrias
	SET FechaEgreso = GETDATE()
	WHERE Transaccion = @Transaccion

	INSERT INTO TrasladosCrias(CorralID, CriaID)
	VALUES (@CorralID, @CriaID)

	DECLARE @DietaID int = (SELECT DietaID FROM Crias WHERE CriaID = @CriaID)
	DECLARE @AlimentoID int = (SELECT AlimentoID FROM Dietas WHERE DietaID = @DietaID)
	DECLARE @NombreAlimentoDieta varchar(max) = (SELECT Nombre FROM Alimentos WHERE AlimentoID = @AlimentoID)
	DECLARE @CantidadAlimentoDieta int = (SELECT Cantidad FROM Alimentos WHERE AlimentoID = @AlimentoID)
	

	INSERT INTO Alimentos
	VALUES (@NombreAlimentoDieta + ' con medicinas', @CantidadAlimentoDieta)
	
	INSERT INTO Dietas
	VALUES (40, @AlimentoID)

	SET @DietaID = (SELECT MAX(DietaID) FROM Dietas)

	UPDATE Crias
	SET DietaID = @DietaID
	WHERE CriaID = @CriaID
	
	END


	