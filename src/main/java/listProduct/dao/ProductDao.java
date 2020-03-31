package listProduct.dao;

import java.util.Map;

import listProduct.model.ProductBean;

public interface ProductDao {
	Map<Integer, ProductBean> getProduct(int pageNo);
	public ProductBean getSelectBook(int productId);
	public long getRecordCounts();
	public int getTotalPages();
}
