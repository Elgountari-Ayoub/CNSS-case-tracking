package ma.MaCNSS.Entities;

import ma.MaCNSS.Entities.Personnes.Employee;

import java.util.Date;


public class EmployeeCareer {
    private Employee employee;
    private Company company;
    private Date date;
    private float salary;
    private int workDays;
    private int workMonths;


    public EmployeeCareer( Company company, Employee employee, Date date, float salary, int workDays){
        this.employee = employee;
        this.company = company;
        this.date = date;
        this.salary = salary;
        this.workDays = workDays;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public int getWorkDays() {
        return workDays;
    }

    public void setWorkDays(int workDays) {
        this.workDays = workDays;
    }

    public int getWorkMonths() {
        return workMonths;
    }

    public void setWorkMonths(int workMonths) {
        this.workMonths = workMonths;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
