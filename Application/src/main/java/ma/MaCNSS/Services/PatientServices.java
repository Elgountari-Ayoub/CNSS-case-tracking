package ma.MaCNSS.Services;


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

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PatientServices {
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

}