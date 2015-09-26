package cn.oocl.model;

/*
 * Java的类====>表 Table
 * Java的对象====> 记录
 * model的创建的好处: 数据的存储与相关数据的操作分开
 * */
public class Category {

	private Integer id;

	private String type;

	private Boolean hot;
	
	public Category(){
		
		super();
	}
	
	public Category(String type, Boolean hot) {
		super();
		this.type = type;
		this.hot = hot;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getHot() {
		return hot;
	}

	public void setHot(Boolean hot) {
		this.hot = hot;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", hot=" + hot + "]";
	}

	
	
}
