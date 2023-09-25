package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.AgentCNSSImp;
import ma.MaCNSS.DAO.Interfaces.AgentCNSSInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;

import java.sql.SQLException;
import java.util.Scanner;

public class AgentServices {
    Scanner scanner = new Scanner(System.in);
    AgentCNSSInterface agentCNSSDao = new AgentCNSSImp();
    public void login(){
        AgentCNSS agentCNSS = new AgentCNSS();
        System.out.print("entre your email ");
        agentCNSS.setEmail(scanner.next()) ;
        System.out.print("entre your password ");
        agentCNSS.setPassword(scanner.next());

           try{
               AgentCNSS loggedInAgent = agentCNSSDao.findByEmail(agentCNSS);
                if (loggedInAgent != null){

                }
           }catch(SQLException ex){
                ex.printStackTrace();;
           }

    }

}
