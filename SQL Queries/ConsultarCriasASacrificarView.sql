CREATE VIEW ConsultarCriasASacrificarView
AS
	SELECT * FROM TrasladosCrias
	WHERE CorralID IN (
		SELECT CorralID FROM Corrales
		WHERE TipoCorralID = 2 --Corrales Enfermos
	)
	AND FechaEgreso IS NULL
	AND DiasEnCorral >= 40