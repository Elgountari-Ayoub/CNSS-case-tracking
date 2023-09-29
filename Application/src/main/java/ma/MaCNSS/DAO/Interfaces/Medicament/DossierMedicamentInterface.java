package ma.MaCNSS.DAO.Interfaces.Medicament;

import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Medicament.Medicament;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

public interface MedicamentInterface {
    public boolean add(Medicament medicament)
            throws SQLException;
    public boolean update(Medicament medicament)
            throws SQLException;
    public boolean delete(String code_bare)
            throws SQLException;
    public Medicament getMedicament(String code_bare)
            throws SQLException;
    public List<Radio> getMedicaments()
            throws SQLException;

}
