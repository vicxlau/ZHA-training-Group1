package cn.oocl.dao.imple;

import java.sql.ResultSet;
import java.util.*;

import cn.oocl.model.Category;
import cn.oocl.model.Product;

/*
 * 1: 相同的DB操作都需要声明在接口中
 * 2: 相同的代码实现,都应该抽取到父类中
 * */
public class ProductDaoImpl extends BaseDaoImpl<Product> {

	@Override
	protected Product getRowMapper(ResultSet rs) throws Exception {
		Product obj = new Product(rs.getInt("pro_id"),rs.getString("pro_name"), rs.getDouble("pro_price"), rs.getString("pro_pic"), rs.getString("pro_remark"));
		return obj;
	}
	

	public List<Product> getAll(){
		// 先在oracle那边测试,sql语句没问题,在复制过来
//		String sql="select * from (select t.*,rownum r from "
//				+ "(select * from category c order by c.cat_id desc) t where rownum<=?) "
//				+ "c where c.r>?";
		//work
		String sql = "select * from product";
		 return super.executeSelect(sql, null);
		
//		 String sql = "call UI_CATEGORY_GET_BY_HOT(?)";
//		 return super.executeSPSelect(sql, null);
	} 
	
	public List<Product> getByPage(String keyword,int page,int size){
		// 先在oracle那边测试,sql语句没问题,在复制过来
		String sql="select * from (select t.*,rownum r from "
				+ "(select * from product p where p.pro_name like ? order by p.pro_id desc) t where rownum<=?) "
				+ "p where p.r>?";
//				+ "(select * from product p where p.pro_name like '%?%' order by p.pro_id desc) t where rownum<=?) "
//		Object[] param=new Object[]{keyword, page*size,(page-1)*size};
//		Object[] param=new Object[]{"'%" + keyword + "%'",page*size,(page-1)*size};
		Object[] param=new Object[]{"'%手%'",3,1};
		// 调用真分页
		 return super.executeSelect(sql, param);
	}
	public Product getByIndex(int id){
//		String sql="SELECT * FROM product WHERE pro_id = ?";
//		String sql = "PRODUCT_GET_BY_INDEX";
		String SPname = "call UI_PRODUCT_GET_BY_INDEX(?,?)";
		Object[] param=new Object[]{id};
		return super.executeSPSelect(SPname,param).get(0);
		
//		return (Product) super.executeSelect(sql, param).get(0);
	}
	public int save(Product product){
//		String sql="insert into product (pro_name,pro_price,pro_pic,pro_remark) values (?,?,?,?)";
//		return super.executeUpdate(sql, param);
		String sql = "call UI_PRODUCT_SAVE(?,?,?,?)";
		Object[] param=new Object[]{product.getName(),product.getPrice(),product.getPic(),product.getRemark()};
		return super.executeSPUpdate(sql, param);
	}
	
	public int update(Product product){
//		String sql="update product set pro_name=?,pro_price=?,pro_pic=?,pro_remark=? where pro_id=?";
//		Object[] param=new Object[]{product.getName(),product.getPrice(),product.getPic(),product.getRemark(),product.getId()};
//		return super.executeUpdate(sql, param);
		String sql = "call UI_PRODUCT_UPDATE(?,?,?,?,?)";
		Object[] param=new Object[]{product.getId(),product.getName(),product.getPrice(),product.getPic(),product.getRemark()};
		return super.executeSPUpdate(sql, param);
	}
	public int delete(int id){
		String sql="call UI_PRODUCT_DELETE(?)";
		Object[] param=new Object[]{id};
		return super.executeSPUpdate(sql, param);
		
//		String sql="delete from product where pro_id=?";
//		Object[] param=new Object[]{id};
//		return super.executeUpdate(sql, param);
	}
}
