package forum.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "forum")
public class FoumBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer f_id;
	private String fname;
	private Date startdate_datetime ;
	private Integer owner_m_id;
	
	@OneToMany(mappedBy="FoumBean", cascade=CascadeType.ALL)
	Set<Launch_activityBean> items = new LinkedHashSet<>();
	
	
	
	public FoumBean(Integer f_id, String fname, Date startdate_datetime, Integer owner_m_id) {
		super();
		this.f_id = f_id;
		this.fname = fname;
		this.startdate_datetime = startdate_datetime;
		this.owner_m_id = owner_m_id;
	}

	public FoumBean() {
		super();
	}

	public Integer getF_id() {
		return f_id;
	}

	public void setF_id(Integer f_id) {
		this.f_id = f_id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public Date getStartdate_datetime() {
		return startdate_datetime;
	}

	public void setStartdate_datetime(Date startdate_datetime) {
		this.startdate_datetime = startdate_datetime;
	}

	public Integer getOwner_m_id() {
		return owner_m_id;
	}

	public void setOwner_m_id(Integer owner_m_id) {
		this.owner_m_id = owner_m_id;
	}
	
	
	
	

}
