package ma.MaCNSS.DAO.Implementations.Medicament;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Medicament.MedicamentInterface;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Medicament.Medicament;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MedicamentImp implements MedicamentInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Medicament medicament) {
        String sql = "INSERT INTO medicament" +
                " (code_bare, prix, label, dossier_matricule, categorie_id) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, medicament.getCode_barre());
            ps.setFloat(2, medicament.getPrix());
            ps.setString(3, medicament.getLabel());
            ps.setInt(4, medicament.getDossier().getMatricule());
            ps.setInt(5, medicament.getCategorie().getId());

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
        return null;
    }

    @Override
    public List<Radio> getMedicaments() throws SQLException {
        return null;
    }
}