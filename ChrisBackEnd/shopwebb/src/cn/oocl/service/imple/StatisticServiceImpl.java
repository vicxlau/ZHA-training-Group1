package cn.oocl.service.imple;

import java.util.ArrayList;
import java.util.List;

import cn.oocl.dao.imple.StatisticDaoImpl;
import cn.oocl.model.ApplicationStat;
import cn.oocl.model.Category;
import cn.oocl.model.Statistic;
import cn.oocl.service.CategoryService;
import cn.oocl.service.StatisticService;

public class StatisticServiceImpl implements StatisticService {

	private StatisticDaoImpl statisticDao = new StatisticDaoImpl();
	private CategoryService categoryService = new CategoryServiceImpl();
	
	/* (non-Javadoc)
	 * @see cn.oocl.service.imple.StatisticService#query()
	 */
	@Override
	public List<ApplicationStat> query(String from, String to){
		List<Statistic> stats = statisticDao.query(from, to);
		List<ApplicationStat> stat = new ArrayList<ApplicationStat>();
		
		for(int i = 0; i < stats.size(); i++){
			int cid = Integer.parseInt(stats.get(i).getCategory());
			Category category = categoryService.getById(cid);
			stat.add(new ApplicationStat(category.getType(), stats.get(i).getCount()));
			//stat.add(new ApplicationStat("123", stats.get(i).getCount()));
		}
		return stat;
	}
	
	
}
