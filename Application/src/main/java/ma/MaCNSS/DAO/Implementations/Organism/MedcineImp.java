package ma.MaCNSS.DAO.Implementations.Organism;

import ma.MaCNSS.Connection.DBConnection;
import ma.MaCNSS.DAO.Interfaces.Organism.MedcineInterface;
import ma.MaCNSS.Entities.Organisme.Laboratoire;
import ma.MaCNSS.Entities.Organisme.Medcine;
import ma.MaCNSS.enums.Genre;
import ma.MaCNSS.enums.TypeMedcine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MedcineImp implements MedcineInterface {
    static Connection con = DBConnection.getConnection();
    @Override
    public boolean add(Medcine medcine) {
        String sql = "INSERT INTO medcine" +
                " (INPE, address, nom, prenom, type) VALUES" +
                " (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, medcine.getINPE());
            ps.setString(2, medcine.getAdress());
            ps.setString(3, medcine.getNom());
            ps.setString(4, medcine.getPrenom());
            ps.setString(5, medcine.getTypeMedcine().toString());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Medcine: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("An exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Medcine medcine) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public Medcine getMedcine(String inpe) throws SQLException  {
        String sql = "SELECT * FROM medcine WHERE inpe = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, inpe);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                String adress, nom, prenom, type;
                adress = resultSet.getString("address");
                nom = resultSet.getString("nom");
                prenom = resultSet.getString("prenom");
                type = resultSet.getString("type");

                TypeMedcine typeMedcine = TypeMedcine.GENERALISTE;
                if (type.equalsIgnoreCase(TypeMedcine.SPECIALISTE.toString())){
                    typeMedcine = TypeMedcine.SPECIALISTE;
                };
                return new Medcine(inpe, adress, nom, prenom, typeMedcine);
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

    public float getMedcinTauxByType(String type){
        String sql = "Select taux from medcineType where type = ?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, type);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                float taux = resultSet.getFloat("taux");
                return taux;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.err.println("Error getting the medcine taux: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return -1;
    }
    @Override
    public List<Medcine> getMedcines() throws SQLException {
        return null;
    }

}