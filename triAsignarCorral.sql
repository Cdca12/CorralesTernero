CREATE TRIGGER triAsignarCorral
ON Crias

AFTER INSERT
AS

DECLARE 
	@CriasID int,
	@CorralID int;
	

SELECT @CriasID = CriasID, @CorralID = CorralID FROM inserted;

	BEGIN
		INSERT INTO TrasladosCrias (CorralID, CriasID)
		VALUES 
			(@CorralID, @CriasID);
	END

	