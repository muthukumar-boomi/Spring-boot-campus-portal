package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "exam_result")
public class exam_result_model {
    
    @Id
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "etype")
    private String etype;

    @Column(name = "subject")
    private String subject;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "time")
    private String time;
    
    @Column(name = "section")
    private String section;
    
    @Column(name = "tmark")
    private String tmark;
    
    @Column(name = "mark")
    private String mark;
    
    @Column(name = "grade")
    private String  grade;
    
    @Column(name = "status")
    private String  status;
    


    // Constructors
    public exam_result_model() {}
    
    public exam_result_model(String id, String name ,String etype, String subject,String date, String time, String section, String tmark, String mark,String grade,
    		String status) {
    	
        this.id = id;
        this.name= name;
        this.etype = etype;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.section = section;
        this.tmark= tmark;
        this.mark = mark;
        this.grade = grade;
    
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEtype() {
		return etype;
	}

	public void setEtype(String etype) {
		this.etype = etype;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getTmark() {
		return tmark;
	}

	public void setTmark(String tmark) {
		this.tmark = tmark;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
    
}