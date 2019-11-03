CREATE VIEW ReporteCriasPropensaEnfermarseView
AS
	SELECT S.*, C.CriaID FROM Se�alesSensores S
	INNER JOIN Crias C ON S.SensorID = C.SensorID
	WHERE Temperatura >= 40
	OR PresionArterial >= 120
	OR Pulso >= 90