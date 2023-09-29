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

    }
}