package springboot.SS.College;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fees")
public class fees_model {
   
    @Id
    private String id;
    
    @Column(name = "name")
    private String name;

    @Column(name = "ftype")
    private String ftype;
    
    @Column(name = "ddate")
    private String ddate;
    
    @Column(name = "rs")
    private String rs;
  
    @Column(name = "pdate")
    private String pdate;
    
    @Column(name = "pmode")
    private String pmode;
    
    @Column(name = "pamount")
    private String pamount;  // Make sure this is String
    
    @Column(name = "status")
    private String status;
   
    // Constructors, getters, and setters...
 public fees_model() {}
    
    public fees_model(String id, String name, String ftype, String ddate, String rs, 
                      String pdate, String pmode, String pamount, String status) {
        this.id = id;
        this.name = name;
        this.ftype = ftype;
        this.ddate = ddate;
        this.rs = rs;
        this.pdate = pdate;
        this.pmode = pmode;
        this.pamount = pamount;  // Now this matches String type
        this.status = status;
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

	public String getFtype() {
		return ftype;
	}

	public void setFtype(String ftype) {
		this.ftype = ftype;
	}

	public String getDdate() {
		return ddate;
	}

	public void setDdate(String ddate) {
		this.ddate = ddate;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public String getPmode() {
		return pmode;
	}

	public void setPmode(String pmode) {
		this.pmode = pmode;
	}

	public String getPamount() {
		return pamount;
	}

	public void setPamount(String pamount) {
		this.pamount = pamount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
}