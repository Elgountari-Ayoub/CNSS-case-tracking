package ma.MaCNSS.DAO.Implementations.Organism;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Organism.LaboratoireInterface;
import ma.MaCNSS.Entities.Organisme.Laboratoire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LaboratoireImp implements LaboratoireInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Laboratoire laboratoire) {
        String sql = "INSERT INTO laboratoire" +
                " (INPE, address, label) VALUES" +
                " (?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, laboratoire.getINPE());
            ps.setString(2, laboratoire.getAdress());
            ps.setString(3, laboratoire.getLabel());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Laboratoire: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Laboratoire laboratoire) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Laboratoire getLaboratoire(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Laboratoire> getLaboratoires() throws SQLException {
        return null;
    }


}