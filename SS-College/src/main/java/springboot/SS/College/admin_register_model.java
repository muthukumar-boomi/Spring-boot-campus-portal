package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin_register")
public class admin_register_model {
    
    @Id
    @Column(name = "name")
    private String name;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "pass")
    private String pass;
    
    @Column(name = "dep")
    private String dep;
    
    // Default constructor
    public admin_register_model() {}
    
    // Parameterized constructor
    public admin_register_model(String name, String mobile, String pass, String dep) {
        this.name = name;
        this.mobile = mobile;
        this.pass = pass;
        this.dep = dep;
    }
    
    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
    
    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }
    
    public String getDep() { return dep; }
    public void setDep(String dep) { this.dep = dep; }
}