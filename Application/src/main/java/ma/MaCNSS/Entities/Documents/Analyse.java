package ma.MaCNSS.Entities.Documents;

import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.abstractClasses.Document;

public class Analyse extends Document {
    private Laboratoire laboratoire ;
    private String type;

    public Analyse(int id, float prix, float taux, String description, Laboratoire laboratoire, String type, Dossier dossier) {
        super(id, prix, taux, description, dossier);
        this.laboratoire = laboratoire;
        this.type = type;
    }

    public Analyse(Laboratoire laboratoire, float prix, float taux, String description, String type, Dossier dossier) {
        super(prix, taux, description, dossier);
        this.laboratoire = laboratoire;
        this.type = type;
    }
    public Analyse() {
    }

    public Laboratoire getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(Laboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
