package ma.MaCNSS.Entities;

public class Company {
    private String matricule;
    private String name;
    private  String email;
    private String password;

    public Company(String matricule, String name, String email, String password){
        this.matricule = matricule;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Company(){

    }

    public String getMatricule(){
        return this.matricule;
    }

    public void setMatricule(String matricule){
        this.matricule = matricule;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
