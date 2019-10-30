CREATE TRIGGER triAsignarCorral
ON Crias

AFTER INSERT
AS
	BEGIN
	
		INSERT INTO TrasladosCrias
		VALUES (
			(SELECT CorralID FROM inserted),
			(SELECT CriasID FROM inserted),
			DEFAULT,
			NULL,
			0
		)
	END

	