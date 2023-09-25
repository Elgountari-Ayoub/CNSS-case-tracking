package ma.MaCNSS.Entities.abstractClasses;

import ma.MaCNSS.enums.Genre;

public abstract class Personne {
    private String CIN ;
    private String nom ;
    private String prenom ;
    private String ville ;
    private String telephone ;
    private String email ;
    private String password ;
    private Genre genre ;

    public Personne() {
    }

    public Personne(String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.ville = ville;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.genre = genre;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
