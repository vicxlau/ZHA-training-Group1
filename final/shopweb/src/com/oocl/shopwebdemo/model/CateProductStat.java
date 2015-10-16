package com.oocl.shopwebdemo.model;

import java.io.Serializable;
import java.text.Format;
import java.util.*;

public class CateProductStat implements Serializable {
	private static final long serialVersionUID = 1603842454809468995L;
	public static Map<Integer, Integer> prod_list;
	public static Map<Integer, Integer> cat_list;
	public static Map<Integer, List<Integer>> user_list;

	static{
		prod_list = new HashMap<Integer, Integer>();
		cat_list = new HashMap<Integer, Integer>();
		user_list = new HashMap<Integer, List<Integer>>();		
	}
	
	public void update(int user_id, int prod_id, int cat_id) {
		log(user_id, prod_id, cat_id);
		if (prod_list.containsKey(prod_id))
			updateProdStat(prod_id);
		else
			addProdStat(prod_id);

		if (cat_list.containsKey(cat_id))
			updateCatStat(cat_id);
		else
			addCatStat(cat_id);

		if (user_id != 0) {
			if (user_list.containsKey(user_id))
				updateUserHistory(user_id, prod_id);
			else
				addUserHistory(user_id, prod_id);
		}
		log(user_id, prod_id, cat_id);
	}

	public void addProdStat(int prod_id) {
		prod_list.put(prod_id, 1);
	}

	public void addCatStat(int cat_id) {
		cat_list.put(cat_id, 1);
	}

	public void addUserHistory(int user_id, int prod_id) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(prod_id);
		user_list.put(user_id, list);
	}

	public void updateProdStat(int prod_id) {
		int count = (int) prod_list.get(prod_id) + 1;
		prod_list.put(prod_id, count);
	}

	public void updateCatStat(int cat_id) {
		int count = (int) cat_list.get(cat_id) + 1;
		cat_list.put(cat_id, count);
	}

	public void updateUserHistory(int user_id, int prod_id) {
		List<Integer> list = user_list.get(user_id);
		if(list == null) list = new ArrayList<Integer>();
		list.add(prod_id);
		user_list.put(user_id, list);
	}

	private void log(int user_id, int prod_id, int cat_id) {
		System.out
				.println(String
						.format("prod_id: %d \n count: %d \n cat_id: %d \n count:%d \n user_id: %d \n user:%s ",
								prod_id, prod_list.get(prod_id), cat_id,
								cat_list.get(cat_id), user_id,
								user_list.get(user_id)));
	}
}
