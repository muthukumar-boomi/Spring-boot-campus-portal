package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admission")
public class admission_model {
    
    @Id
    private String id;
    
    @Column(name = "date")
    private String date;
    
    @Column(name = "atype")
    private String atype;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "lname")
    private String lname;
    
    @Column(name = "dob")
    private String dob;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "blood")
    private String blood;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "course")
    private String course;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "dep")
    private String dep;
    
    @Column(name = "ayear")
    private String ayear;
    
    @Column(name = "pscollege")
    private String pcollege;
    
    
    @Column(name = "accommodation")
    private String accommodation;
    
    
    @Column(name = "sem")
    private String sem;
    
    @Column(name = "rnumber")
    private String rnumber;
    
    @Column(name = "section")
    private String section;
    
    @Column(name = "fname")
    private String fname;
    
    @Column(name = "mname")
    private String mname;
    
    @Column(name = "ph")
    private String ph;
    
    @Column(name = "work")
    private String work;
    
    // Constructors
    public admission_model() {}
    
    public admission_model(String id, String date, String atype, String name, String lname,
            String dob, String gender, String blood, String email, String mobile, String course, 
            String address, String dep, String ayear, String sem, String rnumber, String section, 
            String fname, String mname, String ph,String accommodation,String pcollege, String work) {
        this.id = id;
        this.date = date;
        this.atype = atype;
        this.name = name;
        this.lname = lname;
        this.dob = dob;
        this.gender = gender;
        this.blood = blood;
        this.email = email;
        this.mobile = mobile;
        this.course = course;
        this.address = address;
        this.dep = dep;
        this.ayear = ayear;
        this.sem = sem;
        this.rnumber = rnumber;
        this.section = section;
        this.pcollege = pcollege;
        this.accommodation = accommodation;
        this.fname = fname;
        this.mname = mname;
        this.ph = ph;
        this.work = work;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAtype() {
		return atype;
	}

	public void setAtype(String atype) {
		this.atype = atype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getAyear() {
		return ayear;
	}

	public void setAyear(String ayear) {
		this.ayear = ayear;
	}

	public String getPcollege() {
		return pcollege;
	}

	public void setPcollege(String pcollege) {
		this.pcollege = pcollege;
	}

	public String getAccommodation() {
		return accommodation;
	}

	public void setAccommodation(String accommodation) {
		this.accommodation = accommodation;
	}

	public String getSem() {
		return sem;
	}

	public void setSem(String sem) {
		this.sem = sem;
	}

	public String getRnumber() {
		return rnumber;
	}

	public void setRnumber(String rnumber) {
		this.rnumber = rnumber;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

    
    
}
