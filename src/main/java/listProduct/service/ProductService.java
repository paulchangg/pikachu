package listProduct.service;

import java.util.Map;

import listProduct.model.ProductBean;

public interface ProductService {

	Map<Integer, ProductBean> getProduct(int pageNo);
	public ProductBean getSelectBook(int productId);
	public long getRecordCounts();
	public int getTotalPages();
}
