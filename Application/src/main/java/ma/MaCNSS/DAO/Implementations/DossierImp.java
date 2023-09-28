package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Interfaces.DossierInterface;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Organisme.Radiologie;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.enums.Etat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DossierImp implements DossierInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Dossier dossier) {
        String sql = "INSERT INTO dossier" +
                " (matricule, etat, agentcnss_cin, patient_immatricule) VALUES" +
                " (?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1,dossier.getMatricule());
            ps.setString(2, dossier.getEtat().toString());
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
    public Dossier getDossier(int matricule) throws SQLException {
        String sql = "SELECT * FROM dossier WHERE matricule = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, matricule);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String etat, agentcnss_cin, patient_immatricule;

                etat = resultSet.getString("etat");
                agentcnss_cin = resultSet.getString("agentcnss_cin");
                patient_immatricule = resultSet.getString("patient_immatricule");

                AgentCNSSImp agentCNSSImp = new AgentCNSSImp();
                AgentCNSS agentCNSS = agentCNSSImp.getAgentCNSS(agentcnss_cin);

                PatientImp patientImp = new PatientImp();
                Patient patient = patientImp.getPtient(patient_immatricule);

                return new Dossier(matricule, Etat.valueOf(etat), agentCNSS, patient);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the radiologie: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Dossier> getDossiers() throws SQLException {
        return null;
    }


}