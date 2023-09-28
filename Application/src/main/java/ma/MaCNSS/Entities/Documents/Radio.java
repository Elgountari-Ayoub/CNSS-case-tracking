package ma.MaCNSS.Entities.Documents;

import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.abstractClasses.Document;

public class Radio extends Document {
    private String type ;
    private Radiologie radiologie ;

    public Radio(int id, float prix, float taux, String description, String type, Dossier dossier) {
        super(id, prix, taux, description, dossier);
        this.type = type;
    }

    public Radio(Radiologie radiologie, float prix, float taux, String description, String type, Dossier dossier) {
        super(prix, taux, description, dossier);
        this.type = type;
        this.radiologie = radiologie;
    }

    public Radio() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Radiologie getRadiologie() {
        return this.radiologie;
    }

    public void setRadiologie(Radiologie radiologie) {
        this.radiologie = radiologie;
    }
}
