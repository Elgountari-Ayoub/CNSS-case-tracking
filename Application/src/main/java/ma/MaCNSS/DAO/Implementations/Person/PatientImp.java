package ma.MaCNSS.DAO.Implementations.Person;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Person.PatientInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.enums.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PatientImp implements PatientInterface {
    static Connection con = DBConnection.getConnection();

    @Override
    public boolean add(Patient patient) {

        String sql = "INSERT INTO Patient" +
        " (immatricule, cnss, nom, prenom, ville, telephone, email, password, genre) VALUES" +
        " (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, patient.getImmatricule());
            ps.setString(2, patient.getCIN());
            ps.setString(3, patient.getNom());
            ps.setString(4, patient.getPrenom());
            ps.setString(5, patient.getVille());
            ps.setString(6, patient.getTelephone());
            ps.setString(7, patient.getEmail());
            ps.setString(8, patient.getPassword());
            ps.setString(9, patient.getGenre().toString());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Patient: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Patient patient) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String immatricule) throws SQLException {
        return false;
    }

    @Override
    public Patient getPtient(int id) throws SQLException {

        String sql = "SELECT * FROM patient WHERE id = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String immatricule = resultSet.getString("immatricule");
                String cin = resultSet.getString("CIN");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String ville = resultSet.getString("ville");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Genre genre = Genre.FEMME;
                if (resultSet.getString("genre").equalsIgnoreCase(Genre.HOMME.name())){
                     genre = Genre.HOMME;
                };
                return new Patient(immatricule, cin, nom, prenom, ville, telephone, email, password, genre);
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the member: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Patient> getPatient() throws SQLException {
        return null;
    }

    @Override
    public AgentCNSS login(Patient patient) throws SQLException {
        return null;
    }


}