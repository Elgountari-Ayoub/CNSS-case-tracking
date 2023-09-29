package ma.MaCNSS;

import ma.MaCNSS.DAO.Implementations.Documents.AnalyseImp;
import ma.MaCNSS.DAO.Implementations.Documents.OrdonnanceImp;
import ma.MaCNSS.DAO.Implementations.Documents.RadioImp;
import ma.MaCNSS.DAO.Implementations.Documents.ScannerImp;
import ma.MaCNSS.DAO.Implementations.DossierImp;

import ma.MaCNSS.DAO.Implementations.Medicament.CategorieImp;
import ma.MaCNSS.DAO.Implementations.Medicament.MedicamentImp;
import ma.MaCNSS.DAO.Implementations.Organism.LaboratoireImp;
import ma.MaCNSS.DAO.Implementations.Organism.MedcineImp;
import ma.MaCNSS.DAO.Implementations.Organism.RadiologieImp;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Interfaces.AdminInterface;
import ma.MaCNSS.DAO.Interfaces.Organism.MedcineInterface;
import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Medicament.Categorie;
import ma.MaCNSS.Entities.Medicament.Medicament;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.Helpers.UI;
import ma.MaCNSS.Services.AdminServices;
import ma.MaCNSS.Services.AgentServices;
import ma.MaCNSS.Services.DossierServices;
import ma.MaCNSS.enums.Etat;
import ma.MaCNSS.enums.Genre;

import java.awt.*;
import java.awt.image.Raster;


// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        boolean quite;
        int loginCount;
        do {
            UI.MAIN_MENU();
            choice = PmScanner.takeUserChoice(0, 3);
            switch (choice) {
//              ADMIN
                case 1:
                    // => LOGIN
                    Admin admin = new Admin();
                    admin = AdminServices.login();
                    if (admin == null) {
                        break;
                    }
                    System.out.println("Welcom Admin 💪🏼");

                    // SUBMENU => ADD AN AGENT
                    do {
                        UI.ADMIN_MENU();
                        choice = PmScanner.takeUserChoice(0, 1);
                        AgentCNSS agentCNSS = new AgentCNSS();
                        switch (choice) {

                            case 1:
                                agentCNSS = AgentServices.add();
                                if (agentCNSS != null) {
                                    System.out.println(TextColor.greenText("L'agent bien ajoute"));
                                } else {
                                    System.err.println("L'agent pas ajoute");
                                }
                                break;
                        }
                    } while (choice != 0);
                    choice = -1;
                    break;
//              Agent CNSS
                case 2:
                    // => LOGIN
                    AgentCNSS agentCNSS = new AgentCNSS();
//                    agentCNSS = AgentServices.login();
//                    if (agentCNSS == null) {
//                        break;
//                    }
                    System.out.println("Welcom CNSS Agent 💪🏼");

                    // SUBMENU => ADD AN AGENT
                    do {
                        UI.AGENT_MENU();
                        choice = PmScanner.takeUserChoice(0, 1);
                        Dossier dossier = new Dossier();
                        switch (choice) {
                            case 1:
                                dossier = DossierServices.add();
                                if (agentCNSS != null) {
                                    System.out.println(TextColor.greenText("Le dossier a etait bien ajoute"));
                                } else {
                                    System.err.println("Le dossier pas ajoute");
                                }
                                break;
                        }
                    } while (choice != 0);

                    choice = -1;
                    break;
                case 0:
                    System.out.println("THANKS FOR THE VISIT :)");
                    break;
            }

        } while (choice != 0);

//----------------------------------------


