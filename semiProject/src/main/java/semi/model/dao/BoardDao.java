package semi.model.dao;
/*
import java.util.List;

import org.springframework.stereotype.Repository;

import semi.Form.BoardForm;
import semi.model.BoardDto;

@Repository
public class BoardDao {

	@Resource(name = "sqlSession")
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.spring.board.boardMapper";

	public int getBoardCnt(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardCnt", boardForm);
	}

	public List<BoardDto> getBoardList(BoardForm boardForm) throws Exception {		
		return sqlSession.selectList(NAMESPACE + ".getBoardList", boardForm);
	}

	public int getBoardReRef(BoardForm boardForm) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardReRef", boardForm);
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
	
}
*/