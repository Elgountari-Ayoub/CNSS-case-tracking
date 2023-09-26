package ma.MaCNSS.DAO.Interfaces.Documents;

import ma.MaCNSS.Entities.Documents.Ordonnance;

import java.sql.SQLException;
import java.util.List;

public interface OrdonnanceInterface {
    public boolean add(Ordonnance ordonnance)
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
