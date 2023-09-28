package ma.MaCNSS.DAO.Interfaces;

import ma.MaCNSS.Entities.Admin;
import ma.MaCNSS.Entities.Personnes.Patient;

import java.sql.SQLException;

public interface AdminInterface {
    public boolean add(Admin admin)
            throws SQLException;

    public boolean update(Admin admin)
            throws SQLException;

    public boolean delete(String email)
            throws SQLException;

    public Admin getAdmin(String email)
            throws SQLException;

    public Admin findByEmail(String email)
        throws  SQLException;
}
