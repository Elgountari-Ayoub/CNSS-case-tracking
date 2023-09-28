package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Implementations.Person.AgentCNSSImp;
import ma.MaCNSS.DAO.Implementations.Person.PatientImp;
import ma.MaCNSS.DAO.Interfaces.AdminInterface;
import ma.MaCNSS.DAO.Interfaces.DossierInterface;
import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Dossier;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.enums.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminImp implements AdminInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Admin admin) {
        String sql = "INSERT INTO admin" +
                " (email, pass) VALUES" +
                " (?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(2, admin.getEmail());
            ps.setString(3, admin.getPass());

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
    public boolean update(Admin admin) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String email) throws SQLException {
        return false;
    }

    @Override
    public Admin getAdmin(String email) throws SQLException {
        String sql = "SELECT * FROM admin WHERE email = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String pass;
                int id;

                id = resultSet.getInt("id");
                pass = resultSet.getString("pass");

                return new Admin(id, email, pass);
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
    public Admin findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM admin WHERE email = ? ";
        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,email);
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                Admin admin = new Admin();
                admin.setEmail(result.getString("email"));
                admin.setPass(result.getString("pass"));
                admin.setId(result.getInt("id"));
                return admin ;
            }else{
                System.out.println("Admin not found");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }



}