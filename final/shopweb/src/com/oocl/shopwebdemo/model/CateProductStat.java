package com.oocl.shopwebdemo.model;

import java.io.Serializable;
import java.util.*;

public class CateProductStat implements Serializable{
	private static final long serialVersionUID = 1603842454809468995L;
	private static Map<Integer, Integer> prod_list = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> cat_list = new HashMap<Integer, Integer>();
	private static Map<Integer, String> user_list = new HashMap<Integer, String>();
	
	public CateProductStat(){
		
	}
	public void update(int user_id, int prod_id, int cat_id){
		if(prod_list.containsKey(prod_id))
			updateProdStat(prod_id);
		else
			addProdStat(prod_id);
		
		if(cat_list.containsKey(cat_id))
			updateCatStat(cat_id);
		else 
			addCatStat(cat_id);
		
		if(user_list.containsKey(user_id))
			updateUserHistory(user_id,prod_id);
		else 
			addUserHistory(user_id,prod_id);
		
	}
	public void addProdStat(int prod_id){
		prod_list.put(prod_id, 1);		
	}
	public void addCatStat(int cat_id){
		cat_list.put(cat_id, 1);		
	}
	public void addUserHistory(int user_id, int prod_id){
		cat_list.put(user_id, prod_id);		
	}
	public void updateProdStat(int prod_id){
		int count = (int)prod_list.get(prod_id) + 1;
		prod_list.put(prod_id, count);		
	}
	public void updateCatStat(int cat_id){
		int count = (int)cat_list.get(cat_id) + 1;
		cat_list.put(cat_id, count);		
	}
	public void updateUserHistory(int user_id, int prod_id){
		String history = user_list.get(user_id) +","+ prod_id;
		user_list.put(user_id, history);		
	}
}
