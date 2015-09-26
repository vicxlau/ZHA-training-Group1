package cn.oocl.dao.imple;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import cn.oocl.model.Category;
import cn.oocl.model.Product;
import cn.oocl.util.JDBCUtil;

/*
 * 1: 相同的DB操作都需要声明在接口中
 * 2: 相同的代码实现,都应该抽取到父类中
 * */
public class ProductDaoImpl extends BaseDaoImpl<Product> {

	// implement product 分頁  page = 2 size = 3
	public List<Product> query(String keyword, int page, int size){
		// test in oracle first, if no problem then paste here.
		String sql = "select * from (select t.*, rownum r from "
		+ "(select * from product c where c.pro_name like ? order by c.pro_id) t where rownum <=?)"
		+ " c where c.r > ?";
		Object[] param=new Object[]{"%"+keyword+"%", page*size, (page-1)*size};
		//String sql = "select * from product";
		//Object[] param=new Object[]{};
		//真分頁顯示紀錄
		return super.query(sql, param);
		
		//假分頁
		//String sql2 = "select * from product p where p.PRO_NAME like ? order by p.pro_id desc";
		//return super.query(sql2,  keyword, page, size);
	}

	@Override
	protected Product getRowMapper(ResultSet rs) throws Exception {
		Product product = new Product();
		product.setId(rs.getInt("pro_id"));
		product.setPrice(rs.getDouble("pro_price"));;
		product.setName(rs.getString("pro_name"));
		product.setPic(rs.getString("pro_pic"));
		product.setRemark(rs.getString("pro_remark"));
		return product;
	}

	public int delete(int id){
		String sql="delete from product where pro_id=?";
		Object[] param=new Object[]{id};
		return super.executeUpdate(sql, param);
	}
	
	public Product getById(int id) {
		String sql = "select * from product where pro_id = ?";
		return (Product) super.get(sql, id);
	}

	public int save(Product product) {
		String sql = "insert into product (pro_name,pro_price,pro_pic,pro_remark) values (?,?,?,?)";
		Object[] param = new Object[] { product.getName(), product.getPrice(),
				product.getPic(), product.getRemark() };
		return super.executeUpdate(sql, param);
		
//		String sql = "call UI_PRODUCT_SAVE(?,?,?,?,?,?)";
//		Object[] param = new Object[] { product.getName(), product.getPrice(),
//				product.getPic(), product.getRemark(),1,1 };
//		return super.executeSPUpdate(sql, param);
	}

	public int update(Product product) {
		String sql = "update product set pro_name=?,pro_price=?,pro_pic=?,pro_remark=? where pro_id=?";
		Object[] param = new Object[] { product.getName(), product.getPrice(),
				product.getPic(), product.getRemark(), product.getId() };
		return super.executeUpdate(sql, param);
	}

}
