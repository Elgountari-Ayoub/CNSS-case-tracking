package ma.MaCNSS.DAO.Implementations.Organism;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Organism.MedcineInterface;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MedcineImp implements MedcineInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Medcine medcine) {
        String sql = "INSERT INTO medcine" +
                " (INPE, address, nom, prenom, type) VALUES" +
                " (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, medcine.getINPE());
            ps.setString(2, medcine.getAdress());
            ps.setString(3, medcine.getNom());
            ps.setString(4, medcine.getPrenom());
            ps.setString(5, medcine.getTypeMedcine().toString());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Medcine: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Medcine medcine) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Medcine getMedcine(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Medcine> getMedcines() throws SQLException {
        return null;
    }

}