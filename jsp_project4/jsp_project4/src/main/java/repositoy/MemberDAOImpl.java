package repositoy;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private String NS = "MemberMapper.";
	private int isOK;

	public MemberDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public MemberVO select_login(MemberVO mvo) {
		log.info("->MemberDAO : select_login");
		return sql.selectOne(NS + "selectLogin", mvo);
	}

	@Override
	public int select_id(MemberVO mvo) {
		log.info("->MemberDAO : select_id");
		return sql.selectOne(NS + "selectId", mvo);
	}

	@Override
	public int insert(MemberVO mvo) {
		log.info("->MemberDAO : insert");
		int count;
		count = sql.selectOne(NS + "selectId", mvo);
		if (count == 0) {
			isOK = sql.insert(NS + "join", mvo);
			if (isOK > 0) {
				sql.commit();
			}
		} else {
			isOK = -1;
		}
		return isOK;
	}

	@Override
	public int update(MemberVO mvo) {
		log.info("->MemberDAO : update");
		isOK = sql.update(NS + "updateOne", mvo);
		if (isOK > 0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public int updateLast_login(String id) {
		log.info("->MemberDAO : updateLast_login");
		isOK=sql.update(NS + "last", id);
		if (isOK > 0) {
			sql.commit();
		}
		return isOK;
	}

	@Override
	public List<MemberVO> selectList() {
		log.info("->MemberDAO : updateLast_login");
		return sql.selectList(NS+"list");
	}

}
