package ma.MaCNSS.Entities;

import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Medicament.Medicament;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.enums.Etat;

import java.util.List;

public class Dossier {
    private int matricule;
    private Etat etat = Etat.ACCEPTED;
    private List<Medicament> medicamentList;

    private List<Analyse> analyseList;
    private List<Ordonnance> ordonnanceList;
    private List<Radio> radioList;
    private List<Scanner> scannerList;

    private AgentCNSS agentCNSS;
    private Patient patient;

    public List<Medicament> getMedicamentList() {
        return medicamentList;
    }


    public void setMedicamentList(List<Medicament> medicamentList) {
        this.medicamentList = medicamentList;
    }

    public Dossier(int matricule, Etat etat, AgentCNSS agentCNSS, Patient patient) {
        this.matricule = matricule;
        this.etat = etat;
        this.agentCNSS = agentCNSS;
        this.patient = patient;
    }

    public Dossier(Etat etat, AgentCNSS agentCNSS, Patient patient) {
        this.etat = etat;
        this.agentCNSS = agentCNSS;
        this.patient = patient;
    }

    public Dossier() {
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public List<Analyse> getAnalyseList() {
        return analyseList;
    }

    public void setAnalyseList(List<Analyse> analyseList) {
        this.analyseList = analyseList;
    }

    public List<Ordonnance> getOrdonnanceList() {
        return ordonnanceList;
    }

    public void setOrdonnanceList(List<Ordonnance> ordonnanceList) {
        this.ordonnanceList = ordonnanceList;
    }

    public List<Radio> getRadioList() {
        return radioList;
    }

    public void setRadioList(List<Radio> radioList) {
        this.radioList = radioList;
    }

    public List<Scanner> getScannerList() {
        return scannerList;
    }

    public void setScannerList(List<Scanner> scannerList) {
        this.scannerList = scannerList;
    }

    public AgentCNSS getAgentCNSS() {
        return agentCNSS;
    }

    public void setAgentCNSS(AgentCNSS agentCNSS) {
        this.agentCNSS = agentCNSS;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
