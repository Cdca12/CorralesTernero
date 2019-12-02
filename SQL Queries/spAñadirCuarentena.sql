CREATE PROCEDURE spAñadirCuarentena
	@CriaID int

AS
	BEGIN TRY
		BEGIN TRAN
	

		--Por el momento te manda al 2do corral, de Enfermos
		DECLARE @CorralID int = 2

		--Ciclo
		
		DECLARE @Transaccion int = (
			SELECT Transaccion FROM TrasladosCrias WITH(updlock)
			WHERE CriaID = @CriaID
			AND FechaEgreso IS NULL
		)

		--Actualizamos nuevo estado de la cria
		UPDATE Crias
		SET 
			CorralID = @CorralID,   --Cambia a corral de enfermos
			EstadoCriaID = 2		--Pasa a un estado de En Tratamiento
		WHERE CriaID = @CriaID

		--Registramos la salida del corral actual (historial)
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE Transaccion = @Transaccion


		--Registramos la entrada al nuevo corral (historial)
		INSERT INTO TrasladosCrias(CorralID, CriaID)
		VALUES (@CorralID, @CriaID)

		--Obtener info de la Cria, no es necesario el control de concurrencia
		DECLARE @DietaID int = (SELECT DietaID FROM Crias WHERE CriaID = @CriaID)
		DECLARE @AlimentoID int = (SELECT AlimentoID FROM Dietas WHERE DietaID = @DietaID)
		DECLARE @NombreAlimentoDieta varchar(max) = (SELECT Nombre FROM Alimentos WHERE AlimentoID = @AlimentoID)
		DECLARE @CantidadAlimentoDieta int = (SELECT Cantidad FROM Alimentos WHERE AlimentoID = @AlimentoID)
	
		--Creamos un nuevo alimento compuesto del anterior + medicinas
		INSERT INTO Alimentos WITH (UPDLOCK)	--Bloquea la tabla para asegurar que el MAX acceda a la última tupla
		VALUES (@NombreAlimentoDieta + ' con medicinas', @CantidadAlimentoDieta)

		--Obtenemos nuevo alimento de la nueva dieta
		SET @AlimentoID = (SELECT MAX(AlimentoID) FROM Alimentos)
	
		--Creamos una nueva dieta apartir del nuevo alimento
		INSERT INTO Dietas WITH (UPDLOCK)		--Bloquea la tabla para asegurar que el MAX acceda a la última tupla
		VALUES (40, @AlimentoID)

		SET @DietaID = (SELECT MAX(DietaID) FROM Dietas)

		--Actualizamos la Cria con su nueva dieta
		UPDATE Crias
		SET DietaID = @DietaID
		WHERE CriaID = @CriaID


		COMMIT TRAN
	
	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH
