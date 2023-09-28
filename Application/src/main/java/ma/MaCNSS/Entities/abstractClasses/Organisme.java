package ma.MaCNSS.Entities.abstractClasses;

public abstract class Organisme {
    private String INPE ;
    private String adress ;

    public Organisme() {
    }

    public Organisme(String INPE, String adress) {
        this.INPE = INPE;
        this.adress = adress;
    }
    public Organisme(String adress) {
        this.adress = adress;
    }

    public String getINPE() {
        return INPE;
    }

    public void setINPE(String INPE) {
        this.INPE = INPE;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
