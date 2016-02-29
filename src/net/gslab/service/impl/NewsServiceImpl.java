package net.gslab.service.impl;

import javax.annotation.Resource;

import java.util.List;

import net.gslab.dao.BaseDao;
import net.gslab.dao.NewsDao;
import net.gslab.entity.News;
import net.gslab.service.NewsService;

import org.springframework.stereotype.Service;

@Service(value="newsServiceImpl")
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService{

	private NewsDao newsDao;
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!一定要在service中写上层dao的set
	@Resource(name="newsDaoImpl")
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
		super.setBaseDao(newsDao);
	}
	
	public List<News> listNews()
	{
		List<News> news=newsDao.find("from News");
		System.out.println("listNews() in the UserService"+news);
		return news;
	}
	

	
}
