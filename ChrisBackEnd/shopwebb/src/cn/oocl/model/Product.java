package cn.oocl.model;

/*
 * Java的类====>表 Table
 * Java的对象====> 记录
 * model的创建的好处: 数据的存储与相关数据的操作分开
 * */
public class Product {

	private Integer id;

	private String name;

	private Double price;

	private String pic;

	private String remark;
	
	private Double adv;
	
	private Integer discount;

	private Category category;

	public Product() {
		super();
	}

	public Double getAdv() {
		return adv;
	}

	public void setAdv(Double adv) {
		this.adv = adv;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Product(String name, Double price, String pic, String remark) {
		super();
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.remark = remark;
	}

	public Product(Integer id, String name, Double price, String pic,
			String remark) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.pic = pic;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price
				+ ", pic=" + pic + ", remark=" + remark + ", category="
				+ category + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
