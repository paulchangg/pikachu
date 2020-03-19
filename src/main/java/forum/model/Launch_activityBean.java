package forum.model;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import member.model.MemberBean;


@Entity
@Table(name = "launch_activity")
public class Launch_activityBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Article_Id;
	private String Article_m_id;
	private Integer f_id;
	private String article_title;
	private String article_content;
	
	private String subject;
	private String Location;
	private Date post_Time;
	
	private Date updateTime;
	private Date endTime;
	
	private Integer popularity;
	
	@OneToMany(mappedBy="Launch_activityBean", cascade=CascadeType.ALL)
	Set<ResponserBean> items = new LinkedHashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name = "FK_Launch_activityBean_Article_Id")
	FoumBean foumBean;
	
	@ManyToOne
	@JoinColumn(name = "FK_Launch_activityBean_Article_m_id")
	MemberBean memberBean;
	
	
	
	public Launch_activityBean() {

	}

	public Launch_activityBean(Integer article_Id, String article_m_id, Integer f_id, String article_title,
			String article_content, String subject, String location, Date post_Time, Date updateTime, Date endTime,
			Integer popularity) {
		Article_Id = article_Id;
		Article_m_id = article_m_id;
		this.f_id = f_id;
		this.article_title = article_title;
		this.article_content = article_content;
		this.subject = subject;
		Location = location;
		this.post_Time = post_Time;
		this.updateTime = updateTime;
		this.endTime = endTime;
		this.popularity = popularity;
	}

	public Integer getArticle_Id() {
		return Article_Id;
	}

	public void setArticle_Id(Integer article_Id) {
		Article_Id = article_Id;
	}

	public String getArticle_m_id() {
		return Article_m_id;
	}

	public void setArticle_m_id(String article_m_id) {
		Article_m_id = article_m_id;
	}

	public Integer getF_id() {
		return f_id;
	}

	public void setF_id(Integer f_id) {
		this.f_id = f_id;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public Date getPost_Time() {
		return post_Time;
	}

	public void setPost_Time(Date post_Time) {
		this.post_Time = post_Time;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	
	

}
