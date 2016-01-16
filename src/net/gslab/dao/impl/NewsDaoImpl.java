package net.gslab.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Session;

import net.gslab.dao.NewsDao;
import net.gslab.entity.News;

@Repository(value="newsDaoImpl")
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao{
	
	
}
