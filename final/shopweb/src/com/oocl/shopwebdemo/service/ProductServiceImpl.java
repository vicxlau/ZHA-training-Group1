package com.oocl.shopwebdemo.service;

import com.oocl.shopwebdemo.dao.IProductDao;
import com.oocl.shopwebdemo.dao.ProductDaoImpl;
import com.oocl.shopwebdemo.model.Product;

public class ProductServiceImpl implements IProductService {

	private IProductDao productDao = new ProductDaoImpl();
	
	private void validateProductInfo(Product product) {
		if (product == null)
			throw new IllegalArgumentException("Null product.");
		
		if (product.getName() == null)
			throw new IllegalArgumentException("Null product name.");
		
		if (product.getName().equals(""))
			throw new IllegalArgumentException("Empty product name.");
		
		if (product.getPrice() == null)
			throw new IllegalArgumentException("Null product price.");
		
		if (product.getPrice() < 0)
			throw new IllegalArgumentException("Negative product price.");
	}
	
	@Override
	public void addProduct(Product product) {
		
		validateProductInfo(product);
		productDao.addProduct(product);
	}

	@Override
	public void updateProduct(Product product) {
		
		if (product.getId() == null)
			throw new IllegalArgumentException("No Id set for product update.");
		
		validateProductInfo(product);
		productDao.updateProduct(product);
	}

	@Override
	public void deleteProduct(int productId) {

		productDao.deleteProduct(new Product(productId));
	}

	@Override
	public void addProductVisitTime(int productId) {

		productDao.addVisitTime(new Product(productId));
	}

	@Override
	public Product getProductByIndex(int pid) {
		return productDao.getProductByIndex(pid).get(0);
	}
}
