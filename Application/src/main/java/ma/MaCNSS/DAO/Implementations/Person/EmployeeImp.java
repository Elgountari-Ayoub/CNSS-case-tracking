package ma.MaCNSS.DAO.Implementations.Person;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Implementations.Documents.AnalyseImp;
import ma.MaCNSS.DAO.Interfaces.Person.EmployeeInterface;
import ma.MaCNSS.Entities.Documents.Analyse;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Employee;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EmployeeImp implements EmployeeInterface {
    static Connection con = DBConnection.getConnection();

    @Override
    public boolean add(Employee employee) {

        String sql = "INSERT INTO Employee" +
                " (immatricule, cin, nom, prenom, ville, telephone, email, password, genre, birthday, isPresent) VALUES" +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, employee.getImmatricule());
            ps.setString(2, employee.getCIN());
            ps.setString(3, employee.getNom());
            ps.setString(4, employee.getPrenom());
            ps.setString(5, employee.getVille());
            ps.setString(6, employee.getTelephone());
            ps.setString(7, employee.getEmail());
            ps.setString(8, employee.getPassword());
            ps.setString(9, employee.getGenre().toString());
            ps.setString(10, employee.getImmatricule());
            ps.setBoolean(11, employee.getPresent());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Employee: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        return false;
    }
    @Override
    public boolean delete(String immatricule) throws SQLException {
        return false;
    }

    @Override
    public Employee getEmployee(String immatricule) throws SQLException {
        String query = "SELECT * FROM employee WHERE immatricule = ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, immatricule);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Employee employee = new Employee();
                employee.setImmatricule(result.getString("immatricule"));
                employee.setCIN(result.getString("cin"));
                employee.setNom(result.getString("nom"));
                employee.setNom(result.getString("prenom"));
                employee.setEmail(result.getString("email"));
                employee.setPassword(result.getString("password"));
                employee.setTelephone(result.getString("telephone"));
                Genre genre =  Genre.valueOf(result.getString("genre"));
                employee.setGenre(genre);
                employee.setBirthdayDate(new Date((result.getDate("birthday").getTime())));
                employee.setPresent(result.getBoolean("isPresent"));
                return employee;
            } else {
                throw new SQLException("User not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getEmployees() throws SQLException {
        return null;
    }

    @Override
    public Employee findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM employee WHERE email = ? ";
        try {
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Employee employee = new Employee();
                employee.setImmatricule(result.getString("immatricule"));
                employee.setCIN(result.getString("cin"));
                employee.setNom(result.getString("nom"));
                employee.setNom(result.getString("prenom"));
                employee.setEmail(result.getString("email"));
                employee.setPassword(result.getString("password"));
                employee.setTelephone(result.getString("telephone"));
                Genre genre =  Genre.valueOf(result.getString("genre"));
                employee.setGenre(genre);
                employee.setBirthdayDate(new Date((result.getDate("birthday").getTime())));
                employee.setPresent(result.getBoolean("isPresent"));
                return employee;
            } else {
                throw new SQLException("User not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int updatePresence(String immatricule, boolean isPresent){

        String sql = "update employee set isPresent = ? where immatricule = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setBoolean(1, isPresent);
            ps.setString(2, immatricule);
            return ps.executeUpdate();
        }catch (SQLException ex){
            System.out.println(TextColor.yellowText(ex.getMessage()));
        }
        return 0;
    }

    @Override
    public Employee register(Employee emlpoyee) throws SQLException {
        return null;
    }

    @Override
    public Employee login(Employee employee) throws SQLException {
            return null;
    }


}