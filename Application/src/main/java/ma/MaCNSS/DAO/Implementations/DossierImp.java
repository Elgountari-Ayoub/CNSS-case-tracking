package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.DossierInterface;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DossierImp implements DossierInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Dossier dossier) {
        String sql = "INSERT INTO dossier" +
                " (matricule, etat, agentcnss_cin, patient_immatricule) VALUES" +
                " (?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,dossier.getMatricule());
            ps.setString(2, dossier.getEtat());
            ps.setString(3, dossier.getAgentCNSS().getCIN());
            ps.setString(4, dossier.getPatient().getImmatricule());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Dossier: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Dossier dossier) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int matricule) throws SQLException {
        return false;
    }

    @Override
    public Dossier getDossier(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Dossier> getDossiers() throws SQLException {
        return null;
    }


}