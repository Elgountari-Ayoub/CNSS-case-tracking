package ma.MaCNSS.Entities.Medicament;

import ma.MaCNSS.Entities.Dossier;

public class DossierMedicament {
    private Medicament medicament ;
    private Dossier dossier;

    public DossierMedicament(Dossier dossier, Medicament medicament) {
        this.dossier = dossier;
        this.medicament = medicament;
    }

    public DossierMedicament() {
    }
    public Dossier getDossier() {
        return dossier;
    }

    public void setDossier(Dossier dossier) {
        this.dossier = dossier;
    }

    public Medicament getMedicament() {
        return this.medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}
