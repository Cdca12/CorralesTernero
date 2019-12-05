--Procesar salidas de crias
CREATE PROCEDURE spProcesarSalidasCrias
	@CriaID int
AS

	BEGIN TRY

		BEGIN TRAN

		--A�adimos fecha de egreso a esas cr�as
		--Candado escritura impl�cito, no permite lecturas
		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE CriaID = @CriaID

		--Actualizamos datos de la cria, poniendo su EstadoCriaID a Procesado
		UPDATE Crias
		SET CorralID = NULL, EstadoCriaID = 4
		WHERE CriaID = @CriaID
	
		COMMIT TRAN	

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0 
		ROLLBACK TRANSACTION

	END CATCH