package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.CompanyImp;
import ma.MaCNSS.DAO.Implementations.EmployeeCareerImp;
import ma.MaCNSS.DAO.Implementations.Person.EmployeeImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.Entities.Company;
import ma.MaCNSS.Entities.EmployeeCareer;
import ma.MaCNSS.Entities.Personnes.Employee;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.Helpers.GMailer;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.Helpers.TextColor;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class EmployeeServices {
    static Scanner scanner = new Scanner(System.in);
    static EmployeeImp employeeImp = new EmployeeImp();
    static Employee employee = new Employee();

    public static Employee login() {
        boolean quite;
        int loginCount = 0;
        do {
            System.out.println(TextColor.yellowText("\t\tCOMPANY LOGIN"));

            System.out.print("Enter your email: ");
            employee.setEmail(scanner.next());

            try {
                Employee loggedEmployee = employeeImp.findByEmail(employee.getEmail());
                if (loggedEmployee != null) {
                    System.out.print("Enter your password: ");
                    employee.setPassword(scanner.next());

                    if (employee.getPassword().equals(loggedEmployee.getPassword())) {
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        try {
                            long emailSentTime = System.currentTimeMillis(); // Record the time when the email is sent
                            boolean emailSent = new GMailer().sendEmail(String.valueOf(randomNumber), "email confirmation", employee.getEmail());
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
                                        return employee;
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
        } while (loginCount < 3 && !quite);

        if (quite) {
            return null;
        }
        System.out.println("You have exceeded 3 login attempts. Try later!");
        return null;
    }

    public static Employee add(Company company) {

        try {
            PatientImp patientImp = new PatientImp();
            Patient patient;

            float salary;
            boolean isPresent = true;
            do {
                String immatricule = PmScanner.takeStringInputValue("Enter immatricule: ");
                patient = patientImp.getPtient(immatricule);

                if (patient != null) {
                    System.out.println("We already register this employee as a patient in our system");

                } else {
                    System.out.println("Let's create this patient :)");
                    patient = PatientServices.add();
                }

                String birthdayStr = PmScanner.takeStringInputValue("Enter your birthdayDate (dd/mm/yyyy): ");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date birthdayDate = sdf.parse(birthdayStr);

                employee = new Employee(patient, birthdayDate, isPresent);
                employeeImp.add(employee);

                int workDays = 0;
                salary = PmScanner.takeFloatInputValue("Enter salary: ");
                EmployeeCareerImp employeeCareerImp = new EmployeeCareerImp();
                EmployeeCareer employeeCareer = new EmployeeCareer(company, employee, birthdayDate, salary, workDays);
                employeeCareerImp.add(employeeCareer);

            } while (patient == null && employee == null);

        } catch (Exception ex) {
            System.out.println(TextColor.yellowText(ex.getMessage()));
        }
        return null;
    }

    public static boolean hasRetrainment(String immatricule){
        EmployeeCareerImp employeeCareerImp = new EmployeeCareerImp();
        int workDays = employeeCareerImp.getWorkDays(immatricule);

        if (workDays < 1320){
            System.out.println("Your points !enought for the retrainment");
            System.err.println(TextColor.yellowText("GOO BACK TO THE FUCKING WORK"));
            return false;
        }
        return true;
    }
    public static float RetraitmentSalary(String immatricule) {
        float retrainmentSalary = 0.0F;
        if (!hasRetrainment(immatricule)){
            return retrainmentSalary;
        }
        EmployeeCareerImp employeeCareerImp = new EmployeeCareerImp();
        int workDays = employeeCareerImp.getWorkDays(immatricule);

        if (workDays >= 3240) {
            // supp => supplementaires
            int supp = (workDays - 3240) / 216;

            // MAX SUPP = 70%  ===> (50% + SUPP) ===> the SUPP must be <= 20
            if(supp > 20){
                supp = 20;
            }
            float last96MonthsSalaryAvg = employeeCareerImp.getLast96MonthsSalaryAvg(immatricule);
            retrainmentSalary = last96MonthsSalaryAvg * (0.5F + (supp / 100F));

            System.out.println("Your Retrainment Salary is " + retrainmentSalary);
        } else if (workDays >= 1320) {
            float last96MonthsSalaryAvg = employeeCareerImp.getLast96MonthsSalaryAvg(immatricule);
            retrainmentSalary = last96MonthsSalaryAvg * 0.5F;

            System.out.println("Your Retrainment Salary is " + retrainmentSalary);
        }
        return  retrainmentSalary;
    }

    public static boolean canTakeMoney(String immatricule){
        boolean can = false;
        try {
            employee = employeeImp.getEmployee(immatricule);
            // Get the employee's birthday as a java.util.Date
            Date birthday = employee.getBirthdayDate();
            LocalDate birthdate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate currentDate = LocalDate.now();
            Period age = Period.between(birthdate, currentDate);

            int years = age.getYears();
            if (years >= 55){
                System.out.println("mbrouk l wld lmima, db moumkin t9ross l7it ykhorjo l floass HAHAHAHAHA...");
                can = true;
            }else{
                System.out.println("wa sbaaar a m3alem ra yallah 3andek " + years + "3am");
            }
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return can;
    }


    public static int updatePresenceStatus(){
        String immatricule = PmScanner.takeStringInputValue("Enter Employee imamatricule: ");
        try {
            employee = employeeImp.getEmployee(immatricule);
            System.out.println("0 for absance, 1 for present");
            int status = PmScanner.takeUserChoice(0,1);
            if(status == 1){
            employeeImp.updatePresenceStatus(immatricule, true);
            }else {
            employeeImp.updatePresenceStatus(immatricule, false);
            }
            System.out.println(TextColor.greenText("Updated successfully :)"));
        }catch (SQLException ex){
            System.out.println(TextColor.yellowText(ex.getMessage()));
        }
        return 0;
    }


}