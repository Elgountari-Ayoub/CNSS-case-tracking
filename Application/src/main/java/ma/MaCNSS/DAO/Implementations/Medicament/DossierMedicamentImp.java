package ma.MaCNSS.DAO.Implementations.Medicament;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Implementations.DossierImp;
import ma.MaCNSS.DAO.Interfaces.Medicament.DossierMedicamentInterface;
import ma.MaCNSS.DAO.Interfaces.Medicament.MedicamentInterface;
import ma.MaCNSS.Entities.Documents.Radio;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Medicament.Categorie;
import ma.MaCNSS.Entities.Medicament.DossierMedicament;
import ma.MaCNSS.Entities.Medicament.Medicament;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DossierMedicamentImp implements DossierMedicamentInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(DossierMedicament dossierMedicament) {
        String sql = "INSERT INTO dossierMedicament" +
                " (code_bare, dossier_matricule) VALUES" +
                " (?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, dossierMedicament.getMedicament().getCode_barre());
            ps.setInt(2, dossierMedicament.getDossier().getMatricule());

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
    public boolean update(DossierMedicament dossierMedicament) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String code_bare) throws SQLException {
        return false;
    }


    @Override
    public DossierMedicament getDossierMedicament(String code_bare) throws SQLException {
        String sql = "Select * from dossierMedicament where code_bare = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, code_bare);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int matricule = resultSet.getInt("matricule");
                DossierImp dossierImp = new DossierImp();
                Dossier dossier = dossierImp.getDossier(matricule);

                MedicamentImp medicamentImp = new MedicamentImp();
                Medicament medicament = medicamentImp.getMedicament(code_bare);

                return new DossierMedicament(dossier, medicament);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the dossier medicamnet: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }




    @Override
    public List<DossierMedicament> getDossierMedicaments() throws SQLException {
        return null;
    }
}