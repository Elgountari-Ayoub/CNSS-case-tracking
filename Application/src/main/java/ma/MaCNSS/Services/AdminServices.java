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
        boolean quite = false;
        int loginCount = 0;
        do {
            System.out.println(TextColor.yellowText("\t\tADMIN LOGIN"));

            System.out.print("Enter your email: ");
            admin.setEmail(scanner.next());

            try {
                Admin loggedAdmin = adminImp.findByEmail(admin.getEmail());
                if (loggedAdmin != null) {
                    System.out.print("Enter your password: ");
                    admin.setPass(scanner.next());

                    if (admin.getPass().equals(loggedAdmin.getPass())) {
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            long emailSentTime = System.currentTimeMillis(); // Record the time when the email is sent
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", admin.getEmail());
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
                                        return admin;
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
                        System.err.println("Password is incorrect");
                    }
                } else {
                    System.out.println("Email not correct");
                }
            } catch (SQLException ex) {
                System.err.println("message: " + ex.getMessage());
            }
            loginCount++;
            quite = !PmScanner.confirmation(TextColor.yellowText("try again (Y/N)? "));
        } while (loginCount < 2 && !quite);

        if (quite) {
            return null;
        }
        System.out.println("You have exceeded 3 login attempts. Try later!");
        return null;
    }


}