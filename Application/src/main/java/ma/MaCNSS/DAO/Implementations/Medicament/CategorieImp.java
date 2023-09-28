package ma.MaCNSS.DAO.Implementations.Medicament;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Medicament.CategorieInterface;
import ma.MaCNSS.Entities.Medicament.Categorie;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategorieImp implements CategorieInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Categorie categorie) {
        String sql = "INSERT INTO categorie" +
                " (taux, label) VALUES" +
                " (?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setFloat(1, categorie.getTaux());
            ps.setString(2, categorie.getLabel());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Categorie: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Categorie categorie) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Categorie getCategorie(int id) throws SQLException {
        return null;
    }
    public Categorie getCategorie(String label) throws SQLException {
        String sql = "SELECT * FROM categorie WHERE label = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, label);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                float taux = resultSet.getFloat("taux");

                return new Categorie(id, taux, label);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the categorie: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Categorie> getCategories() throws SQLException {
        return null;
    }
}