package net.gslab.service;

import java.util.List;

import net.gslab.entity.Board;

public interface BoardService extends BaseService<Board>{
	 List<Board> listBoard();
}
