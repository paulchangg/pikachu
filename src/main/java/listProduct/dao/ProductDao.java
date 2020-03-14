package listProduct.dao;

import java.util.Map;

import listProduct.model.ProductBean;

public interface ProductDao {
	Map<Integer, ProductBean> getProduct();
	
}
