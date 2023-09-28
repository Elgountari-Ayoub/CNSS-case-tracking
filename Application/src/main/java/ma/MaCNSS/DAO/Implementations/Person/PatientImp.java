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
                " (immatricule, cin, nom, prenom, ville, telephone, email, password, genre) VALUES" +
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
    public Patient getPtient(String immatricule) throws SQLException {
        String sql = "SELECT * FROM patient WHERE immatricule = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, immatricule);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String cin = resultSet.getString("cin");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String ville = resultSet.getString("ville");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Genre genre = Genre.FEMME;
                if (resultSet.getString("genre").equalsIgnoreCase(Genre.HOMME.name())) {
                    genre = Genre.HOMME;
                }
                ;
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
    public Patient findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM patient WHERE email = ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Patient patient = new Patient();
                patient.setImmatricule(result.getString("immatricule"));
                patient.setCIN(result.getString("cin"));
                patient.setNom(result.getString("nom"));
                patient.setNom(result.getString("prenom"));
                patient.setEmail(result.getString("email"));
                patient.setPassword(result.getString("password"));
                patient.setTelephone(result.getString("telephone"));
                Genre genre =  Genre.valueOf(result.getString("genre"));
                patient.setGenre(genre);
                return patient;
            } else {
                throw new SQLException("User not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Patient getPtientDossiers(String immatricule) throws SQLException {
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