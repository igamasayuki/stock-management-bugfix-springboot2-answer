package jp.co.sample.stock_management.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.stock_management.domain.Member;

/**
 * membersテーブル操作用のリポジトリクラス.
 * 
 * @author igamasayuki
 */
@Repository
public class MemberRepository {

	/**
	 * ResultSetオブジェクトからMemberオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<Member> MEMBER_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String mailAddress = rs.getString("mail_address");
		String password = rs.getString("password");
		return new Member(id, name, mailAddress, password);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

//	/**
//	 * メールアドレスとパスワードからメンバーを取得.
//	 * 
//	 * @param mailAddress
//	 *            メールアドレス
//	 * @param password
//	 *            パスワード
//	 * @return メンバー情報.メンバーが存在しない場合はnull.
//	 */
//	public Member findByMailAddressAndPassword(String mailAddress, String password) {
//		Member member = null;
//		String sql = "SELECT id, name, mail_address, password" + " FROM members"
//				+ " WHERE mail_address=:mailAddress AND password=:password";
//
//		try {
//			SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress)
//					.addValue("password", password);
//			member = jdbcTemplate.queryForObject(sql, param, MEMBER_ROW_MAPPER);
//
//			return member;
//		} catch (DataAccessException e) {
//			return null;
//		}
//	}

	/**
	 * メンバー情報を保存 または 更新する.
	 * 
	 * @param member
	 *            保存または更新するメンバー情報
	 * @return 保存または更新されたメンバー情報
	 */
	public Member save(Member member) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(member);

		if (member.getId() == null) {
			jdbcTemplate.update("INSERT INTO members(name,mail_address,password) values(:name,:mailAddress,:password)",
					param);
		} else {
			jdbcTemplate.update(
					"UPDATE members SET name=:name,mail_address=:mailAddress,password=:password WHERE id=:id", param);
		}
		return member;
	}

	/**
	 * 
	 * メールアドレスからメンバーを検索する
	 * 
	 * @param mailAddress
	 *            メールアドレス
	 * @return Memberオブジェクト.該当するデータがなければnullを返す
	 */
	public Member findByMailAddress(String mailAddress) {
		String sql = "SELECT id, name, mail_address, password FROM members WHERE mail_address=:mailAddress";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		
		List<Member> memberList = jdbcTemplate.query(sql, param, MEMBER_ROW_MAPPER);
		if(memberList.size() == 0) {
			return null;
		}
		return memberList.get(0);
		
		//		try {
//			Member member = jdbcTemplate.queryForObject(sql, param, MEMBER_ROW_MAPPER);
//
//			return member;
//		} catch (DataAccessException e) {
//			return null;
//		}
	}
}
