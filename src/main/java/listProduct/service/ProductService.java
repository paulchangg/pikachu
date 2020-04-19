package listProduct.service;

import java.util.Map;

import listProduct.model.ProductBean;
import member.model.MemberBean;

public interface ProductService {

	Map<Integer, ProductBean> getProduct(int pageNo);
	
	public Map<Integer, ProductBean> getProductDescPrice(int pageNo);

	public Map<Integer, ProductBean> getProductAscPrice(int pageNo);
	
	public ProductBean getSelectBook(int productId);
	
	
	
	public long getRecordCounts();
	
	
	public int getTotalPages();
}

