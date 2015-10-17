package com.oocl.shopwebdemo.model;

import java.io.Serializable;
import java.text.Format;
import java.util.*;

public class ApplicationStat implements Serializable {
	private static final long serialVersionUID = 4258740465014134126L;
	public static Map<Integer, List<Date>> prod_list;
	public static Map<Integer, List<Date>> cat_list;
	public static Map<Integer, List<StatObj>> user_list;

	static{
		prod_list = new HashMap<Integer, List<Date>>();
		cat_list = new HashMap<Integer, List<Date>>();
		user_list = new HashMap<Integer, List<StatObj>>();
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
		List<Date> list = new ArrayList<Date>();
		list.add(new Date());
		prod_list.put(prod_id, list);
	}

	public void addCatStat(int cat_id) {
		List<Date> list = new ArrayList<Date>();
		list.add(new Date());
		cat_list.put(cat_id, list);
	}

//	Map<Integer, List<StatObj> 
	public void addUserHistory(int user_id, int prod_id) {
		List<StatObj> list = new ArrayList<StatObj>();
		list.add(new StatObj(prod_id));
		user_list.put(user_id, list);
	}

//	Map<Integer, List<Date>>
	public void updateProdStat(int prod_id) {
		List<Date> list = prod_list.get(prod_id);
		list.add(new Date());
		prod_list.put(prod_id, list);
	}

	public void updateCatStat(int cat_id) {
		List<Date> list = cat_list.get(cat_id);
		list.add(new Date());
		cat_list.put(cat_id, list);
	}

	public void updateUserHistory(int user_id, int prod_id) {
		List<StatObj> list = user_list.get(user_id);
		list.add(new StatObj(prod_id));
		user_list.put(user_id, list);
	}

	public int getUserProductCount(int user_id, int prod_id) {
		List<StatObj> list = user_list.get(user_id);
		int count = 0;
		for(StatObj obj : list){
			count += obj.isIDEqual(prod_id);
		}
		return count;
	}
	
	public List<Date> getUserProductList(int user_id, int prod_id) {
		List<StatObj> list = user_list.get(user_id);
		List<Date> result = new ArrayList<Date>();
		for(StatObj obj : list){
			if(obj.id == prod_id) result.add(obj.date);
		}
		return result;
	}
	
	private void log(int user_id, int prod_id, int cat_id) {
		System.out.println(String
						.format("prod_id: %d >> count: %d \n cat_id: %d >> count:%d \n",
								prod_id, (prod_list.get(prod_id)==null)?null:prod_list.get(prod_id).size(), 
								cat_id,(cat_list.get(cat_id)==null)?null:cat_list.get(cat_id).size()));
		if(user_id!=0)
			System.out.println(String
					.format("user_id: %d >> user:%s ",
								user_id,(user_list.get(user_id)==null)?null:user_list.get(user_id).size()));
	}
}
