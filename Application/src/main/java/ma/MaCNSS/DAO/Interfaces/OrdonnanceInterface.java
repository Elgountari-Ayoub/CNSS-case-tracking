package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Medcine;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;

import java.sql.SQLException;
import java.util.List;

public interface OrdonnanceInterface {
    public boolean add(Ordonnance ordonnance, Dossier dossier, Medcine medcine)
            throws SQLException;

    public boolean update(Ordonnance ordonnance)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Ordonnance getOrdonnance(int id)
            throws SQLException;

    public List<Ordonnance> getOrdonnances()
            throws SQLException;

}
