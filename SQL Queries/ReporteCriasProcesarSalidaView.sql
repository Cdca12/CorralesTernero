--Reporte Crias listas para procesar salida
CREATE VIEW ReporteCriasProcesarSalidaView
AS
	SELECT TC.CriaID, C.CorralID, TC.Peso, TC.Grasa, 
	G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	E.Descripcion as EstadoCria, TC.DiasEdad FROM 
	(SELECT * FROM Crias WHERE EstadoCriaID = 1 AND DiasEdad >= 150 AND) AS TC
	INNER JOIN Corrales C ON TC.CorralID = C.CorralID
	INNER JOIN GrasaCobertura G ON TC.GrasaCoberturaID = G.GrasaCoberturaID
	INNER JOIN Musculo M ON TC.MusculoID = M.MusculoID
	INNER JOIN EstadoCria E ON TC.EstadoCriaID = E.EstadoCriaID