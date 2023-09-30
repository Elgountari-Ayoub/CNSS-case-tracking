package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.Documents.AnalyseImp;
import ma.MaCNSS.DAO.Implementations.Documents.OrdonnanceImp;
import ma.MaCNSS.DAO.Implementations.Documents.RadioImp;
import ma.MaCNSS.DAO.Implementations.Documents.ScannerImp;
import ma.MaCNSS.DAO.Implementations.DossierImp;
import ma.MaCNSS.DAO.Implementations.Medicament.DossierMedicamentImp;
import ma.MaCNSS.DAO.Implementations.Medicament.MedicamentImp;
import ma.MaCNSS.DAO.Implementations.Organism.LaboratoireImp;
import ma.MaCNSS.DAO.Implementations.Organism.MedcineImp;
import ma.MaCNSS.DAO.Implementations.Organism.RadiologieImp;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Medicament.DossierMedicament;
import ma.MaCNSS.Entities.Medicament.Medicament;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Helpers.GMailer;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Etat;


public class DossierServices {
    public static Dossier add() {
        // HA LKHEDMA DYAL BSS7

        //Create a folder(Dossier)
        try {
            DossierImp dossierImp = new DossierImp();
            Dossier dossier;
            int matricule;
            do {
                matricule = PmScanner.takeIntInputValue("Enter the Folder matricule: ");
                dossier = dossierImp.getDossier(matricule);
                if (dossier != null) {
                    System.out.println("There is a dossier with the same matricule, Try another one");
                }
            }while(dossier != null);

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

            // DOSIER
            dossier = new Dossier(matricule, etat, agentCNSS, patient);
            dossierImp.add(dossier);

            //SCANNER
            float scannerRemb =  takeScannerData(dossier.getMatricule());
            System.out.println("scanner => " + scannerRemb);
            //RADIO
            float radioRemb =  takeRadioData(dossier.getMatricule());
            System.out.println("Radio => " + radioRemb);
            //ANALYSE
            float analyseRemb =  takeAnalyseData(dossier.getMatricule());
            System.out.println("Analyse => " + analyseRemb);
            //ORDONNANCE
            float ordonnanceRemb =  takeOrdonnanceData(dossier.getMatricule());
            System.out.println("Ordonnance => " + ordonnanceRemb);
            // DOSSIER MEDICAMENT
            float medicamentRemb = takeMedicamentData(dossier.getMatricule());
            System.out.println("Medicament => " + medicamentRemb);

            float dossierRemb = scannerRemb + radioRemb + analyseRemb + ordonnanceRemb + medicamentRemb;
            System.out.println(TextColor.greenText("Dossier => " + dossierRemb));

            dossier.setRemboursement(dossierRemb);

            if (dossierImp.setRemboursement(dossier)){
                System.out.println("remboursement saved successfully");

                //Send email to the patient
                boolean emailSent = new GMailer().sendEmail(String.valueOf(dossierRemb), "Remboursement", patient.getEmail());
                if (emailSent) {}
            }
            else {
                System.out.println(TextColor.yellowText("error occured while trying to save the remboursement!"));
            }
            return dossier;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }

    // Scanner
    public static float takeScannerData(int dossier_matricule) {
        float finalPrice = 0;
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

                finalPrice += (prix * taux) / 100;

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);
                scanner = new Scanner(radiologie, prix, taux, description, type, dossier);
                scannerImp.add(scanner);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return finalPrice;
    }

    public static float takeRadioData(int dossier_matricule) {
        float finalPrice = 0;
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
                finalPrice += (prix * taux) / 100;

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                radio = new Radio(radiologie, prix, taux, description, type, dossier);
                radioImp.add(radio);

            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return finalPrice;
    }
    public static float takeAnalyseData(int dossier_matricule) {
        float finalPrice = 0;
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

                finalPrice += (prix * taux) / 100;

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                analyse = new Analyse(laboratoire, prix, taux, description, type, dossier);
                analyseImp.add(analyse);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return finalPrice;
    }

    public static float takeOrdonnanceData(int dossier_matricule) {
        float finalPrice = 0;
        try {
            Ordonnance ordonnance;
            OrdonnanceImp ordonnanceImp = new OrdonnanceImp();
            MedcineImp medcineImp;

            String medcine_inpe, description, type;
            float prix, taux;

            int ordonnanceCount = PmScanner.takeIntInputValue("Enter Ordonnance count: ");
            for (int i = 0; i < ordonnanceCount; i++) {
                System.out.println("Enter Ordonnance " + Integer.toString(i + 1) + " infos:");

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

                finalPrice += (prix * taux) / 100;

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                ordonnance = new Ordonnance(prix, taux, description, medcine, dossier);
                ordonnanceImp.add(ordonnance);
            }

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return finalPrice;
    }
    public static float takeMedicamentData(int dossier_matricule) {
        float finalPrice = 0;
        try {
            Medicament medicament;
            MedicamentImp medicamentImp = new MedicamentImp();

            DossierMedicament dossierMedicament;
            DossierMedicamentImp dossierMedicamentImp = new DossierMedicamentImp();

            String medicament_code_bar, description, type;
            float prix, taux;

            int medicamentCount = PmScanner.takeIntInputValue("Enter medicament count: ");
            for (int i = 0; i < medicamentCount; i++) {
                System.out.println("Enter Medicament " + Integer.toString(i + 1) + " infos:");

                do {
                    medicament_code_bar = PmScanner.takeStringInputValue("Enter the medicament code bare: ");
                    medicament = medicamentImp.getMedicament(medicament_code_bar);
                    if (medicament == null) {
                        System.out.println("There is no medicament with this code bare, Try another one");
                    }
                } while (medicament == null);

                prix = medicament.getPrix();
                taux = medicament.getCategorie().getTaux();

                finalPrice += (prix * taux) / 100;

                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(dossier_matricule);

                dossierMedicament = new DossierMedicament(dossier, medicament);
                dossierMedicamentImp.add(dossierMedicament);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return finalPrice;
    }


}