-- Database: maCNSSdb

-- DROP DATABASE IF EXISTS "maCNSSdb";

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
    cnss SERIAL PRIMARY KEY,
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
    immatricule SERIAL PRIMARY KEY,
    cnss INT,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    ville VARCHAR(255),
    telephone VARCHAR(15),
    email VARCHAR(255),
    password VARCHAR(255),
    genre VARCHAR(10)
    
);

-- Create the dossier table
CREATE TABLE dossier (
    matricule int PRIMARY KEY,
    etat VARCHAR(50),
    agentCnss_cnss INT,
    patient_immatricule INT,
    FOREIGN KEY (agentCnss_cnss) REFERENCES agentCnss (cnss),
    FOREIGN KEY (patient_immatricule) REFERENCES patient (immatricule)
);

-- Create the document table
CREATE TABLE document (
    id int PRIMARY KEY,
    prix DECIMAL(10, 2),
    taux DECIMAL(5, 2),
    description TEXT,
    dossier_matricule INT,
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule)
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

-- Create the laboratoire table
CREATE TABLE laboratoire (
    id SERIAL PRIMARY KEY,
    label VARCHAR(255)
);

-- Create the medcine table
CREATE TABLE medcine (
    INPE VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
	nom VARCHAR(255),
	prenom VARCHAR(255),
	type VARCHAR(255)
);