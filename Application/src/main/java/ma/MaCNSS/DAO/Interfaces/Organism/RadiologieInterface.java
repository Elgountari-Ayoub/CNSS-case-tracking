package ma.MaCNSS.DAO.Interfaces.Organism;


import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.SQLException;
import java.util.List;

public interface RadiologieInterface {
    public boolean add(Radiologie radiologie)
            throws SQLException;

    public boolean update(Radiologie radiologie)
            throws SQLException;

    public boolean delete(String inpe)
            throws SQLException;

    public Radiologie getRadiologie(String inpe)
            throws SQLException;

    public List<Radiologie> getRadiologies()
            throws SQLException;

}
