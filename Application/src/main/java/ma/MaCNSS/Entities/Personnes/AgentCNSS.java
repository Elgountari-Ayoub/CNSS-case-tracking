package ma.MaCNSS.Entities.Personnes;

import ma.MaCNSS.Entities.abstractClasses.Personne;
import ma.MaCNSS.enums.Genre;

public class AgentCNSS extends Personne {

    public AgentCNSS(){
    }
    public AgentCNSS(String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre) {
        super(CIN, nom, prenom, ville, telephone, email, password, genre);
    }
}
