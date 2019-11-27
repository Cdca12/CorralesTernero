CREATE PROCEDURE spSacrificarCrias
AS

	DECLARE @CriasIDASacrificar TABLE (
		CriaID int,
		Transaccion int
	)

	BEGIN TRY
		BEGIN TRAN

		INSERT INTO @CriasIDASacrificar 
		SELECT CriaID, Transaccion FROM TrasladosCrias
		WHERE CorralID IN (
			SELECT CorralID FROM Corrales
			WHERE TipoCorralID = 2 --Corrales Enfermos
		)
		AND FechaEgreso IS NULL
		AND DiasEnCorral >= 40
	
		--Poner CorralID null o dejar historial del ultimo corral (?
		UPDATE Crias
		SET EstadoCriaID = 3
		WHERE CriaID IN (
			SELECT CriaID FROM @CriasIDASacrificar
		)

		UPDATE TrasladosCrias
		SET FechaEgreso = GETDATE()
		WHERE Transaccion IN (
			SELECT Transaccion FROM @CriasIDASacrificar
		)

		COMMIT TRAN

	END TRY
	BEGIN CATCH

		IF @@TRANCOUNT > 0
		ROLLBACK TRAN

	END CATCH