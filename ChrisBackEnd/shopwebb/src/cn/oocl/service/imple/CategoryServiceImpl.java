package cn.oocl.service.imple;

import java.util.List;

import cn.oocl.dao.imple.CategoryDaoImpl;
import cn.oocl.model.Category;
import cn.oocl.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	//Service call Dao
	
	private CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.CategoryService#save(cn.oocl.model.Category)
	 */
	@Override
	public int save(Category category){
		//business logic
		return categoryDaoImpl.save(category);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.CategoryService#update(cn.oocl.model.Category)
	 */
	@Override
	public int update(Category category){
		//business logic
		return categoryDaoImpl.update(category);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.CategoryService#delete(int)
	 */
	@Override
	public int delete(int id){
		//business logic
		return categoryDaoImpl.delete(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.CategoryService#getById(int)
	 */
	@Override
	public Category getById(int id){
		//business logic
		return categoryDaoImpl.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.CategoryService#query(java.lang.String, int, int)
	 */
	@Override
	public List<Category> query (String keyword, int page, int size){
		return categoryDaoImpl.query(keyword, page, size);
	}
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.CategoryService#queryAll()
	 */
	@Override
	public List<Category> queryAll (){
		return categoryDaoImpl.query("", 1, 100);
	}

	public List<Category> queryByHot(int i) {
		// TODO Auto-generated method stub
		return categoryDaoImpl.queryByHot(i);
	}
}
