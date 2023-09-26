package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.AnalyseInterface;
import ma.MaCNSS.DAO.Interfaces.RadioInterface;
import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RadioImp implements RadioInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Radio radio, Dossier dossier, Radiologie radiologie) {
        String sql = "INSERT INTO analysee" +
                " (radiologie_inpe, prix, taux, description, dossier_matricule, type) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, radiologie.getINPE());
            ps.setFloat(2, radio.getPrix());
            ps.setFloat(3, radio.getTaux());
            ps.setString(4, radio.getDescription());
            ps.setInt(5, dossier.getMatricule());
            ps.setString(6, radio.getType());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Analyse: " + e.getMessage());
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

    @Override
    public List<Radio> getRadios() throws SQLException {
        return null;
    }
}