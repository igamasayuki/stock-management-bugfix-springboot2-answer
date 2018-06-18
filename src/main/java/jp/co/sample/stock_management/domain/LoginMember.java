package jp.co.sample.stock_management.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * 利用者のログイン情報を格納するエンティティ.
 * 
 * @author igamasayuki
 *
 */
public class LoginMember  extends User{

	private static final long serialVersionUID = 1L;
	/** メンバー情報 */
	private final Member member;
	
	/**
	 * 通常のメンバー情報に加え、認可用ロールを設定する。
	 * 
	 * @param Member　メンバー情報
	 * @param authorityList 権限情報が入ったリスト
	 */
	public LoginMember(Member member, Collection<GrantedAuthority> authorityList) {
		super(member.getMailAddress(), member.getPassword(), authorityList);
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
}
