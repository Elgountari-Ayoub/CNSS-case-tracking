package ma.MaCNSS.Entities.Organisme;

import ma.MaCNSS.Entities.abstractClasses.Organisme;

public class Medcine extends Organisme {
    private String nom ;
    private String prenom ;


    public Medcine() {

    }

    public Medcine(int INPE, String adress, String nom, String prenom) {
        super(INPE, adress);
        this.nom = nom;
        this.prenom = prenom;

    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


}
