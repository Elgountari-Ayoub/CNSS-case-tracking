package ma.MaCNSS.DAO.Implementations.Documents;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Documents.AnalyseInterface;
import ma.MaCNSS.Entities.Documents.Analyse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AnalyseImp implements AnalyseInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Analyse analyse) {
        String sql = "INSERT INTO analysee" +
                " (laboratoire_inpe, prix, taux, description, dossier_matricule, type) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, analyse.getLaboratoire().getINPE());
            ps.setFloat(2, analyse.getPrix());
            ps.setFloat(3, analyse.getTaux());
            ps.setString(4, analyse.getDescription());
            ps.setInt(5, analyse.getDossier().getMatricule());
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