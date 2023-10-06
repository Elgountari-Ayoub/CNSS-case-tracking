package ma.MaCNSS.Entities.Personnes;

import ma.MaCNSS.Entities.abstractClasses.Personne;
import ma.MaCNSS.enums.Genre;

public class Patient  extends Personne {
    private String immatricule ;


    public Patient() {
    }

    public Patient(String immatricule, String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre) {
        super(CIN, nom, prenom, ville, telephone, email, password, genre);
        this.immatricule = immatricule;
    }

    public Patient(String immatricule,AgentCNSS agentCNSS) {
        super(agentCNSS.getCIN(), agentCNSS.getNom(), agentCNSS.getPrenom(), agentCNSS.getVille(), agentCNSS.getTelephone(), agentCNSS.getEmail(), agentCNSS.getPassword(), agentCNSS.getGenre());
        this.immatricule = immatricule;
    }

    public String getImmatricule() {
        return immatricule;
    }

    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }
}
