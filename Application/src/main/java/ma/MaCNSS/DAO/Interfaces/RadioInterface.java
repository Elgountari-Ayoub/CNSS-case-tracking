package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.SQLException;
import java.util.List;

public interface RadioInterface {
    public boolean add(Radio radio, Dossier dossier, Radiologie radiologie)
            throws SQLException;

    public boolean update(Radio radio)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Radio getRadio(int id)
            throws SQLException;

    public List<Radio> getRadios()
            throws SQLException;

}
