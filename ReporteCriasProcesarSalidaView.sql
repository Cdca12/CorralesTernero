--Reporte Crias listas para procesar salida
ALTER VIEW ReporteCriasProcesarSalidaView
AS
	SELECT TC.CriaID, C.CorralID, TC.Peso, TC.Grasa, 
	G.Tipo as TipoGrasa, M.ColorMusculo as TipoMusculo, 
	D.DietaID, E.Descripcion as EstadoCria, TC.DiasEdad FROM 
	(SELECT * FROM Crias WHERE DiasEdad >= 150 AND EstadoCriaID = 1) AS TC
	INNER JOIN Corrales C ON TC.CorralID = C.CorralID
	INNER JOIN GrasaCobertura G ON TC.GrasaCoberturaID = G.GrasaCoberturaID
	INNER JOIN Musculo M ON TC.MusculoID = M.MusculoID
	INNER JOIN Dietas D ON TC.DietaID = D.DietaID
	INNER JOIN EstadoCria E ON TC.EstadoCriaID = E.EstadoCriaID