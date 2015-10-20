package com.oocl.shopwebdemo.model;

import java.io.Serializable;
import java.text.Format;
import java.util.*;

public class ProductCurrentVisiter implements Serializable {
	private static final long serialVersionUID = 4258740465014134126L;
	public static Map<Integer, Integer> prod_visitor_count;

	static{
		prod_visitor_count = new HashMap<Integer, Integer>();
	}
	
	public int addVisitor(int prod_id) {
		int count = 0;
		if(prod_visitor_count.containsKey(prod_id))
			count = prod_visitor_count.get(prod_id);
		prod_visitor_count.put(prod_id, ++count);
		return prod_visitor_count.get(prod_id);
	}

	public void removeVisitor(int prod_id) {
		if(prod_visitor_count.containsKey(prod_id)){
			int count = prod_visitor_count.get(prod_id);
			prod_visitor_count.put(prod_id, (count<=0)?0:--count);
		}
	}

	public int getVisitor(int prod_id) {
		return (prod_visitor_count.containsKey(prod_id))?
				prod_visitor_count.get(prod_id):0;
	}
	
	public void log(int prod_id) {
		System.out.println(String
						.format("prod_id: %d >> visitorCount: %d \n",
								prod_id, getVisitor(prod_id)));
	}
}
