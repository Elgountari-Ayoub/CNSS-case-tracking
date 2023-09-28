package ma.MaCNSS.DAO.Implementations.Person;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Person.AgentCNSSInterface;
import ma.MaCNSS.Entities.Personnes.AgentCNSS;
import ma.MaCNSS.Entities.Personnes.Patient;
import ma.MaCNSS.enums.Genre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AgentCNSSImp implements AgentCNSSInterface {
    static Connection con = DBConnection.getConnection();

    @Override
    public boolean add(AgentCNSS agentCNSS) {
        String sql = "INSERT INTO AgentCNSS" +
                " (cin, nom, prenom, ville, telephone, email, password, genre) VALUES" +
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
    public boolean delete(String cin) throws SQLException {
        return false;
    }

    @Override
    public AgentCNSS getAgentCNSS(String cin) throws SQLException {
        String sql = "SELECT * FROM patient WHERE cin = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, cin);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
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
                return new AgentCNSS(cin, nom, prenom, ville, telephone, email, password, genre);
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
    public List<AgentCNSS> getAgentCNSSs() throws SQLException {
        return null;
    }

    @Override
    public AgentCNSS findByEmail(String email) throws SQLException {
        String query = "SELECT * FROM agentCNSS WHERE email = ? ";
        try{
            PreparedStatement stmt = con.prepareStatement(query);
            stmt.setString(1,email);
            ResultSet result = stmt.executeQuery();
            if(result.next()){
                AgentCNSS agent = new AgentCNSS();
                agent.setCIN(result.getString("cin"));
                agent.setNom(result.getString("nom"));
                agent.setNom(result.getString("prenom"));
                agent.setPassword(result.getString("password"));
                agent.setEmail(result.getString("email"));
                agent.setTelephone(result.getString("telephone"));
                Genre genre =  Genre.valueOf(result.getString("genre"));
                agent.setGenre(genre);
                return agent ;
            }else{
                System.out.println("Agent not found");
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
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