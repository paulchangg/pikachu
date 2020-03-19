package _04_ShoppingCart.model;

import java.sql.Blob;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

//本類別封裝單筆訂單資料
@Entity
@Table(name = "orderitem")
public class OrderItemBean {
	@Id
	private Integer itemid;
	private Integer ordid;
	private Integer p_id;
	private Double price;
	private Integer qty;
	private Double itemtotal;
	@ManyToOne
	@JoinColumn(name = "FK_OrderBean_orderNo")
	OrdersBean orderBean;
	@Transient
	private String p_name;
	
	
	
	
	
	public OrderItemBean(Integer itemid, Integer ordid, Integer p_id, Double price, Integer qty, Double itemtotal,String p_name) {
		super();
		this.itemid = itemid;
		this.ordid = ordid;
		this.p_id = p_id;
		this.price = price;
		this.qty = qty;
		this.itemtotal = itemtotal;
		
		this.p_name = p_name;
	}





	public String getP_name() {
		return p_name;
	}



	public void setP_name(String p_name) {
		this.p_name = p_name;
	}



	public Integer getItemid() {
		return itemid;
	}
	public void setItemid(Integer itemid) {
		this.itemid = itemid;
	}
	public Integer getOrdid() {
		return ordid;
	}
	public void setOrdid(Integer ordid) {
		this.ordid = ordid;
	}
	public Integer getP_id() {
		return p_id;
	}
	public void setP_id(Integer p_id) {
		this.p_id = p_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Double getItemtotal() {
		return itemtotal;
	}
	public void setItemtotal(Double itemtotal) {
		this.itemtotal = itemtotal;
	}
	
	
	
}