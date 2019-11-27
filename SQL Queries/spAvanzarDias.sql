CREATE PROCEDURE spAvanzarDias
	@Dias int
AS
	BEGIN TRY
		BEGIN TRAN

		DECLARE @ListaCriasActivas TABLE (CriaID int)

		--Obtiene Crias vivas y pone candado para que los demás procesos no obtengan una Edad desactualizada
		INSERT INTO @ListaCriasActivas
		SELECT CriaID FROM Crias WITH (UPDLOCK)
		WHERE EstadoCriaID = 1 OR EstadoCriaID = 2

		--Aumenta edad en días a las Crias vivas
		UPDATE Crias
		SET DiasEdad = DiasEdad + @Dias
		WHERE CriaID IN (
			SELECT CriaID FROM @ListaCriasActivas
		)

		--Aumenta días en corral, ya sea sanos o enfermos
		UPDATE TrasladosCrias
		SET DiasEnCorral = DiasEnCorral + @Dias
		WHERE FechaEgreso IS NULL

		COMMIT TRAN

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH