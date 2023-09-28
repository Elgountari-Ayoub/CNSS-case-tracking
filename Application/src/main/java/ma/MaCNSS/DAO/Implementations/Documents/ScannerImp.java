package ma.MaCNSS.DAO.Implementations.Documents;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Documents.ScannerInterface;
import ma.MaCNSS.Entities.Documents.Scanner;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScannerImp implements ScannerInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Scanner scanner) {
        String sql = "INSERT INTO scanner" +
                " (radiologie_inpe, prix, description, dossier_matricule, type) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            System.out.println(scanner.getDossier().getMatricule());
            ps.setString(1, scanner.getRadiologie().getINPE());
            ps.setFloat(2, scanner.getPrix());
            ps.setString(3, scanner.getDescription());
            ps.setInt(4, scanner.getDossier().getMatricule());
            ps.setString(5, scanner.getType());

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

    public float getScannerTauxByType(String type){
        String sql = "Select taux from scannerType where type = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                float taux = resultSet.getFloat("taux");
                return taux;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the rscanner: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Scanner> getScanners() throws SQLException {
        return null;
    }

}