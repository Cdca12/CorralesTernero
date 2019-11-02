CREATE TRIGGER triAsignarCorral
ON Crias

AFTER INSERT
AS

DECLARE 
	@CriaID int,
	@CorralID int;
	

SELECT @CriaID = CriaID, @CorralID = CorralID FROM inserted;

	BEGIN
		INSERT INTO TrasladosCrias (CorralID, CriaID)
		VALUES 
			(@CorralID, @CriaID);
	END

	