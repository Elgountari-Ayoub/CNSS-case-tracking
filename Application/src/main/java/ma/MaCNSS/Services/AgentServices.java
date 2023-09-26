package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.AgentCNSSImp;
import ma.MaCNSS.DAO.Interfaces.AgentCNSSInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Helpers.GMailer;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class AgentServices implements PersonServices {
    static Scanner scanner = new Scanner(System.in);
    static AgentCNSSInterface agentCNSSDao = new AgentCNSSImp();
    public  Boolean login(){
        AgentCNSS agentCNSS = new AgentCNSS();
        System.out.print("entre your email ");
        agentCNSS.setEmail(scanner.next()) ;
        System.out.print("entre your password ");
        agentCNSS.setPassword(scanner.next());

           try{
               AgentCNSS loggedInAgent = agentCNSSDao.findByEmail(agentCNSS);
                if (loggedInAgent != null){
                    //if (BCrypt.checkpw(agentCNSS.getPassword(), loggedInAgent.getPassword())) {
                    if (agentCNSS.getPassword().equals(loggedInAgent.getPassword())){
                        //GMailer.send();
                        //System.out.println("Password is correct");
                        int randomNumber = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
                        new GMailer().sendEmail(String.valueOf(randomNumber),"email confirmation",agentCNSS.getEmail());


                            System.out.print("entre verification code :  ");
                            String userInput = scanner.next();
                            if(userInput.equals(randomNumber)){
                                System.out.println("correct");
                                return true ;
                            }


                    } else {
                        System.err.println("Password is incorrect");
                        return false ;
                    }
                }else {
                    System.out.println("email not correct");
                }
           }catch(SQLException ex){
               System.err.println("message : " + ex.getMessage());
           }
    return false ;
    }

}
