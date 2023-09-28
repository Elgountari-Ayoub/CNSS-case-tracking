package ma.MaCNSS.Entities.Medicament;

import ma.MaCNSS.Entities.Dossier;

public class Medicament {
    private int code_barre ;
    private float prix ;
    private String label ;
    private Categorie categorie;

    private Dossier dossier;

    public Medicament(int code_barre, float prix, String label, Dossier dossier, Categorie categorie) {
        this.code_barre = code_barre;
        this.prix = prix;
        this.label = label;
        this.dossier = dossier;
        this.categorie = categorie;
    }

    public Medicament(float prix, String label, Dossier dossier, Categorie categorie) {
        this.code_barre = code_barre;
        this.prix = prix;
        this.label = label;
        this.dossier = dossier;
        this.categorie = categorie;
    }

    public Medicament() {
    }

    public int getCode_barre() {
        return code_barre;
    }

    public void setCode_barre(int code_barre) {
        this.code_barre = code_barre;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}
