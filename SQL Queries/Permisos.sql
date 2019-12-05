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
	('Crías'),
	('Corrales'),
	('Alimentos'),
	('Dietas'),
	('Sensores'),
	('Simulaciones');

INSERT INTO MenuItem
VALUES
	('Añadir Crías', 1),
	('Consultar Crías', 1),
	('Reporte Crías Enfermas', 1),
	('Procesar Salidas', 1),
	('Sacrificar Crías', 1),

	('Añadir Corrales', 2),
	('Consultar Corrales', 2),

	('Añadir Alimentos', 3),
	('Consultar Alimentos', 3),

	('Añadir Dietas', 4),
	('Consultar Dietas', 4),

	('Analizar Señales', 5),

	('Simular Señales', 6),
	('Avanzar Días', 6);

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

--	--Armar el nombre de columnas dinámicamente
--	DECLARE @ComponentID varchar(max) = @Component + 'ID'
--	DECLARE @ComponentName varchar(max) = @Component + 'Name'

--	--Ejecutar SQL dinámico
--	DECLARE @SQL varchar(max) = 'SELECT DISTINCT ' + @ComponentID + ', ' + @ComponentName + ' FROM ObtenerConfiguracionView WHERE Username = ''' + @Username + ' '' '
--	EXECUTE(@SQL)
	
--GO

--Test
--SELECT MenuID, MenuName FROM ObtenerConfiguracionView 
----WHERE Username = 'usrAdministrador'
--WHERE Username = 'usrVeterinario'
--GROUP BY MenuID, MenuName






