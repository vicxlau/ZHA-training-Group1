package com.oocl.shopwebdemo.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.oocl.shopwebdemo.model.Product;

public class ProductDaoImpl extends BaseDaoImpl<Product> implements IProductDao {

	@Override
	protected List<Product> mapRowsToObjects(ResultSet rs) throws Exception {

		List<Product> results = new ArrayList<Product>();

		while (rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("pro_id"));
			product.setName(rs.getString("pro_name"));
			product.setPrice(rs.getDouble("pro_price"));
			product.setPic(rs.getString("pro_pic"));
			product.setRemark(rs.getString("pro_remark"));
			product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));

			results.add(product);
		}
		return results;
	}

	@Override
	public int addProduct(Product product) {
		// vicx-- fail???
//		return super.executeUpdate("call UI_PRODUCT_SAVE(?,?,?,?,?,?)",
//				product.getName(), product.getPrice(), product.getPic(),
//				product.getRemark(), product.getAdv(), product.getCategoryID());
		return super.executeUpdate("call UI_PRODUCT_SAVE(?,?,?,?,?,?,?)",
				product.getName(), product.getPrice(), product.getPic(),
				product.getRemark(), product.getAdv(), product.getCategoryID(), product.getDiscount());
	}

	@Override
	public int updateProduct(Product product) {
		// /verver
//		return super.executeUpdate("call UI_PRODUCT_UPDATE(?,?,?,?,?,?,?)",
//				product.getId(), product.getName(), product.getPrice(),
//				product.getPic(), product.getRemark(), product.getAdv(),
//				product.getCategoryID());
		return super.executeUpdate("call UI_PRODUCT_UPDATE(?,?,?,?,?,?,?,?)",
				product.getId(), product.getName(), product.getPrice(),
				product.getPic(), product.getRemark(), product.getAdv(),
				product.getCategoryID(), product.getDiscount());
	}

	@Override
	public int deleteProduct(Product product) {

		return super.executeUpdate("delete product where pro_id=?",
				product.getId());
	}

	// vicx!!!
	@Override
	public int getProductsSearchResultCountByKeyword(String keyword) {

		return super.executeCountQuery(
				"select count(*) from product where pro_name like ?", "%"
						+ keyword + "%");
	}
	@Override
	public int getProductResultCountInCategory(int cid) {
		return super.executeCountQuery(
				"select count(*) from product p left join category_product cp on cp.pro_id = p.pro_id where cp.cat_id =?", 
					cid);
	}

	@Override
	public List<Product> getProductByIndex(int id) {
		String sql = "call UI_PRODUCT_GET_BY_INDEX(?,?)";
		// return super.executeQuery(sql,id);
		return super.executeQuery(sql, new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {

				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("pro_id"));
					product.setName(rs.getString("pro_name"));
					product.setPrice(rs.getDouble("pro_price"));
					product.setPic(rs.getString("pro_pic"));
					product.setRemark(rs.getString("pro_remark"));
					product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));
					product.setCategoryID(rs.getInt("cat_id"));
					results.add(product);
				}
				return results;
			}
		}, id);
	}

//	@Override
//	public List<Product> getAdProduct() {
//		String sql = "call UI_PRODUCT_GET_BY_ADV(?)";
//		// return super.executeQuery(sql);
//		return super.executeQuery(sql, new RowMapper<Product>() {
//
//			@Override
//			public List<Product> getRowMapper(ResultSet rs) throws Exception {
//
//				List<Product> results = new ArrayList<Product>();
//
//				while (rs.next()) {
//					Product product = new Product();
//					product.setId(rs.getInt("pro_id"));
//					product.setName(rs.getString("pro_name"));
//					product.setPrice(rs.getDouble("pro_price"));
//					product.setPic(rs.getString("pro_pic"));
//					product.setRemark(rs.getString("pro_remark"));
//
//					results.add(product);
//				}
//				return results;
//			}
//		});
//	}

	@Override
	public List<Product> getProductInCategory(int cid, int pageSize, int pageNum) {
		String sql = "call UI_PRODUCT_GET_BY_CAT_ID(?,?,?,?)";
		// return super.executeQuery(sql,
		// cid,
		// pageSize*pageNum,
		// (pageNum-1)*pageSize
		// );
		return super.executeQuery(sql, new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {

				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("pro_id"));
					product.setName(rs.getString("pro_name"));
					product.setPrice(rs.getDouble("pro_price"));
					product.setPic(rs.getString("pro_pic"));
					product.setRemark(rs.getString("pro_remark"));
					product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));
