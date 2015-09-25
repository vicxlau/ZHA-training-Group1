package cn.oocl.service.imple;

import java.util.List;

import cn.oocl.dao.imple.ProductDaoImpl;
import cn.oocl.model.Category;
import cn.oocl.model.Product;

public class ProductServiceImpl {

	//Service call Dao
	
	private ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	
	public int save(Product product){
		//business logic
		return productDaoImpl.save(product);
	}
	
	public int update(Product product){
		//business logic
		return productDaoImpl.update(product);
	}
	
	public int delete(int id){
		//business logic
		return productDaoImpl.delete(id);
	}
	
	public Product getById(int id){
		//business logic
		return productDaoImpl.getById(id);
	}
	
	public List<Product> query (String keyword, int page, int size){
		return productDaoImpl.query(keyword, page, size);
	}
	
	public List<Product> queryAll (){
		return productDaoImpl.query("", 1, 100);
	}
}
