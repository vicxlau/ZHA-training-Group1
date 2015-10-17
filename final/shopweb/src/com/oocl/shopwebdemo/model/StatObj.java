package com.oocl.shopwebdemo.model;

import java.io.Serializable;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class StatObj implements Serializable {
	private static final long serialVersionUID = 1603842454809468995L;
	public int id;
	public Date date;

	public StatObj(int p_id) {
		id = p_id;
		date = new Date();
	}
	public StatObj(int p_id, Date p_date) {
		id = p_id;
		date = p_date;
	}

	public int isIDEqual(int p_id) {
		if (p_id == id)
			return 1;
		return 0;
	}

	private void log() {
		System.out.println(toString());
	}

	public String toString() {
		SimpleDateFormat dateFormatGmt = new SimpleDateFormat(
				"yyyy-MMM-dd HH:mm:ss");
		dateFormatGmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		return String.format("prod_id: %d \n date: %s", id,
				dateFormatGmt.format(date));
	}
}
