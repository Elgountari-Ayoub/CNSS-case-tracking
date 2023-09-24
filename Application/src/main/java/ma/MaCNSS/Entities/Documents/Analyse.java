package ma.MaCNSS.Entities.Documents;

import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.abstractClasses.Document;

public class Analyse extends Document {
    private Laboratoire laboratoire ;

    public Analyse(int id, float prix, float taux, String description, Laboratoire laboratoire) {
        super(id, prix, taux, description);
        this.laboratoire = laboratoire;
    }

    public Analyse() {
    }

    public Laboratoire getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(Laboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }
}
