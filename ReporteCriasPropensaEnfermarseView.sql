ALTER VIEW ReporteCriasPropensaEnfermarseView
AS
	SELECT SS.*, C.CriaID FROM
	(SELECT * FROM SeñalesSensores 
	WHERE Temperatura >= 40
	OR PresionArterial >= 120
	OR Pulso >= 90) AS SS
	INNER JOIN (SELECT * FROM Crias WHERE EstadoCriaID = 1) AS C ON SS.SensorID = C.SensorID