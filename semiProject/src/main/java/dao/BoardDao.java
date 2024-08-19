package dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardFileDto;
import com.spring.board.form.BoardFileForm;
import com.spring.board.form.BoardForm;

@Repository
public class BoardDao {

	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.spring.board.boardMapper";

	public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {		
		return sqlSession.selectList(NAMESPACE + ".getBoardList", boardForm);
	}
	
	public int insertBoard(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoard", boardForm);
	}

	public int deleteBoard(BoardForm boardForm) throws Exception {
		return sqlSession.delete(NAMESPACE + ".deleteBoard", boardForm);
	}

	public int updateBoard(BoardForm boardForm) throws Exception {
		return sqlSession.update(NAMESPACE + ".updateBoard", boardForm);
	}
	
	public int insertBoardReply(BoardForm boardForm) throws Exception {
		return sqlSession.insert(NAMESPACE + ".insertBoardReply", boardForm);
	}
	
