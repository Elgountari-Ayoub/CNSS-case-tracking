-- Database: maCNSSdb


CREATE DATABASE "maCNSSdb"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'English_United States.1252'
    LC_CTYPE = 'English_United States.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	
-- Create the agentCnss table
CREATE TABLE agentCnss (
    cnss varchar(255) PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    ville VARCHAR(255),
    telephone VARCHAR(15),
    email VARCHAR(255),
    password VARCHAR(255),
    genre VARCHAR(10)
);
-- Create the patient table
CREATE TABLE patient (
    immatricule varchar(255) PRIMARY KEY,
    cnss varchar(255),
    nom VARCHAR(255),
    prenom VARCHAR(255),
    ville VARCHAR(255),
    telephone VARCHAR(15),
    email VARCHAR(255),
    password VARCHAR(255),
    genre VARCHAR(10)
    
);

-- Create the admin table
CREATE TABLE admin (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    pass VARCHAR(255)
);

-- Create the dossier table
CREATE TABLE dossier (
    matricule int PRIMARY KEY,
    etat VARCHAR(50),
    agentCnss_cnss VARCHAR(255),
    patient_immatricule varchar(255),
    FOREIGN KEY (agentCnss_cnss) REFERENCES agentCnss (cnss),
    FOREIGN KEY (patient_immatricule) REFERENCES patient (immatricule)
);

-- Create the medcine table
CREATE TABLE medcine (
    INPE VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
	nom VARCHAR(255),
	prenom VARCHAR(255),
	type VARCHAR(255)
);

-- Create the organism table
CREATE TABLE laboratoire (
    INPE VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
	label varchar(255) 
);

-- Create the organism table
CREATE TABLE radiologie (
    INPE VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
	label varchar(255) 
);

-- Create the Radio table
CREATE TABLE radio (
    id int PRIMARY KEY,
	radiologie_INPE varchar(255),
    prix DECIMAL(10, 2),
    taux DECIMAL(5, 2),
    description TEXT,
    dossier_matricule INT,
	type varchar(255),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
    FOREIGN KEY (radiologie_INPE) REFERENCES radiologie (INPE)
);

-- Create the Scanner table
CREATE TABLE scanner (
    id int PRIMARY KEY,
	radiologie_INPE varchar(255),
    prix DECIMAL(10, 2),
    taux DECIMAL(5, 2),
    description TEXT,
    dossier_matricule INT,
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
	FOREIGN KEY (radiologie_INPE) REFERENCES radiologie (INPE)
);

-- Create the analyse table
CREATE TABLE analysee (
    id int PRIMARY KEY,
	laboratoire_INPE varchar(255),
    prix DECIMAL(10, 2),
    taux DECIMAL(5, 2),
    description TEXT,
    dossier_matricule INT,
	type varchar(255),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
	FOREIGN KEY (laboratoire_INPE) REFERENCES laboratoire (INPE)
);

-- Create the ordonnance table
CREATE TABLE ordonnance (
    id int PRIMARY KEY,
    prix DECIMAL(10, 2),
    taux DECIMAL(5, 2),
    description TEXT,
    dossier_matricule INT,
	medcine_INPE varchar(255),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
    FOREIGN KEY (medcine_INPE) REFERENCES medcine (INPE)
);

-- Create the categorie table
CREATE TABLE categorie (
    id SERIAL PRIMARY KEY,
    taux DECIMAL(5, 2),
    nom VARCHAR(255)
);

-- Create the medicament table
CREATE TABLE medicament (
    code_bare VARCHAR(50) PRIMARY KEY,
    prix DECIMAL(10, 2),
    nom VARCHAR(255),
    dossier_matricule INT,
    categorie_id INT,
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
    FOREIGN KEY (categorie_id) REFERENCES categorie (id)
);


