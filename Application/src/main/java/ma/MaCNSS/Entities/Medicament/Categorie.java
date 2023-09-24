package ma.MaCNSS.Entities.Medicament;

import java.util.List;

public class Categorie {
    private int id ;
    private float taux ;
    private String label ;
    private List<Medicament> medicamentList ;

    public Categorie(int id, float taux, String label) {
        this.id = id;
        this.taux = taux;
        this.label = label;
    }

    public Categorie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getTaux() {
        return taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
