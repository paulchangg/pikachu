package member.model;

import java.sql.Blob;
import java.sql.Date;

public class memberBean {
	Integer memberId;
	String name;
	String phone;
	String mail;
	String nickName;
	Date birthday;
	String gender;
	Blob image;
	Integer income;
	String city;
	String education;
	
	public memberBean() {
		super();
	}

	public memberBean(Integer memberId, String name, String phone, String mail, String nickName, Date birthday,
			String gender, Blob image, Integer income, String city, String education) {
		super();
		this.memberId = memberId;
		this.name = name;
		this.phone = phone;
		this.mail = mail;
		this.nickName = nickName;
		this.birthday = birthday;
		this.gender = gender;
		this.image = image;
		this.income = income;
		this.city = city;
		this.education = education;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Integer getIncome() {
		return income;
	}

	public void setIncome(Integer income) {
		this.income = income;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	
	
	

}
