--Reporte de crías enfermas
CREATE VIEW ReporteCriasEnfermasView
AS

	SELECT C.CriasID, C.CorralID, C.Peso, G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	E.Descripcion as EstadoCria, D.DietaID, C.SensorID, C.VecesEnTratamiento 
	FROM (SELECT * FROM Crias WHERE EstadoCriaID = 2) as C
		INNER JOIN GrasaCobertura G ON C.GrasaCoberturaID = G.GrasaCoberturaID
		INNER JOIN Musculo M ON C.MusculoID = M.MusculoID
		INNER JOIN EstadoCria E ON C.EstadoCriaID = E.EstadoCriaID
		INNER JOIN Dietas D ON C.DietaID = D.DietaID
