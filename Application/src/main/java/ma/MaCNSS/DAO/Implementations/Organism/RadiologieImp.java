package ma.MaCNSS.DAO.Implementations.Organism;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Organism.LaboratoireInterface;
import ma.MaCNSS.DAO.Interfaces.Organism.RadiologieInterface;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Radiologie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RadiologieImp implements RadiologieInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Radiologie radiologie) {
        String sql = "INSERT INTO laboratoire" +
                " (INPE, address, label) VALUES" +
                " (?, ?, ?) ";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, radiologie.getINPE());
            ps.setString(2, radiologie.getAdress());
            ps.setString(3, radiologie.getLabel());

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
    public boolean update(Radiologie radiologie) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String inpe) throws SQLException {
        return false;
    }

    @Override
    public Laboratoire getRadiologie(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Radiologie> getRadiologies() throws SQLException {
        return null;
    }

}