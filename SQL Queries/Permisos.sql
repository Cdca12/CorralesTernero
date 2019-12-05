 CREATE TABLE Menu (
	MenuID int IDENTITY PRIMARY KEY,
	Name varchar(max)
)

CREATE TABLE MenuItem (
	MenuItemID int IDENTITY PRIMARY KEY,
	Name varchar(max),
	MenuID int FOREIGN KEY REFERENCES Menu
)

CREATE TABLE Usuarios (
	UsuarioID int,
	Username varchar(max),
	MenuItemID int FOREIGN KEY REFERENCES MenuItem,
	PRIMARY KEY (UsuarioID, MenuItemID)
)
GO

INSERT INTO Menu
VALUES
	('Cr�as'),
	('Corrales'),
	('Alimentos'),
	('Dietas'),
	('Sensores'),
	('Simulaciones');

INSERT INTO MenuItem
VALUES
	('A�adir Cr�as', 1),
	('Consultar Cr�as', 1),
	('Reporte Cr�as Enfermas', 1),
	('Procesar Salidas', 1),
	('Sacrificar Cr�as', 1),

	('A�adir Corrales', 2),
	('Consultar Corrales', 2),

	('A�adir Alimentos', 3),
	('Consultar Alimentos', 3),

	('A�adir Dietas', 4),
	('Consultar Dietas', 4),

	('Analizar Se�ales', 5),

	('Simular Se�ales', 6),
	('Avanzar D�as', 6);

GO

--Insertar Usuarios con sus permisos
INSERT INTO Usuarios
SELECT 1, 'usrAdministrador', MenuItemID FROM MenuItem

INSERT INTO Usuarios
SELECT 2, 'usrEncargado', MenuItemID FROM MenuItem
WHERE MenuItemID IN (
	1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 
) 

INSERT INTO Usuarios
SELECT 3, 'usrVeterinario', MenuItemID FROM MenuItem
WHERE MenuItemID IN (
	2, 3, 7, 8, 9, 10, 11, 12
)

GO


CREATE VIEW ObtenerConfiguracionView
AS

	SELECT M.MenuID, M.Name as MenuName, T.MenuItemID, T.Name as MenuItemName, T.Username FROM
	(SELECT U.UsuarioID, U.Username, MI.MenuItemID, MI.Name, MI.MenuID FROM Usuarios U
	INNER JOIN MenuItem MI ON U.MenuItemID = MI.MenuItemID) as T
	INNER JOIN Menu M ON T.MenuID = M.MenuID

GO


--CREATE PROCEDURE spObtenerConfiguracion
--	@Username varchar(max),
--	@Component varchar(max)
--AS

--	--Armar el nombre de columnas din�micamente
--	DECLARE @ComponentID varchar(max) = @Component + 'ID'
--	DECLARE @ComponentName varchar(max) = @Component + 'Name'

--	--Ejecutar SQL din�mico
--	DECLARE @SQL varchar(max) = 'SELECT DISTINCT ' + @ComponentID + ', ' + @ComponentName + ' FROM ObtenerConfiguracionView WHERE Username = ''' + @Username + ' '' '
--	EXECUTE(@SQL)
	
--GO

--Test
--SELECT MenuID, MenuName FROM ObtenerConfiguracionView 
----WHERE Username = 'usrAdministrador'
--WHERE Username = 'usrVeterinario'
--GROUP BY MenuID, MenuName






