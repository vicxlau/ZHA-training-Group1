package cn.oocl.service;

import java.util.List;

import cn.oocl.model.ApplicationStat;

public interface StatisticService {

	public abstract List<ApplicationStat> query(String from, String to);

}