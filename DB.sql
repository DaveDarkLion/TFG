DROP DATABASE IF EXISTS gestion_ejercicios_programacion;
CREATE DATABASE gestion_ejercicios_programacion;
USE gestion_ejercicios_programacion;

-- Generación de tablas

SET NAMES utf8mb4;

CREATE TABLE persona (

	email CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci PRIMARY KEY,
	nombre CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
	apellIdo1 CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
	apellIdo2 CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    password CHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE alumno (

	email CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci PRIMARY KEY,
	
	FOREIGN KEY (email) REFERENCES persona(email)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE profesor (

	email CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci PRIMARY KEY,
	
	FOREIGN KEY (email) REFERENCES persona(email)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE administrador (

	email CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci PRIMARY KEY,
	
	FOREIGN KEY (email) REFERENCES persona(email)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE dificultad (

	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    valor FLOAT NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE categoria (

	id INT AUTO_INCREMENT PRIMARY KEY,
	nombre CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE ejercicio (

	id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	enunciado VARCHAR(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	profesorEmail CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	dificultadId INT,
    visib BOOL,
	
	FOREIGN KEY (profesorEmail) REFERENCES profesor(email),
	FOREIGN KEY (dificultadId) REFERENCES dificultad(Id)
	
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

ALTER DATABASE gestion_ejercicios_programacion CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;

CREATE TABLE dificultadAlumnoEjercicio (

	alumnoEmail CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	ejercicioId INT,
	dificultadId INT NOT NULL,

	PRIMARY KEY (alumnoEmail, ejercicioId),
	FOREIGN KEY (alumnoEmail) REFERENCES alumno(email),
	FOREIGN KEY (dificultadId) REFERENCES dificultad(Id),
	FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id)
	
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE categoriaEjercicio (

	categoriaId INT,
	ejercicioId INT,
	
	PRIMARY KEY (categoriaId, ejercicioId),
	FOREIGN KEY (categoriaId) REFERENCES categoria(Id),
	FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id)
	
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE idea (

	id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	ideaText VARCHAR(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
    profesorEmail CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	
	FOREIGN KEY (profesorEmail) REFERENCES profesor(email)

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE archivo (

	id INT AUTO_INCREMENT PRIMARY KEY,
	ruta char(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci -- Se utlilza la ruta del archivo

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE archivoEjercicioEntrada (

	archivoId INT,
	ejercicioId INT,
	
	PRIMARY KEY (archivoId, ejercicioId),
	FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id),
	FOREIGN KEY (archivoId) REFERENCES archivo(Id)
	
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE archivoEjercicioValIdacion (

	archivoId INT,
	ejercicioId INT,
	
	PRIMARY KEY (archivoId, ejercicioId),
	FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id),
	FOREIGN KEY (archivoId) REFERENCES archivo(Id)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE archivoEjercicioSolucion (

	archivoId INT,
	ejercicioId INT NOT NULL,
	
	PRIMARY KEY (archivoId, ejercicioId),
	FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id),
	FOREIGN KEY (archivoId) REFERENCES archivo(Id)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE ejercicioPersona (

	ejercicioId INT,
    personaEmail CHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
    posit INT,
    
    PRIMARY KEY (ejercicioId, personaEmail),
    FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id),
    FOREIGN KEY (personaEmail) REFERENCES persona(email)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE titulacion (

	id INT AUTO_INCREMENT PRIMARY KEY PRIMARY KEY,
	nombre VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE practica (

	id INT AUTO_INCREMENT PRIMARY KEY,
	mes INT(2),
	ano INT(4),
    descripcion VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	titulacionId INT,
    visib BOOL,
    abierto BOOL,
	
	FOREIGN KEY (titulacionId) REFERENCES titulacion(Id)

);

CREATE TABLE ejercicioPractica (

	ejercicioId INT,
	practicaId INT,
	posit INT,
    
	PRIMARY KEY (ejercicioId, practicaId),
	FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id),
	FOREIGN KEY (practicaId) REFERENCES practica(Id)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE practicaEvaluacion (

	id INT AUTO_INCREMENT PRIMARY KEY,
	mes INT(2) NOT NULL,
	ano INT(4) NOT NULL,
	descripcion VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	titulacionId INT,
    visib BOOL,
    abierto BOOL,
	
	FOREIGN KEY (titulacionId) REFERENCES titulacion(Id)

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE ejercicioPracticaEvaluacion (

	ejercicioId INT,
	practicaEvaluacionId INT,
    posit INT,
	
	PRIMARY KEY (ejercicioId, practicaEvaluacionId),
	FOREIGN KEY (ejercicioId) REFERENCES ejercicio(Id),
	FOREIGN KEY (practicaEvaluacionId) REFERENCES practicaEvaluacion(id)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE examen (

	id INT AUTO_INCREMENT PRIMARY KEY,
	mes INT(2),
	ano INT(4),
	descripcion VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
	titulacionId INT,
    visib BOOL,
    abierto BOOL,
	
	FOREIGN KEY (titulacionID) REFERENCES titulacion(id)

) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;

CREATE TABLE ejercicioExamen (

	ejercicioId INT,
	examenId INT,
    posit INT,
	
	PRIMARY KEY (ejercicioID, examenID),
	FOREIGN KEY (ejercicioID) REFERENCES ejercicio(id),
	FOREIGN KEY (examenID) REFERENCES examen(id)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER DATABASE gestion_ejercicios_programacion CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci;