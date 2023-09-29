package ma.MaCNSS.DAO.Implementations.Documents;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Documents.AnalyseInterface;
import ma.MaCNSS.Entities.Documents.Analyse;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnalyseImp implements AnalyseInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Analyse analyse) {
        String sql = "INSERT INTO analysee" +
                " (laboratoire_inpe, prix, description, dossier_matricule, type) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, analyse.getLaboratoire().getINPE());
            ps.setFloat(2, analyse.getPrix());
            ps.setString(3, analyse.getDescription());
            ps.setInt(4, analyse.getDossier().getMatricule());
            ps.setString(5, analyse.getType());

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

    public float getAnalyseTauxByType(String type){
        String sql = "Select taux from analysetype where type = ?";

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
            System.err.println("Error getting the analyse taux: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public List<Analyse> getAnalyses() throws SQLException {
        return null;
    }


}