package net.gslab.service.impl;

import javax.annotation.Resource;

import java.util.List;

import net.gslab.dao.BaseDao;
import net.gslab.dao.InfoDao;
import net.gslab.entity.Info;
import net.gslab.service.InfoService;

import org.springframework.stereotype.Service;

@Service(value="infoServiceImpl")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService{

	private InfoDao infoDao;
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!一定要在service中写上层dao的set
	@Resource(name="infoDaoImpl")
	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
		super.setBaseDao(infoDao);
	}
	
	public List<Info> listInfo()
	{
		List<Info> info=infoDao.find("from Info");
		System.out.println("listInfo() in the UserService"+info);
		return info;
	}
	

	
}
