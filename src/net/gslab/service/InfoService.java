package net.gslab.service;

import java.util.List;

import net.gslab.entity.Info;

public interface InfoService extends BaseService<Info>{
	 List<Info> listInfo();
}
