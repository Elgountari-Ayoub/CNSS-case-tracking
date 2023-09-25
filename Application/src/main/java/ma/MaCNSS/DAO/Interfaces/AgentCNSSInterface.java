package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Personnes.AgentCNSS;

import java.sql.SQLException;
import java.util.List;

public interface AgentCNSSInterface {
    public boolean add(AgentCNSS agentCNSS)
            throws SQLException;

    public boolean update(AgentCNSS agentCNSS)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public AgentCNSS getAgentCNSS(int id)
            throws SQLException;

    public List<AgentCNSS> getAgentCNSSs()
            throws SQLException;

    public AgentCNSS register(AgentCNSS agentCNSS)
            throws SQLException;
    public AgentCNSS login(AgentCNSS agentCNSS)
            throws SQLException;
}
