package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.SQLException;
import java.util.List;

public interface ScannerInterface {
    public boolean add(Scanner scanner, Dossier dossier, Radiologie radiologie)
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
