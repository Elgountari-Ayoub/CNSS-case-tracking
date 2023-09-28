package ma.MaCNSS.Entities.abstractClasses;

import ma.MaCNSS.Entities.Dossier;

public abstract class Document {
    private int id;
    private float prix;
    private float taux;
    private String description;
    private Dossier dossier;

    public Document() {
    }

    public Document(int id, float prix, float taux, String description, Dossier dossier) {
        this.id = id;
        this.prix = prix;
        this.taux = taux;
        this.description = description;
        this.dossier = dossier;
    }
    public Document(float prix, float taux, String description, Dossier dossier) {
        this.prix = prix;
        this.taux = taux;
        this.description = description;
        this.dossier = dossier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Dossier getDossier() {
        return this.dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }
}
