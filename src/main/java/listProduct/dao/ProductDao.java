package listProduct.dao;

import java.util.Map;

import listProduct.model.ProductBean;
import member.model.MemberBean;

public interface ProductDao {
	Map<Integer, ProductBean> getProduct(int pageNo);
	
	public ProductBean getSelectBook(int productId);
	
	public long getRecordCounts();
	
	public Map<Integer, ProductBean> getProductDescPrice(int pageNo);
	
	public Map<Integer, ProductBean> getProductAscPrice(int pageNo);
	
	
	
	public int getTotalPages();
}
