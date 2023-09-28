package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.Documents.RadioImp;
import ma.MaCNSS.DAO.Implementations.Documents.ScannerImp;
import ma.MaCNSS.DAO.Implementations.DossierImp;
import ma.MaCNSS.DAO.Implementations.Organism.RadiologieImp;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Interfaces.DossierInterface;
import ma.MaCNSS.DAO.Interfaces.Person.AgentCNSSInterface;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Etat;

import java.awt.image.RasterOp;


public class DossierServices {
    public static Dossier add() {
        // HA LKHEDMA DYAL BSS7

        //Create a folder(Dossier)
        try {
            Dossier dossier = new Dossier();
            int matricule = PmScanner.takeIntInputValue("Enter the Folder matricule: ");
            Etat etat = PmScanner.takeFolderStatus();// etat de dossier

            Patient patient;
            do {
                String immatricule = PmScanner.takeStringInputValue("Enter the patient immatricule: ");
                PatientImp patientImp = new PatientImp();
                patient = patientImp.getPtient(immatricule);
                if (patient == null) {
                    System.out.println("There is no patient with this immatricule, Try another one");
                }
            }while(patient == null);

            AgentCNSS agentCNSS;
            do {
                String cin = PmScanner.takeStringInputValue("Enter the cnss agent cin: ");
                AgentCNSSImp agentCNSSImp = new AgentCNSSImp();
                agentCNSS = agentCNSSImp.getAgentCNSS(cin);
                if (agentCNSS == null) {
                    System.out.println("There is no cnss agent with this cin, Try another one");
                }
            }while(agentCNSS == null);

            DossierImp dossierImp = new DossierImp();

            dossier = new Dossier(matricule, etat, agentCNSS, patient);
            if (!dossierImp.add(dossier)){
                System.err.println("Error saving the patient folder");
            }
            //SCANNER
            takeScannerData(dossier.getMatricule());
            //RADIO
            takeRadioData(dossier.getMatricule());
            System.out.println(TextColor.greenText("Keep going bro"));

            return dossier;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        // Scanner count
        float totalPrice = 0;
        return null;
    }

    // Scanner
    public static void takeScannerData(int dossier_matricule) {
        try {
            Scanner scanner;
            ScannerImp scannerImp = new ScannerImp();

            String radiologie_inpe, description, type;
            float prix, taux;

            int scannerCount = PmScanner.takeIntInputValue("Enter Scanner count: ");
            for (int i = 0; i < scannerCount; i++) {
                System.out.println("Enter scanner " + Integer.toString(i+1) + " infos:");


                Radiologie radiologie;
                do {
                    radiologie_inpe = PmScanner.takeStringInputValue("Enter the radiology INPE: ");
                    RadiologieImp radiologieImp = new RadiologieImp();
                    radiologie = radiologieImp.getRadiologie(radiologie_inpe);
                    if (radiologie == null) {
                        System.out.println("There is no radiologie with this INPE, Try another one");
                    }
                }while(radiologie == null);

                do {
                    type = PmScanner.takeStringInputValue("Enter the type: ");
                    taux = scannerImp.getScannerTauxByType(type);
                    if (taux == -1) {
                        System.out.println("There is no scanner with this type, Try another one");
                    }
                }while(taux == -1);

                description = PmScanner.takeStringInputValue("Enter the description: ");
                prix = PmScanner.takeIntInputValue("Enter the price: ");

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                System.out.println(radiologie.getINPE());
                System.out.println(type);
                scanner = new Scanner(radiologie, prix, taux, description, type, dossier);
                scannerImp.add(scanner);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void takeRadioData(int dossier_matricule) {
        try {
            Radio radio;
            RadioImp radioImp = new RadioImp();

            String radiologie_inpe, description, type;
            float prix, taux;

            int radioCount = PmScanner.takeIntInputValue("Enter Radio count: ");
            for (int i = 0; i < radioCount; i++) {
                System.out.println("Enter radio " + Integer.toString(i+1) + " infos:");


                Radiologie radiologie;
                do {
                    radiologie_inpe = PmScanner.takeStringInputValue("Enter the radiology INPE: ");
                    RadiologieImp radiologieImp = new RadiologieImp();
                    radiologie = radiologieImp.getRadiologie(radiologie_inpe);
                    if (radiologie == null) {
                        System.out.println("There is no radiologie with this INPE, Try another one");
                    }
                }while(radiologie == null);

                do {
                    type = PmScanner.takeStringInputValue("Enter the type: ");
                    taux = radioImp.getRadioTauxByType(type);
                    if (taux == -1) {
                        System.out.println("There is no radio with this type, Try another one");
                    }
                }while(taux == -1);

                description = PmScanner.takeStringInputValue("Enter the description: ");
                prix = PmScanner.takeIntInputValue("Enter the price: ");

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                System.out.println(radiologie.getINPE());
                System.out.println(type);
                radio = new Radio(radiologie, prix, taux, description, type, dossier);
                radioImp.add(radio);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }


}