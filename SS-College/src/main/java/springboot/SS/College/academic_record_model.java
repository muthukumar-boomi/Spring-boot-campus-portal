package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "academic_record")
public class academic_record_model {
    
    @Id
    private String id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "sem")
    private String sem;
    
    @Column(name = "dep")
    private String dep;
    
    @Column(name = "subject")
    private String subject;
    
    @Column(name = "imark")
    private String imark;
    
    @Column(name = "mark")
    private String mark;
    
    @Column(name = "total")
    private String total;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "grade")
    private String grade;
    
    // Constructors
    public academic_record_model() {}
    
    public academic_record_model(String id, String name, String sem, String dep, String subject,
            String imark, String mark, String total, String status, String grade) {
        this.id = id;
        this.name = name;
        this.sem = sem;
        this.dep = dep;
        this.subject = subject;
        this.imark = imark;
        this.mark = mark;
        this.total = total;
        this.status = status;
        this.grade = grade;
    }

    // Getters and Setters
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

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getImark() {
        return imark;
    }

    public void setImark(String imark) {
        this.imark = imark;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
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
}