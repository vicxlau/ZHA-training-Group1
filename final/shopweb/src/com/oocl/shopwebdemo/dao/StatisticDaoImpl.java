package com.oocl.shopwebdemo.dao;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.Map.Entry;

import com.oocl.shopwebdemo.model.*;

public class StatisticDaoImpl extends BaseDaoImpl<CateProductStat> implements IStatisticDao {
	@Override
	protected List<CateProductStat> mapRowsToObjects(ResultSet rs) throws Exception {
//		List<CateProductStat> results = new ArrayList<CateProductStat>();
//		
//		while (rs.next()) {
//			User u= new User();
//			u.setId(rs.getInt("user_id"));
//			u.setName(rs.getString("user_name"));
//			u.setRoleId(rs.getInt("role_id"));			
//			results.add(u);
//		}
//		return results;
		return null;
	}
	
	@Override
	public void save(ApplicationStat stat){
		for(Entry<Integer, List<Date>> entry: stat.prod_list.entrySet()){
			int prod_id = entry.getKey();
			for(Date data : entry.getValue()){
				super.executeUpdate("call UI_PRODUCT_STAT_SAVE(?,?)",prod_id,data);
			}
		}
		
		for(Entry<Integer, List<Date>> entry: stat.cat_list.entrySet()){
			int prod_id = entry.getKey();
			for(Date data : entry.getValue()){
				super.executeUpdate("call UI_CATEGORY_STAT_SAVE(?,?)",prod_id,data);
			}
		}
	}
	
}