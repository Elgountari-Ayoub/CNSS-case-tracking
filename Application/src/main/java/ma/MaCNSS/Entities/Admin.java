package ma.MaCNSS.Entities;

public class Admin {
    private int id;
    private String email;
    private String pass;

    public Admin() {
    }

    public Admin(int id, String email, String pass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
    }

    public Admin(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
