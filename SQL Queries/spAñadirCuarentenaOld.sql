--ALTER PROCEDURE spAñadirCuarentena

--AS
	
	DECLARE @ListaCriaID TABLE (
		CriaID int
	)

	INSERT INTO @ListaCriaID
	SELECT DISTINCT CriaID FROM ReporteCriasPropensaEnfermarseView

	--Por el momento te manda al 2do corral, de Enfermos
	DECLARE @CorralID int = 2

	--BEGIN TRY
	--	BEGIN TRAN

		DECLARE @ListaTransaccion TABLE (
			Transaccion int
		)

		INSERT INTO @ListaTransaccion
		SELECT Transaccion FROM TrasladosCrias WITH(updlock)
		WHERE CriaID IN (
			SELECT CriaID FROM @ListaCriaID
		)
		AND FechaEgreso IS NULL

		--Test
		SELECT * FROM @ListaTransaccion


		--COMMIT TRAN
	
	--END TRY
	--BEGIN CATCH

	--	IF @@TRANCOUNT > 0
	--	ROLLBACK TRAN
	--END CATCH

		--Test
		BEGIN TRAN
	
		--Actualizamos estado de las Crías a uno en Cuarentena
		UPDATE Crias
		SET CorralID = @CorralID,   --Cambia a corral de enfermos
		EstadoCriaID = 2			--Pasa a un estado de En Tratamiento
		WHERE CriaID IN (SELECT CriaID FROM @ListaCriaID)
	
		--En el registro de traslados, registrar que se salió del corral actual
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE Transaccion IN (SELECT Transaccion FROM @ListaTransaccion)

		--Insertar nuevo traslado a un corral de cuarentena
		INSERT INTO TrasladosCrias(CorralID, CriaID)
		SELECT @CorralID, CriaID FROM @ListaCriaID


		--Obtener datos sin problema de concurrencia en tablas, ya que pueden ser uno o más datos correspondientes a sus tuplas
		DECLARE @ListaDietaID TABLE (DietaID int) 
		INSERT INTO @ListaDietaID 
		SELECT DietaID FROM Crias WHERE CriaID IN (SELECT CriaID FROM @ListaCriaID)


		DECLARE @ListaAlimentoID TABLE (AlimentoID int) 
		INSERT INTO @ListaAlimentoID
		SELECT AlimentoID FROM Dietas WHERE DietaID IN (SELECT DietaID FROM @ListaDietaID)


		DECLARE @ListaNombreAlimentoDieta TABLE (NombreAlimentoDieta varchar(max)) 
		INSERT INTO @ListaNombreAlimentoDieta
		SELECT Nombre FROM Alimentos WHERE AlimentoID IN (SELECT AlimentoID FROM @ListaAlimentoID)


		DECLARE @ListaCantidadAlimentoDieta TABLE (CantidadAlimentoDieta int) 
		INSERT INTO @ListaCantidadAlimentoDieta
		SELECT Cantidad FROM Alimentos WHERE AlimentoID IN (SELECT AlimentoID FROM @ListaAlimentoID)

		

		--Insertar en la tabla Alimentos el nuevo medicamento que se compone del alimento + medicinas
		UPDATE Alimentos
		SET Nombre = (SELECT NombreAlimentoDieta + ' con medicinas' FROM @ListaNombreAlimentoDieta)
		WHERE AlimentoID = (SELECT AlimentoID FROM @ListaAlimentoID)
		

	



		SELECT * FROM Dietas
		SELECT * FROM Alimentos
		SELECT * FROM Crias

			RETURN;

		--Enlazamos cria con su dieta correspondiente
		DECLARE @DietaCria TABLE (
			DietaID int,
			CriaID int
		)



		INSERT INTO Alimentos
		SELECT 
			(SELECT NombreAlimentoDieta + ' con medicinas' FROM @ListaNombreAlimentoDieta),
			(SELECT CantidadAlimentoDieta FROM @ListaCantidadAlimentoDieta)

		--Actualizamos los datos de las crias con su nueva Dieta
		--UPDATE Crias
		--SET DietaID = (SELECT DietaID FROM @NuevasDietas)
		--WHERE CriaID IN (SELECT CriaID FROM @ListaCriaID)

	

		

		--ROLLBACK TRAN