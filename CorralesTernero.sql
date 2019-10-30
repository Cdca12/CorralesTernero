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

CREATE TABLE Sensores (
	Señal int IDENTITY PRIMARY KEY,
	SensorID int NOT NULL INDEX IX_SensorID NONCLUSTERED,
	PresionArterial int,
	Respiracion int,
	Pulso int,
	Temperatura int
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
	EstadoID char(2) FOREIGN KEY REFERENCES Estados,
	TipoCorralID int FOREIGN KEY REFERENCES TipoCorral,
);

CREATE TABLE Crias (
	CriasID int IDENTITY PRIMARY KEY,
	CorralID int FOREIGN KEY REFERENCES Corrales, 
	Peso int NOT NULL,
	GrasaCoberturaID int FOREIGN KEY REFERENCES GrasaCobertura,
	MusculoID int FOREIGN KEY REFERENCES Musculo,
	EstadoCriaID int FOREIGN KEY REFERENCES EstadoCria DEFAULT 1,
	DietaID int FOREIGN KEY REFERENCES Dietas DEFAULT 1,
	SensorID int FOREIGN KEY REFERENCES Sensores NULL,
	VecesEnTratamiento int NOT NULL DEFAULT 0
);

CREATE TABLE TrasladosCrias (
	Transaccion int IDENTITY PRIMARY KEY,
	CorralID int FOREIGN KEY REFERENCES Corrales INDEX IX_CorralID NONCLUSTERED,
	CriasID int FOREIGN KEY REFERENCES Crias INDEX IX_CriasID NONCLUSTERED,
	FechaIngreso date NOT NULL DEFAULT CAST(GETDATE() as date),
	FechaEgreso date, --Default NULL
	DiasEnCorral int NOT NULL DEFAULT 0,
);