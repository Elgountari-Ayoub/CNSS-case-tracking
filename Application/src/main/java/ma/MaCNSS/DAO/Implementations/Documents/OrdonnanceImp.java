package ma.MaCNSS.DAO.Implementations.Documents;

import ma.MaCNSS.Connection.DBConnection;

import ma.MaCNSS.DAO.Interfaces.Documents.OrdonnanceInterface;

import ma.MaCNSS.Entities.Documents.Ordonnance;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrdonnanceImp implements OrdonnanceInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Ordonnance ordonnance) {
        String sql = "INSERT INTO ordonnance" +
                " (prix, description, dossier_matricule, medcine_inpe) VALUES" +
                " (?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setFloat(1, ordonnance.getPrix());
            ps.setString(2, ordonnance.getDescription());
            ps.setInt(3, ordonnance.getDossier().getMatricule());
            ps.setString(4, ordonnance.getMedcine().getINPE());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Ordonnace: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Ordonnance ordonnance) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Ordonnance getOrdonnance(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Ordonnance> getOrdonnances() throws SQLException {
        return null;
    }


}