package ma.MaCNSS.Entities.Personnes;

import ma.MaCNSS.enums.Genre;

import java.util.Date;

public class Employee extends Patient {
    private Date birthdayDate;
    private Boolean isPresent ;


    public Employee (String immatricule, String CIN, String nom, String prenom, String ville, String telephone, String email, String password, Genre genre, Date birthdayDate, Boolean isPresent) {
        super(immatricule, CIN, nom, prenom, ville, telephone, email, password, genre);
        this.birthdayDate = birthdayDate;
        this.isPresent = isPresent;
    }
    public Employee (Patient patient, Date birthdayDate, Boolean isPresent) {
        super(patient.getImmatricule(), patient.getCIN(), patient.getNom(), patient.getPrenom(), patient.getVille(), patient.getTelephone(), patient.getEmail(), patient.getPassword(), patient.getGenre());
        this.birthdayDate = birthdayDate;
        this.isPresent = isPresent;
    }

    public Employee(){}

    public Date getBirthdayDate() {
        return this.birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public Boolean getPresent() {
        return this.isPresent;
    }

    public void setPresent(Boolean present) {
        this.isPresent = present;
    }
}
