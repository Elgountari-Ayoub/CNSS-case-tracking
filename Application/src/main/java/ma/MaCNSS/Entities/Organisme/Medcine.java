package ma.MaCNSS.Entities.Organisme;

import ma.MaCNSS.Entities.abstractClasses.Organisme;
import ma.MaCNSS.enums.TypeMedcine;

public class Medcine extends Organisme {
    private String nom ;
    private String prenom ;
    private TypeMedcine typeMedcine;


    public Medcine() {

    }

    public Medcine(String INPE, String adress, String nom, String prenom, TypeMedcine typeMedcine) {
        super(INPE, adress);
        this.nom = nom;
        this.prenom = prenom;

        this.typeMedcine = typeMedcine;
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


    public TypeMedcine getTypeMedcine() {
        return typeMedcine;
    }

    public void setTypeMedcine(TypeMedcine typeMedcine) {
        this.typeMedcine = typeMedcine;
    }
}