/*        // get the patient by his Matricule
//        try {
////            patient = new Patient("1000", "cin-1", "p-nom", "p-prenom", "Safi", "06","patient@gmail.com", "pass-p1", Genre.HOMME);
////            patientImp.add(patient);
////            patient = patientImp.getPtient("1000");
////            System.out.println(patient.getNom());
//
//            // Insert a Patient
//            agentCNSS = takeAgentCNSSData();
//            agentCNSSImp.add(agentCNSS);
//        } catch (Exception ex) {
//            System.err.println("Error messgae : " + ex.getMessage());
/     }
    }
 */
    }

    // DOSSIER SERVICE

    //    Dossier
    public static Dossier takeDossierData() {
        try {
            String matricule, etatString;
            Etat etat;
            Dossier dossier = new Dossier();
            matricule = PmScanner.takeStringInputValue("Matricule: ");
            etatString = PmScanner.takeStringInputValue("Etat(ATTENTE/ACCPTER/REFUSE): ");
            etat = Etat.valueOf(etatString);
            dossier.setEtat(etat);

            //Scanner


//        return new Scanner(radiologie, prix, taux, description, dossier, type);
            return new Dossier();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    // Scanner
    public static Scanner takeScannerData() {
        try {
            String radiologie_inpe, description, type;
            int dossier_matricule;
            float prix, taux;
            radiologie_inpe = PmScanner.takeStringInputValue("Radiologie inpe: ");
            prix = PmScanner.takeFloatInputValue("Prix: ");
            taux = PmScanner.takeFloatInputValue("Taux: ");
            description = PmScanner.takeStringInputValue("Description: ");
            dossier_matricule = PmScanner.takeIntInputValue("Dossier matricule: ");
            type = PmScanner.takeStringInputValue("Type: ");

            RadiologieImp radiologieImp = new RadiologieImp();
            Radiologie radiologie = radiologieImp.getRadiologie(radiologie_inpe);

            DossierImp dossierImp = new DossierImp();
            Dossier dossier = dossierImp.getDossier(dossier_matricule);

            return new Scanner(radiologie, prix, taux, description,type, dossier);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    //Radio
    public static Radio takeRadioData() {
        try {

            String radiologie_inpe, description, type;
            int dossier_matricule;
            float prix, taux;
            radiologie_inpe = PmScanner.takeStringInputValue("Radiologie inpe: ");
            prix = PmScanner.takeFloatInputValue("Prix: ");
            taux = PmScanner.takeFloatInputValue("Taux: ");
            description = PmScanner.takeStringInputValue("Description: ");
            dossier_matricule = PmScanner.takeIntInputValue("Dossier matricule: ");
            type = PmScanner.takeStringInputValue("Type: ");

            RadiologieImp radiologieImp = new RadiologieImp();
            Radiologie radiologie = radiologieImp.getRadiologie(radiologie_inpe);

            DossierImp dossierImp = new DossierImp();
            Dossier dossier = dossierImp.getDossier(dossier_matricule);

            return new Radio(radiologie, prix, taux, description, type, dossier);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    // Analyse
    public static Analyse takeAnalyseData() {
        try {

            String laboratoire_inpe, description, type;
            int dossier_matricule;
            float prix, taux;
            laboratoire_inpe = PmScanner.takeStringInputValue("Laboratoire inpe: ");
            prix = PmScanner.takeFloatInputValue("Prix: ");
            taux = PmScanner.takeFloatInputValue("Taux: ");
            description = PmScanner.takeStringInputValue("Description: ");
            dossier_matricule = PmScanner.takeIntInputValue("Dossier matricule: ");
            type = PmScanner.takeStringInputValue("Type: ");

            LaboratoireImp laboratoireImp = new LaboratoireImp();
            Laboratoire laboratoire = laboratoireImp.getLaboratoire(laboratoire_inpe);

            DossierImp dossierImp = new DossierImp();
            Dossier dossier = dossierImp.getDossier(dossier_matricule);


            return new Analyse(laboratoire, prix, taux, description, type, dossier);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    // Ordonnance
    public static Ordonnance takeOrdonnanceData() {
        try {
            String medcine_inpe, description, type;
            int dossier_matricule;
            float prix, taux;
            medcine_inpe = PmScanner.takeStringInputValue("Medcine INPE: ");
            prix = PmScanner.takeFloatInputValue("Prix: ");
            taux = PmScanner.takeFloatInputValue("Taux: ");
            description = PmScanner.takeStringInputValue("Description: ");
            dossier_matricule = PmScanner.takeIntInputValue("Dossier matricule: ");

            MedcineImp medcineImp = new MedcineImp();
            Medcine medcine = medcineImp.getMedcine(medcine_inpe);

            DossierImp dossierImp = new DossierImp();
            Dossier dossier = dossierImp.getDossier(dossier_matricule);

            return new Ordonnance(prix, taux, description, medcine, dossier);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    // Medicament
    public static Medicament takeMedicamentData() {
        try {
            String categorie_label, label;
            int dossier_matricule;
            float prix;

            prix = PmScanner.takeFloatInputValue("Prix: ");
            label = PmScanner.takeStringInputValue("Label: ");
            categorie_label = PmScanner.takeStringInputValue("Categorie label: ");
            dossier_matricule = PmScanner.takeIntInputValue("Dossier matricule: ");

            CategorieImp categorieImp = new CategorieImp();
            Categorie categorie = categorieImp.getCategorie(label);

            DossierImp dossierImp = new DossierImp();
            Dossier dossier = dossierImp.getDossier(dossier_matricule);

            return new Medicament(prix, label, dossier, categorie);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }
    // and take the data of each one

    // AGENT SERVICE

    //--------------------------------------------
    public static Radiologie takeRadiologieData() {
        try {
            String inpe, address, label;

            inpe = PmScanner.takeStringInputValue("INPE: ");
            address = PmScanner.takeStringInputValue("Adress: ");
            label = PmScanner.takeStringInputValue("Label: ");

            return new Radiologie(inpe, address, label);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public static Laboratoire takeLaboratoireData() {
        try {
            String inpe, address, label;

            inpe = PmScanner.takeStringInputValue("INPE: ");
            address = PmScanner.takeStringInputValue("Adress: ");
            label = PmScanner.takeStringInputValue("Label: ");

            return new Laboratoire(inpe, address, label);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    public static Medcine takeedcineData() {
        try {
            String inpe, address, label;

            inpe = PmScanner.takeStringInputValue("INPE: ");
            address = PmScanner.takeStringInputValue("Adress: ");
            label = PmScanner.takeStringInputValue("Label: ");

//            return new Medcine(inpe, address,label);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return null;
    }

    //--------------------------------------------
    // MENUs


}