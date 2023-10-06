package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Person.EmployeeInterface;
import ma.MaCNSS.Entities.EmployeeCareer;
import ma.MaCNSS.Entities.Personnes.Employee;
import ma.MaCNSS.Helpers.TextColor;
import ma.MaCNSS.enums.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class EmployeeCareerImp  {
    static Connection con = DBConnection.getConnection();

    public boolean add(EmployeeCareer employeeCareer) {

        String sql = "INSERT INTO EmployeeCareer" +
                " (matricule, immatricule, date, salary, workDays) VALUES" +
                " (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, employeeCareer.getCompany().getMatricule());
            ps.setString(2, employeeCareer.getEmployee().getImmatricule());
            ps.setDate(3, new java.sql.Date(employeeCareer.getDate().getTime()));
            ps.setFloat(4, employeeCareer.getSalary());
            ps.setInt(5, employeeCareer.getWorkDays());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Employee Career row: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Employee employee) throws SQLException {
        return false;
    }

    public boolean delete(String immatricule) throws SQLException {
        return false;
    }


    public Employee getEmployee(String immatricule) throws SQLException {
        String sql = "SELECT * FROM employee WHERE immatricule = ?";
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
                Date birthday = resultSet.getDate("birthday");
                boolean isPresent = resultSet.getBoolean("isPresent");

                Genre genre = Genre.FEMME;
                if (resultSet.getString("genre").equalsIgnoreCase(Genre.HOMME.name())) {
                    genre = Genre.HOMME;
                }

                return new Employee(immatricule, cin, nom, prenom, ville, telephone, email, password, genre, birthday, isPresent );
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

    public List<Employee> getEmployees() throws SQLException {
        return null;
    }

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
                return employee;
            } else {
                throw new SQLException("User not found");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Employee register(Employee emlpoyee) throws SQLException {
        return null;
    }

    public Employee login(Employee employee) throws SQLException {
            return null;
    }


    public int getWorkDays(String immatricule){
        int workDays = 0;
        String sql = "Select sum(workdays) as \"workDays\"from employeeCareer where immatricule = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, immatricule);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()){
                workDays = resultSet.getInt("workDays");
            }

        }catch (SQLException ex){
            System.out.println(TextColor.yellowText(ex.getMessage()));
        }
        return workDays;
    }
    public float getLast96MonthsSalaryAvg(String immatricule){
        float salary = 0, salaryAvg = 0;

        String sql = "\n" +
                "Select salary from employeeCareer where immatricule = ? order by salary desc limit 96";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, immatricule);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                salary += resultSet.getFloat("salary");
            }
            salaryAvg = salary / 96;

        }catch (SQLException ex){
            System.out.println(TextColor.yellowText(ex.getMessage()));
        }
        return salaryAvg;
    }



}