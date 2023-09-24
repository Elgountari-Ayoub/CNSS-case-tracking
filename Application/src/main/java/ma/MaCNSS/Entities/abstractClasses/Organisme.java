package ma.MaCNSS.Entities.abstractClasses;

public abstract class Organisme {
    private int INPE ;
    private String adress ;

    public Organisme() {
    }

    public Organisme(int INPE, String adress) {
        this.INPE = INPE;
        this.adress = adress;
    }

    public int getINPE() {
        return INPE;
    }

    public void setINPE(int INPE) {
        this.INPE = INPE;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
