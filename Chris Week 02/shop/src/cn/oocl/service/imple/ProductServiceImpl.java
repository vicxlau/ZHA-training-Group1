package cn.oocl.service.imple;

import java.util.List;

import cn.oocl.dao.imple.ProductDaoImpl;
import cn.oocl.model.Category;
import cn.oocl.model.Product;
import cn.oocl.service.ProductService;

public class ProductServiceImpl implements ProductService {

	//Service call Dao
	
	private ProductDaoImpl productDaoImpl = new ProductDaoImpl();
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.ProductService#save(cn.oocl.model.Product)
	 */
	@Override
	public int save(Product product){
		//business logic
		return productDaoImpl.save(product);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.ProductService#update(cn.oocl.model.Product)
	 */
	@Override
	public int update(Product product){
		//business logic
		return productDaoImpl.update(product);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.ProductService#delete(int)
	 */
	@Override
	public int delete(int id){
		//business logic
		return productDaoImpl.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.ProductService#getById(int)
	 */
	@Override
	public Product getById(int id){
		//business logic
		return productDaoImpl.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.ProductService#query(java.lang.String, int, int)
	 */
	@Override
	public List<Product> query (String keyword, int page, int size){
		return productDaoImpl.query(keyword, page, size);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.ProductService#queryAll()
	 */
	@Override
	public List<Product> queryAll (){
		return productDaoImpl.query("", 1, 100);
	}
}
