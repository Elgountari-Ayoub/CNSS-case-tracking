package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;

import java.sql.SQLException;
import java.util.List;

public interface DossierInterface {
    public boolean add(Dossier dossier)
            throws SQLException;

    public boolean update(Dossier dossier)
            throws SQLException;

    public boolean delete(int matricule)
            throws SQLException;

    public Dossier getDossier(int id)
            throws SQLException;

    public List<Dossier> getDossiers()
            throws SQLException;

}
