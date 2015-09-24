package cn.oocl.dao.imple;

import java.sql.ResultSet;
import java.util.*;

import cn.oocl.model.Category;

/*
 * 1: 相同的DB操作都需要声明在接口中
 * 2: 相同的代码实现,都应该抽取到父类中
 * */
public class CategoryDaoImpl extends BaseDaoImpl<Category> {

	@Override
	protected Category getRowMapper(ResultSet rs) throws Exception {
		Category obj = new Category(rs.getInt("cat_id"),
				rs.getString("cat_type"), rs.getBoolean("cat_hot"));
		return obj;
	}

	// //work
	// public Category get(int id) {
	// String sql = "SELECT * FROM category WHERE cat_id = ?";
	// Connection conn = null;
	// PreparedStatement stat = null;
	// ResultSet rs = null;
	// ConnUtil util = new ConnUtil();
	// conn = util.getConnection();
	// // 2: 获取的是操作mysql prepareStatement对象
	// try {
	// stat = conn.prepareStatement(sql);
	// // 根据传入的数据,对sql语句进行赋值
	// stat.setInt(1, id);
	// rs = stat.executeQuery();
	// Category rObj = null;
	// if(rs.next()){
	// rObj = new Category(rs.getString("cat_type"),rs.getBoolean("cat_hot"));
	// rObj.setId(rs.getInt("cat_id"));
	// }
	// return rObj;
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// } finally {
	// util.closeConn(null, stat , conn);
	// }
	// }
	public List<Category> get(int page,int size){
		// 先在oracle那边测试,sql语句没问题,在复制过来
//		String sql="select * from (select t.*,rownum r from "
//				+ "(select * from category c order by c.cat_id desc) t where rownum<=?) "
//				+ "c where c.r>?";
		String sql = "select * from category";
		Object[] param=new Object[]{page*size,(page-1)*size};
		// 调用真分页
		 return super.executeSelect(sql, param);
	}

	public List<Category> getAll(){
		// 先在oracle那边测试,sql语句没问题,在复制过来
//		String sql="select * from (select t.*,rownum r from "
//				+ "(select * from category c order by c.cat_id desc) t where rownum<=?) "
//				+ "c where c.r>?";
		String sql = "select * from category";
		// 调用真分页
		 return super.executeSelect(sql, null);
	} 
	
	public List<Category> getByPage(String keyword,int page,int size){
		// 先在oracle那边测试,sql语句没问题,在复制过来
//		String sql="select * from (select t.*,rownum r from "
//				+ "(select * from category c where c.cat_type like ? order by c.cat_id desc) t where rownum<=?) "
//				+ "c where c.r>?";
//		Object[] param=new Object[]{"'%" + keyword + "%'",page*size,(page-1)*size};
//		Object[] param=new Object[]{"'%2%'",page*size,(page-1)*size};
//		return super.executeSelect(sql, param);

		String sql = "call UI_PRODUCT_GET_BY_PAGE(?,?,?,?)";
		Object[] param=new Object[]{keyword,page*size,(page-1)*size};
		return super.executeSPSelect(sql, param);
	}
	public Category getByIndex(int id) {
		// String sql="SELECT * FROM category WHERE cat_id = ?";
		// Object[] param=new Object[]{id};
		// return (Category) super.executeSelect(sql, param).get(0);

		String sql = "call UI_CATEGORY_GET_BY_INDEX(?,?)";
		Object[] param = new Object[] { id };
		return super.executeSPSelect(sql, param).get(0);
	}

	public int save(Category category) {
		// String sql="insert into category (cat_type,cat_hot) values (?,?)";
		// Object[] param=new Object[]{category.getType(),category.getHot()};
		// // 统一调用父类的操作数据的方法
		// return super.executeUpdate(sql, param);
		String sql = "call UI_CATEGORY_SAVE(?,?)";
		Object[] param = new Object[] { category.getType(), category.getHot() };
		return super.executeSPUpdate(sql, param);

	}

	public int update(Category category) {
		// String sql="update product set cat_type=?,cat_hot=? where cat_id=?";
		// Object[] param=new
		// Object[]{category.getType(),category.getHot(),category.getId()};
		// return super.executeUpdate(sql, param);

		String sql = "call UI_CATEGORY_UPDATE(?,?,?)";
		Object[] param = new Object[] { category.getId(), category.getType(),
				category.getHot() };
		return super.executeSPUpdate(sql, param);
	}

	public int delete(int id) {

		String sql = "call UI_CATEGORY_DELETE(?)";
		Object[] param = new Object[] { id };
		return super.executeSPUpdate(sql, param);

		// String sql="delete from category where cat_id=?";
		// Object[] param=new Object[]{id};
		// return super.executeUpdate(sql, param);
	}
}
