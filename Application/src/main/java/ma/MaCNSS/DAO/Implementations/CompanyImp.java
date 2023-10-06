package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.CompanyInterface;
import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Company;
import ma.MaCNSS.Entities.Dossier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyImp implements CompanyInterface {
    static Connection con = DBConnection.getConnection();

    @Override
    public int add(Company company) {
        String sql = "INSERT INTO company" +
                " (matricule, name, email, password) VALUES" +
                " (?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1,company.getMatricule());
            ps.setString(2, company.getName());
            ps.setString(3, company.getEmail());
            ps.setString(4, company.getPassword());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting Company: " + e.getMessage());
            return 0;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Company company) throws SQLException {
        return 0;
    }

    @Override
    public int delete(String matricule) throws SQLException {
        return 0;
    }

    @Override
    public Company getCompany(String matricule) throws SQLException {
        return null;
    }

    @Override
    public List<Company> getCompany() throws SQLException {
        return null;
    }
    public Company findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM company WHERE email = ? ";
        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,email);
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                Company company = new Company();
                company.setMatricule(result.getString("matricule"));
                company.setName(result.getString("name"));
                company.setEmail(result.getString("email"));
                company.setPassword(result.getString("password"));
                return company ;
            }else{
                System.out.println("Company not found");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

}
