package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class attendance_model {
    
    @Id
    
    
    @Column(name = "rnumber")
    private String rnumber;
    
    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private String date;
    

    @Column(name = "gender")
    private String gender;
    
    @Column(name = "course")
    private String course;
    
    @Column(name = "section")
    private String section;
    
    @Column(name = "status")
    private String status;

    // Constructors
    public attendance_model() {}
    
    public attendance_model(String rnumber, String name,String date,  String gender, String course, String section, String status) {
    	 this.rnumber = rnumber;
        this.name = name;
        this.date = date;
        this.gender = gender;
        this.course = course;
        this.section = section;
        this.status= status;
        
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


    
}