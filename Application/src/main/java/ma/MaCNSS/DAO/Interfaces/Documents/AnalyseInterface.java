package ma.MaCNSS.DAO.Interfaces.Documents;

import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;

import java.sql.SQLException;
import java.util.List;

public interface AnalyseInterface {
    public boolean add(Analyse analyse)
            throws SQLException;

    public boolean update(Analyse analyse)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Analyse getAnalyse(int id)
            throws SQLException;

    public List<Analyse> getAnalyses()
            throws SQLException;

}
