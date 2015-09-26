package cn.oocl.service;

import java.util.List;

import cn.oocl.model.Category;

public interface CategoryService {

	public abstract int save(Category category);

	public abstract int update(Category category);

	public abstract int delete(int id);

	public abstract Category getById(int id);

	public abstract List<Category> query(String keyword, int page, int size);

	public abstract List<Category> queryAll();

}