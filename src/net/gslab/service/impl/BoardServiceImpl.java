package net.gslab.service.impl;

import javax.annotation.Resource;

import java.util.List;

import net.gslab.dao.BaseDao;
import net.gslab.dao.BoardDao;
import net.gslab.entity.Board;
import net.gslab.service.BoardService;

import org.springframework.stereotype.Service;

@Service(value="boardServiceImpl")
public class BoardServiceImpl extends BaseServiceImpl<Board> implements BoardService{

	private BoardDao boardDao;
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!一定要在service中写上层dao的set
	@Resource(name="boardDaoImpl")
	public void setBoardDao(BoardDao boardDao) {
		this.boardDao = boardDao;
		super.setBaseDao(boardDao);
	}
	
	public List<Board> listBoard()
	{
		List<Board> board=boardDao.find("from Board");
		System.out.println("listBoard() in the UserService"+board);
		return board;
	}
	

	
}
