package net.gslab.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.hibernate.Query;
import org.hibernate.Session;

import net.gslab.dao.BoardDao;
import net.gslab.entity.Board;

@Repository(value="boardDaoImpl")
public class BoardDaoImpl extends BaseDaoImpl<Board> implements BoardDao{
	
	
}
