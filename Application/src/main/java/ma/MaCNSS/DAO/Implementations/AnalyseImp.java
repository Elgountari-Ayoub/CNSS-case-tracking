package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.AnalyseInterface;
import ma.MaCNSS.DAO.Interfaces.OrdonnanceInterface;
import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Documents.Ordonnance;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AnalyseImp implements AnalyseInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Analyse analyse, Dossier dossier, Laboratoire laboratoire) {
        String sql = "INSERT INTO analysee" +
                " (laboratoire_inpe, prix, taux, description, dossier_matricule, type) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, laboratoire.getINPE());
            ps.setFloat(2, analyse.getPrix());
            ps.setFloat(3, analyse.getTaux());
            ps.setString(4, analyse.getDescription());
            ps.setInt(5, dossier.getMatricule());
            ps.setString(6, analyse.getType());

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
    public boolean update(Analyse analyse) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Analyse getAnalyse(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Analyse> getAnalyses() throws SQLException {
        return null;
    }


}