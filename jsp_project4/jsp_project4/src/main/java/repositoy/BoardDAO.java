package repositoy;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	List<BoardVO> paglist(PagingVO pgvo);

	int selectTotal(PagingVO pgvo);

	BoardVO selectOne(BoardVO bvo);

	BoardVO updateOne(BoardVO bvo);

	BoardVO selectOnePlus(BoardVO bvo);

	int insertOne(BoardVO bvo);

	int deleteOne(BoardVO bvo);

}
