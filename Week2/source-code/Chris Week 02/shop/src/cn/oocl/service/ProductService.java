package cn.oocl.service;

import java.util.List;

import cn.oocl.model.Product;

public interface ProductService {

	public abstract int save(Product product);

	public abstract int update(Product product);

	public abstract int delete(int id);

	public abstract Product getById(int id);

	public abstract List<Product> query(String keyword, int page, int size);

	public abstract List<Product> queryAll();

}