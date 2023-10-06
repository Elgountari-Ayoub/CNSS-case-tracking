package ma.MaCNSS.DAO.Implementations.Medicament;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Medicament.MedicamentInterface;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Medicament.Categorie;
import ma.MaCNSS.Entities.Medicament.Medicament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MedicamentImp implements MedicamentInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Medicament medicament) {
        String sql = "INSERT INTO medicament" +
                " (code_bare, prix, label, categorie_label) VALUES" +
                " (?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, medicament.getCode_barre());
            ps.setFloat(2, medicament.getPrix());
            ps.setString(3, medicament.getLabel());
            ps.setString(5, medicament.getCategorie().getLabel());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Medicament: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Medicament medicament) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String code_bare) throws SQLException {
        return false;
    }

    @Override
    public Medicament getMedicament(String code_bare) throws SQLException {
        String sql = "Select * from medicament where code_bare = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code_bare);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                float prix = resultSet.getFloat("prix");
                String medicament_label = resultSet.getString("label");
                String categorie_label = resultSet.getString("categorie_label");

                // get the categorie object
                CategorieImp categorieImp = new CategorieImp();
                Categorie categorie = categorieImp.getCategorie(categorie_label);

                return new Medicament(code_bare, prix, medicament_label, categorie);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the medicament: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }




    @Override
    public List<Medicament> getMedicaments() throws SQLException {
        return null;
    }
}