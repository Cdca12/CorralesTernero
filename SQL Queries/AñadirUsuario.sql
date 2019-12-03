--En master db
CREATE LOGIN [login] WITH PASSWORD = '<password>';
CREATE USER [login] FROM LOGIN [login]

--En cada base de datos
CREATE USER [login] FROM LOGIN [login]
ALTER ROLE db_owner ADD MEMBER [login]