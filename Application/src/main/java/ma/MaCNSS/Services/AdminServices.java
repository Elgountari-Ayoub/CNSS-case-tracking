package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.AdminImp;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Interfaces.AdminInterface;
import ma.MaCNSS.DAO.Interfaces.Person.AgentCNSSInterface;
import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Helpers.GMailer;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AdminServices {
    static Scanner scanner = new Scanner(System.in);
    static AdminImp adminImp = new AdminImp();
    static Admin admin = new Admin();

    public static Admin login() {
        String email, pass;
        boolean quite = false;
        int loginCount = 0;
        do {
            System.out.println(TextColor.yellowText("\t\tADMIN LOGIN"));

            System.out.print("Enter yout email: ");
            admin.setEmail(scanner.next());

            try {
                Admin loggedAdmin = adminImp.findByEmail(admin.getEmail());
                if (loggedAdmin != null) {
                    System.out.print("Enter your password: ");
                    admin.setPass(scanner.next());

                    //if (BCrypt.checkpw(agentCNSS.getPassword(), loggedInAgent.getPassword())) {
                    if (admin.getPass().equals(loggedAdmin.getPass())) {
                        //GMailer.send();
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", admin.getEmail());
                            if (emailSent) {
                                int userInput = PmScanner.takeIntInputValue("Enter verification code: ");

                                if (userInput == randomNumber) {
                                    System.out.println("Connected successfully");
                                    return admin;
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
                        System.err.println("Password is incorrect");
                    }
                } else {
                    System.out.println("email not correct");
                }
            } catch (SQLException ex) {
                System.err.println("message : " + ex.getMessage());
            }
            loginCount++;
            quite = !PmScanner.confirmation(TextColor.yellowText("trye again(Y/N)? "));
        } while (loginCount < 2 && quite == false);

        if (quite == true) {
            return null;
        }
        System.out.println("You Passed 3 times, Try later!");
        return null;
    }
}