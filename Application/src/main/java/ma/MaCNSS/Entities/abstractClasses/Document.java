package ma.MaCNSS.Entities.abstractClasses;

public abstract class Document {
    private int id ;
    private float prix ;
    private float taux ;
    private String description ;

    public Document() {
    }

    public Document(int id, float prix, float taux, String description) {
        this.id = id;
        this.prix = prix;
        this.taux = taux;
        this.description = description;
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
}
