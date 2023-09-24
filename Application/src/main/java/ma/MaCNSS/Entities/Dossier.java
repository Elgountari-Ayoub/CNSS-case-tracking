package ma.MaCNSS.Entities;

import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Medicament.Medicament;

import java.util.List;

public class Dossier {
    private  int matricule ;
    private String etat ;
    private List<Medicament> medicamentList ;

    private List<Analyse> analyseList ;
    private List<Ordonnance> ordonnanceList ;
    private List<Radio> radioList ;
    private List<Scanner> scannerList ;

    public List<Medicament> getMedicamentList() {
        return medicamentList;
    }


    public void setMedicamentList(List<Medicament> medicamentList) {
        this.medicamentList = medicamentList;
    }

    public Dossier(int matricule, String etat) {
        this.matricule = matricule;
        this.etat = etat;
    }

    public Dossier() {
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
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
}
