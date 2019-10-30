--Reporte Crias listas para procesar salida
CREATE VIEW ReporteCriasProcesarSalidaView
AS

	SELECT C.CriasID, C.CorralID, C.Peso, G.Tipo as GrasaCobertura, M.ColorMusculo as TipoMusculo, 
	E.Descripcion as EstadoCria, C.DiasEnCorral FROM
	(SELECT C1.*, T1.DiasEnCorral FROM Crias C1
	INNER JOIN (SELECT * FROM TrasladosCrias
	WHERE DiasEnCorral >= 150) as T1 -- 5 meses
	ON C1.CriasID = T1.CriasID) as C
	INNER JOIN GrasaCobertura G ON C.GrasaCoberturaID = G.GrasaCoberturaID
	INNER JOIN Musculo M ON C.MusculoID = M.MusculoID
	INNER JOIN EstadoCria E ON C.EstadoCriaID = E.EstadoCriaID
	WHERE CorralID IS NOT NULL
	
		