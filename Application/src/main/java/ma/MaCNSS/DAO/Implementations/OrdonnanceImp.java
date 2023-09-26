package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;

import ma.MaCNSS.DAO.Interfaces.OrdonnanceInterface;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Medcine;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrdonnanceImp implements OrdonnanceInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Ordonnance ordonnance, Dossier dossier, Medcine medcine) {
        String sql = "INSERT INTO ordonnance" +
                " (prix, taux, description, dossier_matricule, medcine_inpe) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setFloat(1, ordonnance.getPrix());
            ps.setFloat(2, ordonnance.getTaux());
            ps.setString(3, ordonnance.getDescription());
            ps.setInt(4, dossier.getMatricule());
            ps.setInt(5, medcine.getINPE());

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