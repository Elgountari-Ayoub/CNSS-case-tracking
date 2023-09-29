package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.Documents.AnalyseImp;
import ma.MaCNSS.DAO.Implementations.Documents.OrdonnanceImp;
import ma.MaCNSS.DAO.Implementations.Documents.RadioImp;
import ma.MaCNSS.DAO.Implementations.Documents.ScannerImp;
import ma.MaCNSS.DAO.Implementations.DossierImp;
import ma.MaCNSS.DAO.Implementations.Organism.LaboratoireImp;
import ma.MaCNSS.DAO.Implementations.Organism.MedcineImp;
import ma.MaCNSS.DAO.Implementations.Organism.RadiologieImp;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Etat;


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
            //ANALYSE
            takeAnalyseData(dossier.getMatricule());
            //ORDONNANCE
            takeOrdonnanceData(dossier.getMatricule());

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
                    type = PmScanner.takeStringInputValue("Enter the scanner type: ");
                    taux = scannerImp.getScannerTauxByType(type.toUpperCase());
                    if (taux == -1) {
                        System.out.println("There is no scanner with this type, Try another one");
                    }
                }while(taux == -1);

                description = PmScanner.takeStringInputValue("Enter the description: ");
                prix = PmScanner.takeIntInputValue("Enter the price: ");

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);
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
                    type = PmScanner.takeStringInputValue("Enter the radio type: ");
                    taux = radioImp.getRadioTauxByType(type);
                    if (taux == -1) {
                        System.out.println("There is no radio with this type, Try another one");
                    }
                }while(taux == -1);

                description = PmScanner.takeStringInputValue("Enter the description: ");
                prix = PmScanner.takeIntInputValue("Enter the price: ");

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                radio = new Radio(radiologie, prix, taux, description, type, dossier);
                radioImp.add(radio);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static void takeAnalyseData(int dossier_matricule) {
        try {
            Analyse analyse;
            AnalyseImp analyseImp = new AnalyseImp();

            String laboratoire_inpe, description, type;
            float prix, taux;

            int radioCount = PmScanner.takeIntInputValue("Enter Analyses count: ");
            for (int i = 0; i < radioCount; i++) {
                System.out.println("Enter Analyse " + Integer.toString(i+1) + " infos:");


                Laboratoire laboratoire;
                do {
                    laboratoire_inpe = PmScanner.takeStringInputValue("Enter the laboratoire INPE: ");
                    LaboratoireImp laboratoireImp = new LaboratoireImp();
                    laboratoire = laboratoireImp.getLaboratoire(laboratoire_inpe);
                    if (laboratoire == null) {
                        System.out.println("There is no laboratoire with this INPE, Try another one");
                    }
                }while(laboratoire == null);

                do {
                    type = PmScanner.takeStringInputValue("Enter the type: ");
                    taux = analyseImp.getAnalyseTauxByType(type);
                    if (taux == -1) {
                        System.out.println("There is no analyse with this type, Try another one");
                    }
                }while(taux == -1);

                description = PmScanner.takeStringInputValue("Enter the description: ");
                prix = PmScanner.takeIntInputValue("Enter the price: ");

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                analyse = new Analyse(laboratoire, prix, taux, description, type, dossier);
                analyseImp.add(analyse);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void takeOrdonnanceData(int dossier_matricule) {
        try {
            Ordonnance ordonnance;
            OrdonnanceImp ordonnanceImp = new OrdonnanceImp();
            MedcineImp medcineImp;

            String medcine_inpe, description, type;
            float prix, taux;

            int ordonnanceCount = PmScanner.takeIntInputValue("Enter Ordonnance count: ");
            for (int i = 0; i < ordonnanceCount; i++) {
                System.out.println("Enter Analyse " + Integer.toString(i + 1) + " infos:");

                Medcine medcine;
                do {
                    medcine_inpe = PmScanner.takeStringInputValue("Enter the medcin INPE: ");
                    medcineImp = new MedcineImp();
                    medcine = medcineImp.getMedcine(medcine_inpe);
                    if (medcine == null) {
                        System.out.println("There is no medcine with this INPE, Try another one");
                    }
                } while (medcine == null);

                do {
                    type = PmScanner.takeStringInputValue("Enter the Medcin type(GENERALISTE/SPECIALISTE)?: ");
                    taux = medcineImp.getMedcinTauxByType(type);
                    if (taux == -1) {
                        System.out.println("There is no medcine with this type, Try another one");
                    }
                } while (taux == -1);

                description = PmScanner.takeStringInputValue("Enter the description: ");
                prix = PmScanner.takeIntInputValue("Enter the price: ");

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                ordonnance = new Ordonnance(prix, taux, description, medcine, dossier);
                System.out.println(TextColor.greenText(
                        medcine.getINPE()
                ));
                ordonnanceImp.add(ordonnance);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }


}