package ma.MaCNSS;

import ma.MaCNSS.DAO.Implementations.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.PatientImp;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.enums.Genre;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        //String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre
//        AgentCNSS agentCNSS = new AgentCNSS("pm01", "P", "M", "PM's world", "0713244063", "pm@gmail.com", "pm01", Genre.HOMME);
//        AgentCNSSImp agentCNSSImp = new AgentCNSSImp();
//        agentCNSSImp.add(agentCNSS);
        Patient patient;
        PatientImp patientImp;


        for (int i = 1; i < 100; i++) {
            patient = new Patient
                    (String.valueOf(i * 1000),
                    "H" + String.valueOf(i),
                            "p-nom" + String.valueOf(i),
                            "p-prenom" + String.valueOf(i),

                    "Safi", "06" + String.valueOf(i),
                            "p"+ String.valueOf(i) +"@gmail.com", "passp" + String.valueOf(i),
                    Genre.HOMME);
            patientImp = new PatientImp();
            patientImp.add(patient);
        }
    }

}