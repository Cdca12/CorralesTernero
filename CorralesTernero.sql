CREATE DATABASE CorralesTernero;
USE CorralesTernero;


CREATE TABLE EstadoCria (
	EstadoCriaID int PRIMARY KEY,
	Descripcion varchar(255) NOT NULL
);

CREATE TABLE GrasaCobertura (
	GrasaCoberturaID int PRIMARY KEY,
	Tipo varchar(255) NOT NULL,
	Descripcion varchar(255) NOT NULL
);

CREATE TABLE Musculo (
	MusculoID int PRIMARY KEY,
	ColorMusculo varchar(255) NOT NULL
);

CREATE TABLE Alimentos (
	AlimentoID int IDENTITY PRIMARY KEY,
	Nombre varchar(255) NOT NULL,
	Cantidad int NOT NULL
);

CREATE TABLE Dietas (
	DietaID int IDENTITY PRIMARY KEY,
	DiasTratamiento int NOT NULL,
	AlimentoID int FOREIGN KEY REFERENCES Alimentos
);

CREATE TABLE TipoCorral (
	TipoCorralID int PRIMARY KEY,
	Descripcion varchar(50) NOT NULL
);

CREATE TABLE Estados (
	EstadoID char(2) PRIMARY KEY,
	Nombre varchar(255) NOT NULL
);

CREATE TABLE Corrales (
	CorralID int PRIMARY KEY,
	EstadoID char(2) FOREIGN KEY REFERENCES Estados NOT NULL,
	TipoCorralID int FOREIGN KEY REFERENCES TipoCorral NOT NULL,
);

CREATE TABLE Sensores (
	SensorID int IDENTITY PRIMARY KEY,
	CriasID int NOT NULL,
);

CREATE TABLE SeñalesSensores (
	Señal int IDENTITY PRIMARY KEY,
	SensorID int FOREIGN KEY REFERENCES Sensores,
	PresionArterial int,
	Respiracion int,
	Pulso int,
	Temperatura int
);

CREATE TABLE Crias (
	CriasID int IDENTITY PRIMARY KEY,
	CorralID int FOREIGN KEY REFERENCES Corrales NOT NULL, 
	Peso int NOT NULL,
	Grasa int NOT NULL,
	GrasaCoberturaID int FOREIGN KEY REFERENCES GrasaCobertura NULL,
	MusculoID int FOREIGN KEY REFERENCES Musculo NOT NULL,
	SensorID int FOREIGN KEY REFERENCES Sensores NULL,
	DietaID int FOREIGN KEY REFERENCES Dietas DEFAULT 1,
	EstadoCriaID int FOREIGN KEY REFERENCES EstadoCria DEFAULT 1,
	DiasEdad int NOT NULL DEFAULT 0
);

CREATE TABLE TrasladosCrias (
	Transaccion int IDENTITY PRIMARY KEY,
	CorralID int FOREIGN KEY REFERENCES Corrales INDEX IX_CorralID NONCLUSTERED,
	CriasID int FOREIGN KEY REFERENCES Crias INDEX IX_CriasID NONCLUSTERED,
	FechaIngreso date NOT NULL DEFAULT CAST(GETDATE() as date),
	FechaEgreso date, --Default NULL
	DiasEnCorral int NOT NULL DEFAULT 0
);


--USE master