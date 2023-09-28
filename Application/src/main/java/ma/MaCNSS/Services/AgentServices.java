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

            System.out.print("Enter yout email: ");
            agentCNSS.setEmail(scanner.next());

            try {
                AgentCNSS loggedAgent = agentCNSSImp.findByEmail(agentCNSS.getEmail());
                if (loggedAgent != null) {
                    System.out.print("Enter your password: ");
                    agentCNSS.setPassword(scanner.next());

                    //if (BCrypt.checkpw(agentCNSS.getPassword(), loggedInAgent.getPassword())) {
                    if (agentCNSS.getPassword().equals(loggedAgent.getPassword())) {
                        //GMailer.send();
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", agentCNSS.getEmail());
                            if (emailSent) {
                                int userInput = PmScanner.takeIntInputValue("Enter verification code: ");

                                if (userInput == randomNumber) {
                                    System.out.println("Connected successfully");
                                    return agentCNSS;
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