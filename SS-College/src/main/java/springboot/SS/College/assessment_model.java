package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "assessment")
public class assessment_model {
    
    @Id
  
    
    @Column(name = "rnumber")
    private String rnumber;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "atype")
    private String atype;
    
    @Column(name = "course")
    private String course;
    
    @Column(name = "subject")
    private String subject;
    
    @Column(name = "sem")
    private String sem;
    
    @Column(name = "section")
    private String section;
    
    @Column(name = "maxmark")
    private String maxmark;
    
    @Column(name = "mobtined")
    private String mobtined;
    
    @Column(name = "ddate")
    private String ddate;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "grade")
    private String grade;
    
    // Constructors
    public assessment_model() {}
    
    public assessment_model( String rnumber, String name, String atype, String course,
                          String subject, String sem, String section, String maxmark, String mobtined,
                          String ddate, String status, String grade) {
     
        this.rnumber = rnumber;
        this.name = name;
        this.atype = atype;
        this.course = course;
        this.subject = subject;
        this.sem = sem;
        this.section = section;
        this.maxmark = maxmark;
        this.mobtined = mobtined;
        this.ddate = ddate;
        this.status = status;
        this.grade = grade;
    }

	public String getRnumber() {
		return rnumber;
	}

	public void setRnumber(String rnumber) {
		this.rnumber = rnumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getMaxmark() {
		return maxmark;
	}

	public void setMaxmark(String maxmark) {
		this.maxmark = maxmark;
	}

	public String getMobtined() {
		return mobtined;
	}

	public void setMobtined(String mobtined) {
		this.mobtined = mobtined;
	}

	public String getDdate() {
		return ddate;
	}

	public void setDdate(String ddate) {
		this.ddate = ddate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
    
    // Getters and Setters
    
   
   
}