package ma.MaCNSS.DAO.Implementations.Documents;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Documents.ScannerInterface;
import ma.MaCNSS.Entities.Documents.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ScannerImp implements ScannerInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Scanner scanner) {
        String sql = "INSERT INTO scanner" +
                " (radiologie_inpe, prix, taux, description, dossier_matricule, type) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, scanner.getRadiologie().getINPE());
            ps.setFloat(2, scanner.getPrix());
            ps.setFloat(3, scanner.getTaux());
            ps.setString(4, scanner.getDescription());
            ps.setInt(5, scanner.getDossier().getMatricule());
            ps.setString(6, scanner.getType());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Scanner: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Scanner scanner) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Scanner getScanner(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Scanner> getScanners() throws SQLException {
        return null;
    }

}