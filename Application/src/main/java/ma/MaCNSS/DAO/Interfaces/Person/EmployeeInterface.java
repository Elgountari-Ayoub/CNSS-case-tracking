package ma.MaCNSS.DAO.Interfaces.Person;

import ma.MaCNSS.Entities.Personnes.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeInterface {
    public boolean add(Employee emlpoyee)
            throws SQLException;

    public boolean update(Employee emlpoyee)
            throws SQLException;

    public boolean delete(String cin)
            throws SQLException;

    public Employee getEmployee(String cin)
            throws SQLException;

    public List<Employee> getEmployees()
            throws SQLException;

    public Employee findByEmail (String  email)
            throws SQLException;


    public Employee register(Employee employee)
            throws SQLException;

    public Employee login(Employee employee)
            throws SQLException;
}
