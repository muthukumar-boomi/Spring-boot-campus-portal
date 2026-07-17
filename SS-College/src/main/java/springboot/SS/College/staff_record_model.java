package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "staff_record")
public class staff_record_model {
    
    @Id
    private String id;
    
    @Column(name = "fname")
    private String fname;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "designation")
    private String designation;
    

    @Column(name = "subject")
    private String subject;
    
    @Column(name = "dep")
    private String dep;
    
    @Column(name = "exp")
    private String exp;
    
    // Constructors
    public staff_record_model() {}
    
    public staff_record_model(String id, String fname, String name, String gender, String email, 
                             String mobile,String designation, String  subject, String dep, String exp) {
        this.id = id;
        this.fname = fname;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.mobile = mobile;
        this.designation = designation;
        this.subject = subject;
        this.dep = dep;
        this.exp = exp;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

 
}