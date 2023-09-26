package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;

import java.sql.SQLException;
import java.util.List;

public interface PatientInterface {
    public boolean add(Patient patient)
            throws SQLException;

    public boolean update(Patient patient)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Patient getPtient(int id)
            throws SQLException;

    public List<Patient> getPatient()
            throws SQLException;

    public AgentCNSS login(Patient patient)
            throws SQLException;
}
