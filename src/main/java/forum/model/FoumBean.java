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
	private String fname;//版名
	private Date startdate_datetime;//開版日期
	private Date update_datetime; // 更新版的日期 資料庫要新增欄位
	private String owner_m_id;//版主會員

	@OneToMany(mappedBy = "foumBean", cascade = CascadeType.ALL)
	Set<Launch_activityBean> items = new LinkedHashSet<>();

	public FoumBean() {
		super();
	}

	public FoumBean(Integer f_id, String fname, Date startdate_datetime, Date update_datetime, String owner_m_id,
			Set<Launch_activityBean> items) {
		super();
		this.f_id = f_id;
		this.fname = fname;
		this.startdate_datetime = startdate_datetime;
		this.update_datetime = update_datetime;
		this.owner_m_id = owner_m_id;
		this.items = items;
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

	public Date getUpdate_datetime() {
		return update_datetime;
	}

	public void setUpdate_datetime(Date update_datetime) {
		this.update_datetime = update_datetime;
	}

	public String getOwner_m_id() {
		return owner_m_id;
	}

	public void setOwner_m_id(String owner_m_id) {
		this.owner_m_id = owner_m_id;
	}

	public Set<Launch_activityBean> getItems() {
		return items;
	}

	public void setItems(Set<Launch_activityBean> items) {
		this.items = items;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FoumBean [f_id=");
		builder.append(f_id);
		builder.append(", fname=");
		builder.append(fname);
		builder.append(", startdate_datetime=");
		builder.append(startdate_datetime);
		builder.append(", update_datetime=");
		builder.append(update_datetime);
		builder.append(", owner_m_id=");
		builder.append(owner_m_id);
		builder.append(", items=");
		builder.append(items);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
