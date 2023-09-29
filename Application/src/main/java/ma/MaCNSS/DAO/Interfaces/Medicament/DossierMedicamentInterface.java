package ma.MaCNSS.DAO.Interfaces.Medicament;

import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Medicament.DossierMedicament;
import ma.MaCNSS.Entities.Medicament.Medicament;

import java.sql.SQLException;
import java.util.List;

public interface DossierMedicamentInterface {
    public boolean add(DossierMedicament dossierMedicament)
            throws SQLException;
    public boolean update(DossierMedicament dossierMedicament)
            throws SQLException;
    public boolean delete(String code_bare)
            throws SQLException;
    public DossierMedicament getDossierMedicament(String code_bare)
            throws SQLException;
    public List<DossierMedicament> getDossierMedicaments()
            throws SQLException;

}
