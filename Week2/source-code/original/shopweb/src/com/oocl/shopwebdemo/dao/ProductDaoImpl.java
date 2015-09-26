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
			
			results.add(product);
		}
		
		return results;
	}
	

	@Override
	public int addProduct(Product product) {

		return super.executeUpdate("insert into product (pro_name,pro_price,pro_pic,pro_remark) values (?,?,?,?)",
						product.getName(),
						product.getPrice(),
						product.getPic(),
						product.getRemark()
					);
	}


	@Override
	public int updateProduct(Product product) {

		return super.executeUpdate("update product set pro_name=?, pro_price=?, pro_pic=?, pro_remark=? where pro_id=?",
						product.getName(),
						product.getPrice(),
						product.getPic(),
						product.getRemark(),
						product.getId()
				);
	}


	@Override
	public int deleteProduct(Product product) {
		
		return super.executeUpdate("delete product where pro_id=?",	product.getId());
	}
	
	
	@Override
	public int getProductsSearchResultCountByKeyword(String keyword) {
		
		return super.executeCountQuery("select count(*) from product where pro_name like ?", "%"+keyword+"%");
	}


	@Override
	public List<Product> searchProductsByKeywordWithPaging(String keyword, int pageSize, int pageNum) {

		String subQuery1 = "select * from product p where p.pro_name like ? order by p.pro_id desc";
		String subQuery2 = "select t.*, rownum r from (" + subQuery1 + ") t where rownum <= ?";
    	String preparedStatement = "select * from (" + subQuery2 + ") p where p.r > ?";
    
		return super.executeQuery(preparedStatement,
						"%"+keyword+"%",
						pageSize*pageNum,
						(pageNum-1)*pageSize
					);
	}
}