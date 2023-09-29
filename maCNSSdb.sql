
-- Create the agentCnss table
CREATE TABLE agentCnss (
    cin varchar(255) PRIMARY KEY,
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
    cin varchar(255),
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
    agentCnss_cin VARCHAR(255),
    patient_immatricule varchar(255),
    remboursement decimal (10,2),
    FOREIGN KEY (agentCnss_cin) REFERENCES agentCnss (cin),
    FOREIGN KEY (patient_immatricule) REFERENCES patient (immatricule)
);


-- Create the medcineType table
CREATE TABLE medcineType (
    type VARCHAR(255) PRIMARY KEY,
    taux DECIMAL(10, 2)
    
);


-- Create the medcine table
CREATE TABLE medcine (
    INPE VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
	nom VARCHAR(255),
	prenom VARCHAR(255),
	type VARCHAR(255),
	FOREIGN KEY (type) REFERENCES medcineType (type)
	
);


-- Create the LABORATOIRE organism table
CREATE TABLE laboratoire (
    INPE VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
	label varchar(255) 
);

-- Create the RADIOLOGIE organism table
CREATE TABLE radiologie (
    INPE VARCHAR(50) PRIMARY KEY,
    address VARCHAR(255),
	label varchar(255)      
);


-- Create the RADIO TYPE table
CREATE TABLE radiotype (
	type VARCHAR(255) PRIMARY KEY,
    taux DECIMAL(5, 2)
);


-- Create the Radio table
CREATE TABLE radio (
    id Serial PRIMARY KEY,
	radiologie_INPE varchar(255),
    prix DECIMAL(10, 2),
    
    description TEXT,
    dossier_matricule INT,
	type VARCHAR(255),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
    FOREIGN KEY (type) REFERENCES radiotype (type),
    FOREIGN KEY (radiologie_INPE) REFERENCES radiologie (INPE)
);

-- Create the SCANNER TYPE table
CREATE TABLE scannertype (
	type VARCHAR(255) PRIMARY KEY,
	taux DECIMAL(5, 2)
);

-- Create the Scanner table
CREATE TABLE scanner (
    id Serial PRIMARY KEY,
	radiologie_INPE varchar(255),
    prix DECIMAL(10, 2),
    description TEXT,
    dossier_matricule INT,
	type varchar(255),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
	FOREIGN KEY (type) REFERENCES scannertype (type),
	FOREIGN KEY (radiologie_INPE) REFERENCES radiologie (INPE)
);


-- Create the ANALYSE TYPE table
CREATE TABLE analyseType (
	type VARCHAR(255) PRIMARY KEY,
	taux DECIMAL(10, 2)
);

-- Create the analyse table
CREATE TABLE analysee (
    id Serial PRIMARY KEY,
	laboratoire_INPE varchar(255),
    prix DECIMAL(10, 2),
    description TEXT,
    dossier_matricule INT,
	type varchar(255),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
	FOREIGN KEY (type) REFERENCES analyseType (type),
	FOREIGN KEY (laboratoire_INPE) REFERENCES laboratoire (INPE)
);


-- Create the ANALYSE TYPE table
CREATE TABLE ordonnanceType(
	type VARCHAR(255) PRIMARY KEY,
	taux DECIMAL(10, 2)
);
-- Create the ordonnance table
CREATE TABLE ordonnance (
    id SERIAL PRIMARY KEY,
    description TEXT,
    prix DECIMAL(10, 2),
    dossier_matricule INT,
	medcine_INPE varchar(255),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
    FOREIGN KEY (medcine_INPE) REFERENCES medcine (INPE)
);

-- Create the categorie table
CREATE TABLE categorie (
    label VARCHAR(255) PRIMARY KEY,
    taux DECIMAL(5, 2),
    
);

-- Create the medicament table
CREATE TABLE medicament (
    code_bare VARCHAR(50) PRIMARY KEY,
    prix DECIMAL(10, 2),
    label VARCHAR(255),
    categorie_label varchar(255),

    FOREIGN KEY (categorie_label) REFERENCES categorie (label)
);


-- Create the dossierMedicament table
CREATE TABLE dossierMedicament (
    code_bare VARCHAR(50),
	dossier_matricule INT,
	
	FOREIGN KEY (code_bare) REFERENCES medicament (code_bare),
    FOREIGN KEY (dossier_matricule) REFERENCES dossier (matricule),
	
	PRIMARY KEY(code_bare, dossier_matricule)
);