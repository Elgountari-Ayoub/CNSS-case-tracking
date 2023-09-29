package ma.MaCNSS.Entities.Medicament;

import java.util.List;

public class Categorie {
    private float taux ;
    private String label ;
    private List<Medicament> medicamentList ;

    public Categorie( float taux, String label) {
        this.taux = taux;
        this.label = label;
    }

    public Categorie() {
    }

    public float getTaux() {
        return this.taux;
    }

    public void setTaux(float taux) {
        this.taux = taux;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
