package com.oocl.shopwebdemo.dao;
import java.sql.*;
import java.util.*;

import com.oocl.shopwebdemo.model.*;

/*
 * 1: 相同的DB操作都需要声明在接口中
 * 2: 相同的代码实现,都应该抽取到父类中
 * */

//vicx
public class CategoryDaoImpl extends BaseDaoImpl<Category>{ //implements ICategoryDao {

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
	public List<Category> getAll(){
		String sql = "call UI_CATEGORY_GET_ALL(?)";
		 return super.executeQuery(sql);
	}
	public List<Category> getCategoryByIndex(int cid){
		String sql = "call UI_CATEGORY_GET_BY_ID(?,?)";
		 return super.executeQuery(sql,cid);
	} 
}
