package ma.MaCNSS.DAO.Interfaces.Organism;

import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Organisme.Laboratoire;

import java.sql.SQLException;
import java.util.List;

public interface LaboratoireInterface {
    public boolean add(Laboratoire laboratoire)
            throws SQLException;

    public boolean update(Laboratoire laboratoire)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Laboratoire getLaboratoire(String inpe)
            throws SQLException;

    public List<Laboratoire> getLaboratoires()
            throws SQLException;

}
