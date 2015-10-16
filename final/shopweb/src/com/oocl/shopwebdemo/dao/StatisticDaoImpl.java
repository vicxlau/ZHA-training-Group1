package com.oocl.shopwebdemo.dao;

import java.sql.*;
import java.util.*;

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
	
}
