package ma.MaCNSS.Entities.Documents;

import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.abstractClasses.Document;

public class Radio extends Document {
    private String type ;
    private Radiologie radiologie ;

    public Radio(int id, float prix, float taux, String description, String type) {
        super(id, prix, taux, description);
        this.type = type;
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
        return radiologie;
    }

    public void setRadiologie(Radiologie radiologie) {
        this.radiologie = radiologie;
    }
}
