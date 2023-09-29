package ma.MaCNSS.Entities.Documents;

import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Medcine;
import ma.MaCNSS.Entities.abstractClasses.Document;

public class Ordonnance extends Document {

    private Medcine medcine ;

    public Ordonnance(int id, float prix, float taux, String description, Medcine medcine, Dossier dossier) {
        super(id, prix, taux, description, dossier);
        this.medcine = medcine;
    }

    public Ordonnance(float prix, float taux, String description, Medcine medcine, Dossier dossier) {
        super(prix, taux, description, dossier);
        this.medcine = medcine;
    }

    public Ordonnance() {
    }

    public Medcine getMedcine() {
        return this.medcine;
    }

    public void setMedcine(Medcine medcine) {
        this.medcine = medcine;
    }
}