//					product.setCategoryID(cid);
					results.add(product);
				}
				return results;
			}
		},

		cid, pageSize * pageNum, (pageNum - 1) * pageSize);
	}

	@Override
	public List<Product> searchProductsByKeywordWithPaging(String keyword,int pageSize, int pageNum) {

		String sql = "call UI_PRODUCT_GET_BY_KEYWORD(?,?,?,?)";
		// return super.executeQuery(sql,
		// "%"+keyword+"%",
		// pageSize*pageNum,
		// (pageNum-1)*pageSize
		// );
		return super.executeQuery(sql, new RowMapper<Product>() {
			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {
				return mapRowsToObjects(rs);
			}
		},

		"%" + keyword + "%", pageSize * pageNum, (pageNum - 1) * pageSize);

		// String subQuery1 =
		// "select * from product p where p.pro_name like ? order by p.pro_id desc";
		// String subQuery2 = "select t.*, rownum r from (" + subQuery1 +
		// ") t where rownum <= ?";
		// String preparedStatement = "select * from (" + subQuery2 +
		// ") p where p.r > ?";
		// return super.executeQuery(preparedStatement,
		// "%"+keyword+"%",
		// pageSize*pageNum,
		// (pageNum-1)*pageSize
		// );
	}

	@Override
	public List<Product> getHotProduct() {
		// vicx:: pending
		String sql = "call UI_PRODUCT_GET_BY_VISTTIME(?,?)";
		// return super.executeQuery(sql, 6);
		return super.executeQuery(sql, new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {

				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("pro_id"));
					product.setName(rs.getString("pro_name"));
					product.setPrice(rs.getDouble("pro_price"));
					product.setPic(rs.getString("pro_pic"));
					product.setRemark(rs.getString("pro_remark"));
					product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));

					results.add(product);
				}
				return results;
			}
		}, 6);

		// String sql = "call UI_PRODUCT_GET_ALL(?)";
		// return super.executeQuery(sql);
	}

	@Override
	public List<Product> getAdvProduct() {
		String sql = "call UI_PRODUCT_GET_BY_ADV(?,?)";
		// return super.executeQuery(sql,3);
		return super.executeQuery(sql, new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {

				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("pro_id"));
					product.setName(rs.getString("pro_name"));
					product.setPrice(rs.getDouble("pro_price"));
					product.setPic(rs.getString("pro_pic"));
					product.setRemark(rs.getString("pro_remark"));
					product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));

					results.add(product);
				}
				return results;
			}
		}, 3);
	}

	@Override
	public int addVisitTime(Product product) {
		String sql = "call UI_PRODUCT_UPDATE_VISTTIME(?,?)";
		return super.executeUpdate(sql, product.getId(), 1);

	}

	@Override
	public List<Product> getDistinctOrderedProductNames() {
		
		return super.executeQuery("call UI_PRODUCT_GET_DISTINCT_NAME(?)", new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {
				
				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setName(rs.getString("pro_name"));

					results.add(product);
				}
				return results;
			}
			
		});
	}

	@Override
	public List<Product> getProductByCatIdAndPrice(int cid, int lowerbound,
			int upperbound, int pageSize, int pageNum) {
//		String sql = "select rs.* from (select t.*, rownum r from ( select k.* from "
//				+ "(select p.* from category_product c_p left join product p on c_p.pro_id = p.pro_id where c_p.cat_id = ?)"
//				+ " k where k.pro_dis <= ? and k.pro_dis > ? )t where rownum <= ? ) rs where rs.r > ?";
		//UI_PRODUCT_GET_BY_CAT_PRICE (cat_index IN NUMBER,upperbound IN NUMBER,lowerbound IN NUMBER, last_pro_num IN NUMBER, first_pro_num IN NUMBER, pro OUT sys_refcursor)
		String sql = "call UI_PRODUCT_GET_BY_CAT_PRICE (?,?,?,?,?,?)";
		return super.executeQuery(sql, new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {
				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("pro_id"));
					product.setName(rs.getString("pro_name"));
					product.setPrice(rs.getDouble("pro_price"));
					product.setPic(rs.getString("pro_pic"));
					product.setRemark(rs.getString("pro_remark"));
					product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));

					results.add(product);

				}
				return results;
			}

			
		}, cid,upperbound,lowerbound,pageSize * pageNum, (pageNum - 1) * pageSize);
	}

	@Override
	public int getProductResultCountByCatIdAndPrice(int cid,
			int lowerbound, int upperbound) {
		String sql = "select count(*) from (select k.* from"+
				  "(select p.* from category_product c_p left join product p on c_p.pro_id = p.pro_id where c_p.cat_id = ?"+
				  ") k where k.PRO_DIS <= ? and k.PRO_DIS > ?) r";
		return super.executeCountQuery(sql, cid,upperbound,lowerbound);
	}
	
    @Override
    public List<Product> getProductsByIds(List<Integer> productIdList) {

        StringBuilder builder = new StringBuilder();
        for (Integer pro_id : productIdList) {
            builder.append(pro_id + ",");
        }
        
        return super.executeQuery_preparedStatement("select * from product where pro_id in (" + builder.substring(0, builder.length()-1) +")", new RowMapper<Product>() {

            @Override
            public List<Product> getRowMapper(ResultSet rs) throws Exception {
                
                List<Product> results = new ArrayList<Product>();

                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("pro_id"));
                    product.setName(rs.getString("pro_name"));
                    product.setPrice(rs.getDouble("pro_price"));
                    product.setPic(rs.getString("pro_pic"));
                    product.setRemark(rs.getString("pro_remark"));
                    product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));

                    results.add(product);
                }
                return results;
            }
            
        });
    }
    
    @Override
    public int getProductResultCountByCatIdAndVolumn(int cid) {
    	String sql = "select count(*) from (select * from (select * from (select item_name, sum(item_number) as sum_number from item group by item_name) t order by t.sum_number desc) k right join product p on p.PRO_NAME = k.item_name join category_product c_p on c_p.PRO_ID = p.PRO_ID where c_p.CAT_ID = ?)";
		return super.executeCountQuery(sql, cid);
    }
    
    @Override
    public List<Product> getProductByCatIdAndVolumn(int cid, int pageSize, int pageNum) {
    	String sql = 
    		"select * from ("+
	    		"select p.*, rownum r from ("+
	    			"select * from ("+
	    				"select item_name, sum(item_number) as sum_number from item group by item_name) t "+
	    			"order by t.sum_number desc) k "+
	    		"right join product p on p.PRO_NAME = k.item_name join category_product c_p on c_p.PRO_ID = p.PRO_ID where c_p.CAT_ID = ? AND rownum <= ?"+
	    	") l where l.r >?";
    	return super.executeQuery_preparedStatement(sql, new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {
				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("pro_id"));
					product.setName(rs.getString("pro_name"));
					product.setPrice(rs.getDouble("pro_price"));
					product.setPic(rs.getString("pro_pic"));
					product.setRemark(rs.getString("pro_remark"));
					product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));

					results.add(product);

				}
				return results;
			}

		}, cid,pageSize * pageNum, (pageNum - 1) * pageSize);
	}
    @Override
    public int getProductResultCountByCatIdAndVisit(int cid) {
    	String sql = "select count(*) from (select * from product order by pro_visittime desc) p join category_product c_p on c_p.PRO_ID = p.PRO_ID where c_p.CAT_ID = ?";
		return super.executeCountQuery(sql, cid);
    }
	
	@Override
    public List<Product> getProductByCatIdAndVisit(int cid, int pageSize, int pageNum) {
    	String sql = 
    		"select * from ("+
    			"select p.*, rownum r from "+
    			"(select * from product order by pro_visittime desc) p "+
    			"join category_product c_p on c_p.PRO_ID = p.PRO_ID where c_p.CAT_ID = ? and rownum <= ?) t "+
    		"where t.r >?";
		return super.executeQuery_preparedStatement(sql, new RowMapper<Product>() {

			@Override
			public List<Product> getRowMapper(ResultSet rs) throws Exception {
				List<Product> results = new ArrayList<Product>();

				while (rs.next()) {
					Product product = new Product();
					product.setId(rs.getInt("pro_id"));
					product.setName(rs.getString("pro_name"));
					product.setPrice(rs.getDouble("pro_price"));
					product.setPic(rs.getString("pro_pic"));
					product.setRemark(rs.getString("pro_remark"));
					product.setDiscount(Integer.parseInt(rs.getString("pro_dis")));

					results.add(product);

				}
				return results;
			}

			
		}, cid,pageSize * pageNum, (pageNum - 1) * pageSize);
	}
}