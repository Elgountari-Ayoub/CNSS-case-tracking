package ma.MaCNSS.DAO.Interfaces.Person;

import ma.MaCNSS.Entities.Personnes.AgentCNSS;

import java.sql.SQLException;
import java.util.List;

public interface AgentCNSSInterface {
    public boolean add(AgentCNSS agentCNSS)
            throws SQLException;

    public boolean update(AgentCNSS agentCNSS)
            throws SQLException;

    public boolean delete(String cin)
            throws SQLException;

    public AgentCNSS getAgentCNSS(String cin)
            throws SQLException;

    public List<AgentCNSS> getAgentCNSSs()
            throws SQLException;

    public AgentCNSS findByEmail (String  email)
            throws SQLException;


    public AgentCNSS register(AgentCNSS agentCNSS)
            throws SQLException;

    public AgentCNSS login(AgentCNSS agentCNSS)
            throws SQLException;
}
