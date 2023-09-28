package ma.MaCNSS.DAO.Implementations.Organism;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Organism.LaboratoireInterface;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LaboratoireImp implements LaboratoireInterface {
    static Connection con = DBConnection.getConnection();

    @Override
    public boolean add(Laboratoire laboratoire) {
        String sql = "INSERT INTO laboratoire" +
                " (INPE, address, label) VALUES" +
                " (?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, laboratoire.getINPE());
            ps.setString(2, laboratoire.getAdress());
            ps.setString(3, laboratoire.getLabel());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Laboratoire: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Laboratoire laboratoire) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Laboratoire getLaboratoire(String inpe) throws SQLException {
        String sql = "SELECT * FROM laboratoire WHERE inpe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, inpe);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String adress, label;
                adress = resultSet.getString("address");
                label = resultSet.getString("label");

                return new Laboratoire(inpe, adress, label);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the radiologie: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Laboratoire> getLaboratoires() throws SQLException {
        return null;
    }


}