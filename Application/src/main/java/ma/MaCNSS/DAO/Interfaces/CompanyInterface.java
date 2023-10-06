package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Company;
import ma.MaCNSS.Entities.Dossier;

import java.sql.SQLException;
import java.util.List;

public interface CompanyInterface {
    public int add(Company company)
            throws SQLException;

    public int update(Company company)
            throws SQLException;

    public int delete(String matricule)
            throws SQLException;

    public Company getCompany(String matricule)
            throws SQLException;

    public List<Company> getCompany()
            throws SQLException;

    public Company findByEmail(String email)
            throws SQLException;

}
