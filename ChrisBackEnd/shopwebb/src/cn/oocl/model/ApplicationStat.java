package cn.oocl.model;

import java.io.Serializable;
import java.text.Format;
import java.util.*;

public class ApplicationStat implements Serializable {
	private static final long serialVersionUID = 4258740465014134126L;
	private String name;
	private int count;

	public ApplicationStat(String name,int count){
		this.name = name;
		this.count = count;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
