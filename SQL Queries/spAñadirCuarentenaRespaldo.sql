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

		--Respaldo de la tabla actual de Alimentos
		DECLARE @AlimentosIDRespaldo TABLE (AlimentoID int)
		INSERT @AlimentosIDRespaldo
		SELECT AlimentoID FROM Alimentos

		--Insertar en la tabla Alimentos el nuevo medicamento que se compone del alimento + medicinas
		INSERT INTO Alimentos
		SELECT 
			(SELECT NombreAlimentoDieta + ' con medicinas' FROM @ListaNombreAlimentoDieta),
			(SELECT CantidadAlimentoDieta FROM @ListaCantidadAlimentoDieta)


		--Obtenemos diferencia entre la tabla actual y la del respaldo, es decir, obtendremos los nuevos alimentos insertados
		DECLARE @NuevosAlimentos TABLE (AlimentoID int)
		INSERT INTO @NuevosAlimentos
		SELECT (
			SELECT AlimentoID FROM Alimentos
			EXCEPT
			SELECT AlimentoID FROM @AlimentosIDRespaldo
		)


		--Respaldo de la tabla actual de Dietas
		DECLARE @DietasIDRespaldo TABLE (DietaID int)
		INSERT @DietasIDRespaldo
		SELECT DietaID FROM Dietas
		
		--Creamos una nueva dieta con este nuevo alimento + medicinas	
		INSERT INTO Dietas
		SELECT
			40,
			(SELECT AlimentoID FROM @ListaAlimentoID)

		--Obtenemos diferencia entre la tabla actual y la del respaldo, es decir, obtendremos las nuevas dietas insertadas
		DECLARE @NuevasDietas TABLE (DietaID int)
		INSERT INTO @NuevasDietas
		SELECT (
			SELECT DietaID FROM Dietas
			EXCEPT
			SELECT DietaID FROM @DietasIDRespaldo
		)


		SELECT * FROM Dietas
		SELECT * FROM Alimentos
		SELECT * FROM @NuevosAlimentos
		SELECT * FROM @NuevasDietas
		SELECT * FROM Crias



		--Enlazamos cria con su dieta correspondiente
		DECLARE @DietaCria TABLE (
			DietaID int,
			CriaID int
		)


		SELECT DietaID = 

		INSERT INTO @DietaCria
		SELECT 
			(SELECT DietaID FROM @NuevasDietas),
			(SELECT CriaID FROM @ListaCriaID)
		

		SELECT * FROM @DietaCria


		INSERT INTO Alimentos
		SELECT 
			(SELECT NombreAlimentoDieta + ' con medicinas' FROM @ListaNombreAlimentoDieta),
			(SELECT CantidadAlimentoDieta FROM @ListaCantidadAlimentoDieta)

		--Actualizamos los datos de las crias con su nueva Dieta
		--UPDATE Crias
		--SET DietaID = (SELECT DietaID FROM @NuevasDietas)
		--WHERE CriaID IN (SELECT CriaID FROM @ListaCriaID)

	

		

		--ROLLBACK TRAN