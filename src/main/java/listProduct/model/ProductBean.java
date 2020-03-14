package listProduct.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class ProductBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer p_id;
	private String p_name;
	private String p_desc;
	private Double price;
	private Integer stock;
	private Blob p_img;
	private Integer p_bns;
	
	
	
	public ProductBean(Integer p_id, String p_name, String p_desc, Double price, Integer stock, Blob p_img,
			Integer p_bns) {
		super();
		this.p_id = p_id;
		this.p_name = p_name;
		this.p_desc = p_desc;
		this.price = price;
		this.stock = stock;
		this.p_img = p_img;
		this.p_bns = p_bns;
	}


	public ProductBean() {
		
	}


	public Integer getP_id() {
		return p_id;
	}


	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}


	public String getP_name() {
		return p_name;
	}


	public void setP_name(String p_name) {
		this.p_name = p_name;
	}


	public String getP_desc() {
		return p_desc;
	}


	public void setP_desc(String p_desc) {
		this.p_desc = p_desc;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public Integer getStock() {
		return stock;
	}


	public void setStock(Integer stock) {
		this.stock = stock;
	}


	public Blob getP_img() {
		return p_img;
	}


	public void setP_img(Blob p_img) {
		this.p_img = p_img;
	}


	public Integer getP_bns() {
		return p_bns;
	}


	public void setP_bns(Integer p_bns) {
		this.p_bns = p_bns;
	}

	

}
