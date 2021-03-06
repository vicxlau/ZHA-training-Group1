package cn.oocl.dao.imple;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.oocl.model.Product;
import cn.oocl.model.Statistic;
import cn.oocl.service.imple.CategoryServiceImpl;

public class StatisticDaoImpl extends BaseDaoImpl<Statistic> {

	@Override
	protected Statistic getRowMapper(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Statistic> query(String from, String to) {
//		String sql = "SELECT c.cat_id, count(*) "
//				+ "FROM product_stat p LEFT JOIN CATEGORY_PRODUCT c ON c.pro_id = p.PRO_ID "
//				+ "group by c.cat_id";
		String sql ="SELECT c.CAT_ID, COUNT(*) FROM "
				+ "(SELECT PRO_ID FROM PRODUCT_STAT WHERE VISIT_AT > ? AND VISIT_AT   < ?) p "
				+ "LEFT JOIN CATEGORY_PRODUCT c ON c.PRO_ID = p.PRO_ID GROUP BY c.CAT_ID";
		Object[] param = new Object[] {from, to};
		return super.query(sql, param, new RowMapper<Statistic>(){
			@Override
			public Statistic mapRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Statistic statistic = new Statistic();
				statistic.setCount(rs.getInt("COUNT(*)"));	
				statistic.setCategory(rs.getString("CAT_ID"));
				return statistic;
			}
		});
	}

}
