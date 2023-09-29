package ma.MaCNSS.Entities.Medicament;

import ma.MaCNSS.Entities.Dossier;

public class Medicament {
    private String code_barre ;
    private float prix ;
    private String label ;
    private Categorie categorie;


/*
* Delete the dossier from the Medicament class imp
* */
    public Medicament(String code_barre, float prix, String label, Categorie categorie) {
        this.code_barre = code_barre;
        this.prix = prix;
        this.label = label;
        this.categorie = categorie;
    }


    public Medicament() {
    }

    public String getCode_barre() {
        return code_barre;
    }

    public void setCode_barre(String code_barre) {
        this.code_barre = code_barre;
    }

    public float getPrix() {
        return this.prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Categorie getCategorie() {
        return this.categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }
}
