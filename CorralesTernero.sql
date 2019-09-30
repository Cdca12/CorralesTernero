CREATE DATABASE CorralesTernero;
USE CorralesTernero;

CREATE TABLE EstadoCria (
	EstadoCriaID int PRIMARY KEY,
	Descripcion varchar(255) NOT NULL
);

CREATE TABLE Peso (
	PesoID int PRIMARY KEY,
	CondicionCorporal varchar(255) NOT NULL
);

CREATE TABLE GrasaCobertura (
	GrasaCoberturaID int PRIMARY KEY,
	Descripcion varchar(255) NOT NULL
);

CREATE TABLE Musculo (
	MusculoID int PRIMARY KEY,
	ColorMusculo varchar(255) NOT NULL
);

CREATE TABLE Alimentos (
	AlimentoID int IDENTITY PRIMARY KEY,
	Descripcion varchar(255) NOT NULL,
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
	PresionArterial int NOT NULL,
	Respiracion int NOT NULL,
	Pulso int NOT NULL,
	Temperatura int NOT NULL
);

CREATE TABLE TipoCorral (
	TipoCorralID int PRIMARY KEY,
	Descripcion varchar(50) NOT NULL
);

CREATE TABLE Corrales (
	Transaccion int IDENTITY PRIMARY KEY,
	CorralID int NOT NULL INDEX IX_CorralID NONCLUSTERED,
	Ubicacion varchar(50) NOT NULL,
	TipoCorralID int FOREIGN KEY REFERENCES TipoCorral,
	FechaIngreso date NOT NULL DEFAULT CAST(GETDATE() as date),
	FechaEgreso date, --Default NULL
	DiasEnCorral int NOT NULL DEFAULT 0,
	
);

CREATE TABLE Crias (
	CriasID int IDENTITY PRIMARY KEY,
	EstadoCriaID int FOREIGN KEY REFERENCES EstadoCria,
	PesoID int FOREIGN KEY REFERENCES Peso,
	GrasaCoberturaID int FOREIGN KEY REFERENCES GrasaCobertura,
	MusculoID int FOREIGN KEY REFERENCES Musculo,
	DietaID int FOREIGN KEY REFERENCES Dietas,
	SensorID int FOREIGN KEY REFERENCES Sensores,
	CorralID int FOREIGN KEY REFERENCES Corrales, 
	VecesEnTratamiento int NOT NULL DEFAULT 0
);