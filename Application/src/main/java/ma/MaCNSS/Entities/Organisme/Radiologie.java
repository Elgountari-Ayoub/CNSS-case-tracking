package ma.MaCNSS.Entities.Organisme;

import ma.MaCNSS.Entities.abstractClasses.Organisme;

public class Radiologie extends Organisme {
    private String label ;

    public Radiologie(int INPE, String adress, String label) {
        super(INPE, adress);
        this.label = label;
    }

    public Radiologie() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
