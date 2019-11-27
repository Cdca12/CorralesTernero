CREATE VIEW ConsultarCriasView
AS
	SELECT C.CriaID, C.CorralID, C.Peso, C.Grasa, G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	C.SensorID, C.DietaID, E.Descripcion as EstadoCria, C.DiasEdad FROM Crias C
	INNER JOIN GrasaCobertura G ON C.GrasaCoberturaID = G.GrasaCoberturaID
	INNER JOIN Musculo M ON C.MusculoID = M.MusculoID
	INNER JOIN EstadoCria E ON C.EstadoCriaID = E.EstadoCriaID
