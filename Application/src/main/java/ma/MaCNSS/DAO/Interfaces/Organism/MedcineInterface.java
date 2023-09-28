package ma.MaCNSS.DAO.Interfaces.Organism;

import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;

import java.sql.SQLException;
import java.util.List;

public interface MedcineInterface {
    public boolean add(Medcine medcine)
            throws SQLException;

    public boolean update(Medcine medcine)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Medcine getMedcine(String inpe)
            throws SQLException;

    public List<Medcine> getMedcines()
            throws SQLException;

}
