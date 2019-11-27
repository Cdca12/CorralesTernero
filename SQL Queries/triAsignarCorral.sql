CREATE TRIGGER triAsignarCorral
ON Crias

AFTER INSERT
AS

	INSERT INTO TrasladosCrias (CorralID, CriaID)
	SELECT CorralID, CriaID FROM inserted