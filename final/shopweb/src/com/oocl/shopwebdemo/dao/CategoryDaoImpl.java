package com.oocl.shopwebdemo.dao;
import java.sql.*;
import java.util.*;

import com.oocl.shopwebdemo.model.*;

/*
 * 1: 相同的DB操作都需要声明在接口中
 * 2: 相同的代码实现,都应该抽取到父类中
 * */

//vicx
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements ICategoryDao {

	@Override
	protected List<Category> mapRowsToObjects(ResultSet rs) throws Exception {
		List<Category> results = new ArrayList<Category>();

		while (rs.next()) {
			Category category = new Category();
			category.setId(rs.getInt("cat_id"));
			category.setType(rs.getString("cat_type"));
			category.setHot(rs.getBoolean("cat_hot"));
			
			results.add(category);
		}
		return results;
	}
	@Override
	public List<Category> getAll(){
		String sql = "call UI_CATEGORY_GET_ALL(?)";
//		 return super.executeQuery(sql);
		 return super.executeQuery(sql, new RowMapper<Category>() {

			@Override
			public List<Category> getRowMapper(ResultSet rs) throws Exception {
				return mapRowsToObjects(rs);
			}
		});
	}
	
	@Override
	public List<Category> getCategoryByKeyword(String keyword,int pageSize, int pageNum) {
		String sql = "call UI_CATEGORY_GET_BY_KEYWORD(?,?,?,?)";
		return super.executeQuery(sql, new RowMapper<Category>() {
			@Override
			public List<Category> getRowMapper(ResultSet rs) throws Exception {
				return mapRowsToObjects(rs);
			}
		},
		"%" + keyword + "%", pageSize * pageNum, (pageNum - 1) * pageSize);
	}
	
	@Override
	public List<Category> getCategoryByIndex(int cid){
		String sql = "call UI_CATEGORY_GET_BY_ID(?,?)";
//		 return super.executeQuery(sql,cid);
		 return super.executeQuery(sql,new RowMapper<Category>() {

			@Override
			public List<Category> getRowMapper(ResultSet rs) throws Exception {
				return mapRowsToObjects(rs);
			}
		},cid);
	}
	
	@Override
	public int addCategory(Category category) {
		return super.executeUpdate("call UI_CATEGORY_SAVE(?,?)",
				category.getType(),category.getHot());
	}

	@Override
	public int updateCategory (Category category) {
		return super.executeUpdate("call UI_CATEGORY_UPDATE(?,?,?)",
				category.getId(), category.getType(), category.getHot());
	}
	
	@Override
	public int deleteCategory(int id) {
		return super.executeUpdate("call UI_CATEGORY_DELETE(?)", id);
	}
}
