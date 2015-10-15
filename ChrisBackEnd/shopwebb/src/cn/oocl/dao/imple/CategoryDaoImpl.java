package cn.oocl.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.oocl.model.Category;
import cn.oocl.model.Product;
import cn.oocl.util.JDBCUtil;

/*
 * 1: 相同的DB操作都需要声明在接口中
 * 2: 相同的代码实现,都应该抽取到父类中
 * */

public class CategoryDaoImpl extends BaseDaoImpl<Category> {
	
	@Override
	protected Category getRowMapper(ResultSet rs) throws Exception {
		Category category = new Category();
		// table's row -> category object
		category.setId(rs.getInt("cat_id"));
		category.setType(rs.getNString("cat_type"));
		category.setHot(rs.getBoolean("cat_hot"));
		return category;
	}
		
	public int save(Category category){
		String sql="insert into category (cat_type,cat_hot) values (?,?)";
		Object[] param=new Object[]{category.getType(),category.getHot()};
		// 统一调用父类的操作数据的方法
		return super.executeUpdate(sql, param);
	}
	

	public int update(Category category){
		String sql="update category set cat_type=?,cat_hot=? where cat_id=?";
		Object[] param=new Object[]{category.getType(),category.getHot(), category.getId()};
		// 统一调用父类的操作数据的方法
		return super.executeUpdate(sql, param);
	}
	
	public int delete(int id){
		String sql="delete from category where cat_id=?";
		Object[] param=new Object[]{id};
		return super.executeUpdate(sql, param);
	}
	
	public Category getById(int id) {
		String sql = "select * from category where cat_id = ?";
		return (Category)super.get(sql, id);
		
	}
	
	// implement product 分頁  page = 2 size = 3
	public List<Category> query(String keyword, int page, int size){
			// test in oracle first, if no problem then paste here.
			String sql = "select * from (select t.*, rownum r from "
						+ "(select * from category c where c.cat_type like ? order by c.cat_id) t where rownum <=?)"
						+ " c where c.r > ?";
			Object[] param=new Object[]{"%"+keyword+"%", page*size, (page-1)*size};
			//真分頁顯示紀錄
			return super.query(sql, param);
	}
	
	public List<Category> queryByHot(int hot) {
		String sql = "select * from category c where c.cat_hot = ?";
		Object[] param = new Object[] { hot };
		return (List<Category>) super.query(sql, param);
	}
}
