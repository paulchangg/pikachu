package forum.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import member.model.MemberBean;

@Entity
@Table(name = "responser")
public class ResponserBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer res_id;
	private String res_m_id;
	private Integer Article_Id;
	private Date updateTime;
	private String res_content;

	@ManyToOne
	@JoinColumn(name = "FK_ResponserBean_Article_Id")
	Launch_activityBean launch_activityBean;

	@ManyToOne
	@JoinColumn(name = "FK_ResponserBean_res_im_id")
	MemberBean memberBean;

	public ResponserBean() {

	}

	public ResponserBean(Integer res_id, String res_im_id, Integer article_Id, Date updateTime, String res_content, String res_m_id) {
		super();
		this.res_id = res_id;
		this.res_m_id = res_m_id;
		Article_Id = article_Id;
		this.updateTime = updateTime;
		this.res_content = res_content;
	}

	public Integer getRes_id() {
		return res_id;
	}

	public void setRes_id(Integer res_id) {
		this.res_id = res_id;
	}

	public String getRes_m_id() {
		return res_m_id;
	}

	public void setRes_m_id(String res_m_id) {
		this.res_m_id = res_m_id;
	}

	public Integer getArticle_Id() {
		return Article_Id;
	}

	public void setArticle_Id(Integer article_Id) {
		Article_Id = article_Id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRes_content() {
		return res_content;
	}

	public void setRes_content(String res_content) {
		this.res_content = res_content;
	}

}
