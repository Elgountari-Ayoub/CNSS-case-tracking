package ma.MaCNSS.Services;


import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Implementations.AdminImp;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Interfaces.Person.AgentCNSSInterface;
import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Helpers.GMailer;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PatientServices {
    static Connection con = DBConnection.getConnection();

    static Scanner scanner = new Scanner(System.in);
    static PatientImp patientImp = new PatientImp();
    static Patient patient = new Patient();

    public static Patient login() {
        String email, pass;
        boolean quite = false;
        int loginCount = 0;
        do {
            System.out.println(TextColor.yellowText("\t\tPATIENT LOGIN"));

            System.out.print("Enter your email: ");
            patient.setEmail(scanner.next());

            try {
                Patient loggedPatient = patientImp.findByEmail(patient.getEmail());
                if (loggedPatient != null) {
                    System.out.print("Enter your password: ");
                    patient.setPassword(scanner.next());

                    if (patient.getPassword().equals(loggedPatient.getPassword())) {
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            long emailSentTime = System.currentTimeMillis(); // Record the time when the email is sent
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", patient.getEmail());
                            if (emailSent) {
                                System.out.println("Verification code sent successfully. You have 5 minutes to enter it.");

                                // Calculate the end time of the 5-minute window
                                long endTime = emailSentTime + (5 * 60 * 1000);

                                // Enter a loop to wait for user input
                                while (System.currentTimeMillis() < endTime) {
                                    // Calculate remaining time in mm:ss format
                                    long remainingTimeSeconds = (endTime - System.currentTimeMillis()) / 1000;
                                    long minutes = remainingTimeSeconds / 60;
                                    long seconds = remainingTimeSeconds % 60;
                                    String remainingTimeFormatted = String.format("%02d:%02d", minutes, seconds);

                                    // Display the remaining time to the user
                                    System.out.println("Time remaining: " + remainingTimeFormatted);

                                    // Check if the user entered the correct code
                                    int userInput = PmScanner.takeIntInputValue("Enter verification code: ");
                                    if (userInput == randomNumber) {
                                        System.out.println("Connected successfully");
                                        return patientImp.findByEmail(patient.getEmail());
                                    } else {
                                        System.out.println(TextColor.yellowText("Wrong verification code!"));
                                    }

                                    // Introduce a delay to refresh the display every second
                                    Thread.sleep(1000);
                                }
                                // Time's up
                                System.out.println("Time's up! Please try again.");
                            } else {
                                System.out.println(TextColor.yellowText("Error sending the email, check your network!"));
                            }
                        } catch (Exception ex) {
                            System.out.println("Error sending the email, check your network!");
                        }
                    } else {
                        System.out.println(TextColor.yellowText("Password is incorrect"));
                    }
                } else {
                    System.out.println("Email not correct");
                }
            } catch (SQLException ex) {
                System.err.println("message: " + ex.getMessage());
            }
            loginCount++;
            quite = !PmScanner.confirmation(TextColor.yellowText("try again (Y/N)? "));
        } while (loginCount < 3 && !quite);

        if (quite) {
            return null;
        }
        System.out.println("You have exceeded 3 login attempts. Try later!");
        return null;
    }

    static boolean foundRows = false;

    public static void getDossiers(Patient patient) {
        foundRows = true;
        String sql = "SELECT\n" +
                "    d.matricule AS dossier_number,\n" +
                "\td.remboursement As dossier_remboursement,\n" +
                "\t(SELECT COUNT(*) FROM analysee a WHERE a.dossier_matricule = d.matricule) AS analysee_count,\n" +
                "    (SELECT COUNT(*) FROM scanner s WHERE s.dossier_matricule = d.matricule) AS scanner_count,\n" +
                "    (SELECT COUNT(*) FROM radio r WHERE r.dossier_matricule = d.matricule) AS radio_count,\n" +
                "    (SELECT COUNT(*) FROM ordonnance o WHERE o.dossier_matricule = d.matricule) AS ordonnance_count,\n" +
                "    (SELECT COUNT(*) FROM dossierMedicament dm WHERE dm.dossier_matricule = d.matricule) AS dossiermedicament_count\n" +
                "FROM dossier d\n" +
                "WHERE d.patient_immatricule = ?;";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            System.out.println(TextColor.greenText(patient.getImmatricule()));
            ps.setString(1, patient.getImmatricule());
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {

                int matricule = resultSet.getInt("dossier_number");
                float remboursement = resultSet.getFloat("dossier_remboursement");
                int analyseCount = resultSet.getInt("analysee_count");
                int scannerCount = resultSet.getInt("scanner_count");
                int radioCount = resultSet.getInt("radio_count");
                int ordonnanceCount = resultSet.getInt("ordonnance_count");
                int dossierMedicamentCount = resultSet.getInt("dossiermedicament_count");

                System.out.println(TextColor.greenText(
                        "\tdossier_number: " + matricule + "\n" +
                                "\tdossier_remboursement: " + remboursement + "\n" +
                                "\tanalysee_count: " + analyseCount + "\n" +
                                "\tscanner_count: " + scannerCount + "\n" +
                                "\tradio_count: " + radioCount + "\n" +
                                "\tordonnance_count: " + ordonnanceCount + "\n" +
                                "\tdossiermedicament_count: " + dossierMedicamentCount + "\n\n" +
                                "-------------------------------------------------------------\n\n"
                ));
            }
            if (!foundRows) {
                System.out.println(TextColor.yellowText("Empty ..!"));
            }
        } catch (SQLException ex) {
            System.out.println(TextColor.yellowText(ex.getMessage()));
        }
    }

    public static Patient add() {
        String cin, nom, prenom, ville, telephone, email, genreString, password;
        String immatricule = PmScanner.takeStringInputValue("Enter immatricule: ");
        cin = PmScanner.takeStringInputValue("CIN: ");
        nom = PmScanner.takeStringInputValue("Nom: ");
        prenom = PmScanner.takeStringInputValue("Prenom: ");
        ville = PmScanner.takeStringInputValue("Ville: ");
        telephone = PmScanner.takeStringInputValue("Telephone: ");
        Genre genre = Genre.valueOf(PmScanner.takeGender("Genre(HOMME/FEMME)? "));

        email = PmScanner.takeStringInputValue("Email: ");
        password = PmScanner.takeStringInputValue("Mote de pass: ");

        patient =  new Patient(immatricule, cin, nom, prenom, ville, telephone, email, password, genre);
        if (patientImp.add(patient)){
            return patient;
        }
        return null;
    }

}