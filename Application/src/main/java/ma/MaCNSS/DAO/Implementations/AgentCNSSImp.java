package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.AgentCNSSInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ma.MaCNSS.enums.Genre;
import org.mindrot.jbcrypt.BCrypt;

public class AgentCNSSImp implements AgentCNSSInterface {
    static Connection con = DBConnection.getConnection();

    @Override
    public boolean add(AgentCNSS agentCNSS) {

        /*
        *
        * cnss SERIAL PRIMARY KEY,
    nom VARCHAR(255),
    prenom VARCHAR(255),
    ville VARCHAR(255),
    telephone VARCHAR(15),
    email VARCHAR(255),
    password VARCHAR(255),
    genre VARCHAR(10)*/
        String sql = "INSERT INTO AgentCNSS" +
                " (cnss, nom, prenom, ville, telephone, email, password, genre) VALUES" +
                " (?, ?, ?, ?, ?, ?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, agentCNSS.getCIN());
            ps.setString(2, agentCNSS.getNom());
            ps.setString(3, agentCNSS.getPrenom());
            ps.setString(4, agentCNSS.getVille());
            ps.setString(5, agentCNSS.getTelephone());
            ps.setString(6, agentCNSS.getEmail());
            ps.setString(7, agentCNSS.getPassword());
            ps.setString(8, agentCNSS.getGenre().toString());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting AgentCNSS: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(AgentCNSS agentCNSS) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public AgentCNSS getAgentCNSS(int id) throws SQLException {
        return null;
    }

    @Override
    public List<AgentCNSS> getAgentCNSSs() throws SQLException {
        return null;
    }

    @Override
    public AgentCNSS register(AgentCNSS agentCNSS) throws SQLException {

        return null;
    }
    /*
            *
            * cnss SERIAL PRIMARY KEY,
        nom VARCHAR(255),
        prenom VARCHAR(255),
        ville VARCHAR(255),
        telephone VARCHAR(15),
        email VARCHAR(255),
        password VARCHAR(255),
        genre VARCHAR(10)*/
    @Override
    public AgentCNSS findByEmail(AgentCNSS agentCNSS) throws SQLException {
        String query = "SELECT * FROM agentCNSS WHERE email = ? ";
        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,agentCNSS.getEmail());
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                    AgentCNSS agent = new AgentCNSS();
                    agent.setCIN(result.getString("cnss"));
                    agent.setNom(result.getString("nom"));
                    agent.setNom(result.getString("prenom"));
                    agent.setPassword(result.getString("password"));
                    agent.setEmail(result.getString("email"));
                    agent.setTelephone(result.getString("telephone"));
                    if (result.getString("genre").equals(Genre.HOMME.name()) ) {
                        agent.setGenre(Genre.HOMME);
                    }else {
                        agent.setGenre(Genre.FEMME);
                    }
                    return agent ;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}