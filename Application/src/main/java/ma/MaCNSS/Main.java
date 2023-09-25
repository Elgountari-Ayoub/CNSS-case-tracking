package ma.MaCNSS;

import ma.MaCNSS.DAO.Implementations.AgentCNSSImp;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.enums.Genre;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre
        AgentCNSS agentCNSS = new AgentCNSS("pm01", "P", "M", "PM's world", "0713244063", "pm@gmail.com", "pm01", Genre.HOMME);
        AgentCNSSImp agentCNSSImp = new AgentCNSSImp();
        agentCNSSImp.add(agentCNSS);
    }
}