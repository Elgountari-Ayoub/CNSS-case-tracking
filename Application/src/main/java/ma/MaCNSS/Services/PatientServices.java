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
            System.out.println(TextColor.yellowText("\t\tAGENT CNSS LOGIN"));

            System.out.print("Enter yout email: ");
            patient.setEmail(scanner.next());

            try {
                Patient loggedPatient = patientImp.findByEmail(patient.getEmail());
                if (loggedPatient != null) {
                    System.out.print("Enter your password: ");
                    patient.setPassword(scanner.next());

                    //if (BCrypt.checkpw(agentCNSS.getPassword(), loggedInAgent.getPassword())) {
                    if (patient.getPassword().equals(loggedPatient.getPassword())) {
                        //GMailer.send();
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", patient.getEmail());
                            if (emailSent) {
                                int userInput = PmScanner.takeIntInputValue("Enter verification code: ");

                                if (userInput == randomNumber) {
                                    System.out.println("Connected successfully");
                                    return patient;
                                } else {
                                    System.out.println(TextColor.yellowText("Wrong verification code?!"));
                                }
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
                    System.out.println("email not correct");
                }
            } catch (SQLException ex) {
                System.err.println("message : " + ex.getMessage());
            }
            loginCount++;
            quite = !PmScanner.confirmation(TextColor.yellowText("trye again(Y/N)? "));
        } while (loginCount < 3 && quite == false);

        if (quite == true) {
            return null;
        }
        System.out.println("You Passed 3 times, Try later!");
        return null;
    }

    public static void getDossiers(Patient patient) {
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
            ResultSet resultSet =  ps.executeQuery();
            if (resultSet.next()){
                int matricule = resultSet.getInt("dossier_number");
                float remboursement = resultSet.getFloat("dossier_remboursement");
                int analyseCount = resultSet.getInt("analysee_count");
                int scannerCount = resultSet.getInt("scanner_count");
                int radioCount = resultSet.getInt("radio_count");
                int ordonnanceCount = resultSet.getInt("ordonnance_count");
                int dossierMedicamentCount = resultSet.getInt("dossiermedicament_count");

                System.out.println(TextColor.greenText(
                        "\tdossier_number: " + matricule+ "\n" +
                                "\tdossier_remboursement: " +remboursement+ "\n" +
                                "\tanalysee_count: " +analyseCount+ "\n" +
                                "\tscanner_count: " + scannerCount+"\n" +
                                "\tradio_count: " + radioCount + "\n" +
                                "\tordonnance_count: " + ordonnanceCount + "\n" +
                                "\tdossiermedicament_count: "+ dossierMedicamentCount+ "\n\n"
                ));
            }
            else {
                System.out.println(TextColor.yellowText("Empty ..!"));
            }
        } catch (SQLException ex) {
            System.out.println(TextColor.yellowText(ex.getMessage()));
        }
    }

}