package ma.MaCNSS.DAO.Interfaces.Medicament;
import ma.MaCNSS.Entities.Medicament.Categorie;

import java.sql.SQLException;
import java.util.List;

public interface CategorieInterface {
    public boolean add(Categorie categorie)
            throws SQLException;

    public boolean update(Categorie categorie)
            throws SQLException;

    public boolean delete(int id)
            throws SQLException;

    public Categorie  getCategorie (int id)
            throws SQLException;

    public List<Categorie> getCategories()
            throws SQLException;

}
