package forum.service;

import java.util.Map;

import listProduct.model.ProductBean;

public interface FoumService {

	Map<Integer, ProductBean> getProduct(int pageNo);
	public ProductBean getSelectBook(int productId);
	public long getRecordCounts();
	public int getTotalPages();
}
