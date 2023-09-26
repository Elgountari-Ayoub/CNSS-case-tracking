package ma.MaCNSS.DAO.Interfaces.Documents;

import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.SQLException;
import java.util.List;

public interface ScannerInterface {
    public boolean add(Scanner scanner)
            throws SQLException;

    public boolean update(Scanner scanner)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Scanner getScanner(int id)
            throws SQLException;

    public List<Scanner> getScanners()
            throws SQLException;

}
