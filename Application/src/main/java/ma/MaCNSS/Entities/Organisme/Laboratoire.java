package ma.MaCNSS.Entities.Organisme;

import ma.MaCNSS.Entities.abstractClasses.Organisme;

public class Laboratoire extends Organisme {
    private String label ;
    

    public Laboratoire(String INPE, String adress, String label) {
        super(INPE, adress);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
