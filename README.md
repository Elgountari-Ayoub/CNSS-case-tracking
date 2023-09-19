# CNSS-case-tracking
CNSS case tracking project, Console Java Application


# CDC

## Contexte du projet :

Dans le cadre de faciliter le suivi des dossiers des patients au Maroc, le groupe CNSS a lancé un appel d'offre pour la création d'une application console qui va être exploitée dans tous le réseau des agences CNSS Maroc.

## Objectif du projet

L’application MaCNSS va permettre aux agents CNSS de suivre en toute simplicité les dossiers de remboursement de chaque patient qui sont inscrit à la CNSS.

Description fonctionnelle des besoins

Nous proposons de développer une application qui permettra de réaliser les tâches précitées suivant les modalités suivantes :

### 1 - Authentification de patient :

Chaque Agent CNSS a un compte dans l'application, pour qu'il puisse accéder à l'application, il doit saisir l'email + mot de passe avec un code vérification envoyé sur son boite email valable (5min)

### 2 - **Déposition de dossier**

Déposé le dossier de patient dans le système , le dossier est identifié par le matricule du patient, par la suite chaque patient peut joindre le code barre (si nécessaire) de chaque médicament prescrit sur l'ordonnance

- Le patient peut joindre aussi les analyses de laboratoire
- Le patient peut joindre une copie des radios ou scanner
- le système va indiquer automatiquement à l'agent le montant de remboursement de chaque document déposé par le patient.

### 3 - **Gestion de patient par admin**

- On a un seul administrateur dans le système qui va gérer les comptes des agents CNSS

### 4 - Consulter l’historique

À tout moment le patient peut consulter l’historique de ses dossiers de remboursement.

Le dossier du remboursement est soit : en attente, refusé ou validé

- En attente : le dossier est en traitement par l’agent CNSS
- Refusé : le dossier du patient est incomplet, un email est envoyé automatiquement au patient concerné avec le motif de refus
- Validé : c’est un dossier terminé et traité par l’agent CNSS, dans ce sens un email est envoyé automatiquement au patient avec le montant du remboursement

### 5 - Livrable

lien github de l'application + cahier de charges + conception