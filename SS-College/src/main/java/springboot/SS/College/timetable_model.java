package springboot.SS.College;

import jakarta.persistence.*;

@Entity
@Table(name = "timetable")
public class timetable_model {

    @Id
  
    @Column(name = "course")
    private String course;

    @Column(name = "sem")
    private String sem;

    @Column(name = "section")
    private String section;

    @Column(name = "subject")
    private String subject;

    @Column(name = "staff")
    private String staff;

    @Column(name = "day")
    private String day;

    @Column(name = "stime")
    private String stime;

    @Column(name = "etime")
    private String etime;

    // Constructors
    public timetable_model() {}

    public timetable_model( String course, String sem, String section,
                           String subject, String staff, String day, String stime, String etime) {
    
        this.course = course;
        this.sem = sem;
        this.section = section;
        this.subject = subject;
        this.staff = staff;
        this.day = day;
        this.stime = stime;
        this.etime = etime;
    }

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
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

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStime() {
		return stime;
	}

	public void setStime(String stime) {
		this.stime = stime;
	}

	public String getEtime() {
		return etime;
	}

	public void setEtime(String etime) {
		this.etime = etime;
	}

    
}