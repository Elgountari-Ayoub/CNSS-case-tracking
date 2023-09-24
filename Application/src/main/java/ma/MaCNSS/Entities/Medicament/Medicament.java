package ma.MaCNSS.Entities.Medicament;

public class Medicament {
    private int code_barre ;
    private float prix ;
    private String label ;
    private Categorie categorie ;

    public Medicament(int code_barre, float prix, String label) {
        this.code_barre = code_barre;
        this.prix = prix;
        this.label = label;
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
}
