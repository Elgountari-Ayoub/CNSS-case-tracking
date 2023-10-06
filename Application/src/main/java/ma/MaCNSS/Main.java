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
import ma.MaCNSS.DAO.Implementations.Person.EmployeeImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Interfaces.AdminInterface;
import ma.MaCNSS.DAO.Interfaces.Organism.MedcineInterface;
import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Company;
import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.EmployeeCareer;
import ma.MaCNSS.Entities.Medicament.Categorie;
import ma.MaCNSS.Entities.Medicament.Medicament;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Employee;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.Helpers.UI;
import ma.MaCNSS.Services.*;
import ma.MaCNSS.enums.Etat;
import ma.MaCNSS.enums.Genre;

import java.awt.*;
import java.awt.image.Raster;

public class Main {
    private static final java.util.Scanner scanner = new java.util.Scanner(System.in);

    public static void main(String[] args) {
        int choice;


        EmployeeImp employeeImp = new EmployeeImp();
        try {
            Employee employee = employeeImp.getEmployee("imat1111");
            System.out.println(employee.getPresent());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        int loginCount;
        do {
            UI.MAIN_MENU();
            choice = PmScanner.takeUserChoice(0, 4);
            switch (choice) {
//              ADMIN
                case 1:
                    // => LOGIN
                    Admin admin = new Admin();
                    admin = AdminServices.login();
                    if (admin == null) {
                        break;
                    }
                    System.out.println("Welcome Admin 💪🏼");

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
                    try {
                        // => LOGIN
                        AgentCNSS agentCNSS;
                        agentCNSS = AgentServices.login();
                        if (agentCNSS == null) {
                            break;
                        }
                        System.out.println("Welcome CNSS Agent 💪🏼");

                        // SUBMENU => SUBMIT A FILE
                        do {
                            UI.AGENT_MENU();
                            choice = PmScanner.takeUserChoice(0, 2);
                            Dossier dossier;
                            Company company;

                            switch (choice) {
                                case 1:
                                    dossier = DossierServices.add();
                                    if (dossier != null) {
                                        System.out.println(TextColor.greenText("Le dossier a etait bien ajoute"));
                                    } else {
                                        System.err.println("Le dossier pas ajoute");
                                    }
                                    break;
                                case 2:
                                    try {
                                        company = CompanyServices.add();
                                        if (company != null) {
                                            System.out.println(TextColor.greenText("Company added successfully"));
                                        } else {
                                            System.err.println("The company not added!, check the administration");
                                        }
                                    } catch (Exception ex) {
                                        System.out.println(TextColor.yellowText(ex.getMessage()));
                                    }
                            }
                        } while (choice != 0);

                        choice = -1;
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    //Patient
                case 3:
                    try {
                        // => LOGIN
                        Patient patient = new Patient();
                        patient = PatientServices.login();
                        if (patient == null) {
                            break;
                        }
                        System.out.println("Welcome Patien Agent 💪🏼");

                        do {
                            UI.PATIENT_MENU();
                            choice = PmScanner.takeUserChoice(0, 1);
                            switch (choice) {
                                case 1:
                                    patient.setImmatricule("imat1111");
                                    PatientServices.getDossiers(patient);
                                    break;
                            }
                        } while (choice != 0);

                        choice = -1;
                        break;
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                case 4:
                    try {
                        // => LOGIN
                        Company company;
                        company = CompanyServices.login();
                        if (company == null) {
                            break;
                        }
                        System.out.println("Welcome " + company.getName() + " 💪🏼");

                        // SUBMENU => Add an employee
                        do {
                            UI.COMPANY_MENU();
                            choice = PmScanner.takeUserChoice(0, 1);
                            switch (choice) {
                                case 1:
                                    try {
                                        company = CompanyServices.add();
                                        if (company != null) {
                                            System.out.println(TextColor.greenText("Company added successfully"));
                                        } else {
                                            System.err.println("The company not added!, check the administration");
                                        }
                                    } catch (Exception ex) {
                                        System.out.println(TextColor.yellowText(ex.getMessage()));
                                    }
                            }
                        } while (choice != 0);

                        choice = -1;
                        break;
                    } catch (Exception ex) {
                        System.out.println(TextColor.yellowText(ex.getMessage()));
                    }
                case 0:
                    System.out.println("THANKS FOR THE VISIT :)");
                    break;
            }

        } while (choice != 0);

    }
}