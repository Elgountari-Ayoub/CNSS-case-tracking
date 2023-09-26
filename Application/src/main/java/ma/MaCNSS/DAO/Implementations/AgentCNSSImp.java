package ma.MaCNSS.DAO.Implementations;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.AgentCNSSInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AgentCNSSImp implements AgentCNSSInterface {
    static Connection con = DBConnection.getConnection();

    @Override
    public boolean add(AgentCNSS agentCNSS) {

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

    @Override
    public AgentCNSS login(AgentCNSS agentCNSS) throws SQLException {
        return null;
    }

}