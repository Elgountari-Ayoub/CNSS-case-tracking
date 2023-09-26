package ma.MaCNSS.Entities.Documents;

import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.abstractClasses.Document;

public class Scanner extends Document {
    private String type ;
    private Radiologie radiologie ;

    public Scanner(int id, float prix, float taux, String description, String type, Radiologie radiologie, Dossier dossier) {
        super(id, prix, taux, description, dossier);
        this.type = type;
        this.radiologie = radiologie;
    }

    public Scanner() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Radiologie getRadiologie() {
        return radiologie;
    }

    public void setRadiologie(Radiologie radiologie) {
        this.radiologie = radiologie;
    }
}
