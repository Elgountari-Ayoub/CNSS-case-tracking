package ma.MaCNSS.DAO.Implementations.Documents;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Documents.RadioInterface;

import ma.MaCNSS.Entities.Documents.Radio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RadioImp implements RadioInterface {
    static Connection con = DBConnection.getConnection();
    @Override
public boolean add(Radio radio) {
        String sql = "INSERT INTO radio" +
                " (radiologie_inpe, prix, description, dossier_matricule, type) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, radio.getRadiologie().getINPE());
            ps.setFloat(2, radio.getPrix());
            ps.setString(3, radio.getDescription());
            ps.setInt(4, radio.getDossier().getMatricule());
            ps.setString(5, radio.getType());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Radio: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Radio radio) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Radio getRadio(int id) throws SQLException {
        return null;
    }

    public float getRadioTauxByType(String type){
        String sql = "Select taux from radioType where type = ?";

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
            System.err.println("Error getting the radio taux: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Radio> getRadios() throws SQLException {
        return null;
    }
}