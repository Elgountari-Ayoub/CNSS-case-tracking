package ma.MaCNSS.Services;


import ma.MaCNSS.DAO.Implementations.DossierImp;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Interfaces.DossierInterface;
import ma.MaCNSS.DAO.Interfaces.Person.AgentCNSSInterface;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Helpers.GMailer;
import ma.MaCNSS.Helpers.PmScanner;
import ma.MaCNSS.enums.Genre;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class DossierServices {
    Scanner scanner = new Scanner(System.in);
    AgentCNSSInterface agentCNSSDao = new AgentCNSSImp();

    DossierInterface dossierDoa = new DossierImp();

    public static Dossier add() {
        // HA LKHEDMA DYAL BSS7
        return null;
    }



}