package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Helpers.GMailer;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Genre;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AgentServices  {
    static Scanner scanner = new Scanner(System.in);
    static AgentCNSSImp agentCNSSImp = new AgentCNSSImp();
    static AgentCNSS agentCNSS = new AgentCNSS();

    public static AgentCNSS login() {
        String email, pass;
        boolean quite = false;
        int loginCount = 0;
        do {
            System.out.println(TextColor.yellowText("\t\tAGENT CNSS LOGIN"));

            System.out.print("Enter your email: ");
            agentCNSS.setEmail(scanner.next());

            try {
                AgentCNSS loggedAgent = agentCNSSImp.findByEmail(agentCNSS.getEmail());
                if (loggedAgent != null) {
                    System.out.print("Enter your password: ");
                    agentCNSS.setPassword(scanner.next());

                    if (agentCNSS.getPassword().equals(loggedAgent.getPassword())) {
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            long emailSentTime = System.currentTimeMillis(); // Record the time when the email is sent
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", agentCNSS.getEmail());
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
                                        return agentCNSS;
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


    public static AgentCNSS add() {
        String cin, nom, prenom, ville, telephone, email, genreString, password;
        cin = PmScanner.takeStringInputValue("CIN: ");
        nom = PmScanner.takeStringInputValue("Nom: ");
        prenom = PmScanner.takeStringInputValue("Prenom: ");
        ville = PmScanner.takeStringInputValue("Ville: ");
        telephone = PmScanner.takeStringInputValue("Telephone: ");
        Genre genre = Genre.valueOf(PmScanner.takeGender("Genre(HOMME/FEMME)? "));

        email = PmScanner.takeStringInputValue("Email: ");
        password = PmScanner.takeStringInputValue("Mote de pass: ");

        agentCNSS =  new AgentCNSS(cin, nom, prenom, ville, telephone, email, password, genre);
        if (agentCNSSImp.add(agentCNSS)){
            return agentCNSS;
        }
        return null;
    }




}