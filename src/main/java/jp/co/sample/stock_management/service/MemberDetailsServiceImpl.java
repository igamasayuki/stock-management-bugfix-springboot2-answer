package jp.co.sample.stock_management.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.sample.stock_management.domain.LoginMember;
import jp.co.sample.stock_management.domain.Member;
import jp.co.sample.stock_management.repository.MemberRepository;

/**
 * ログイン後のメンバー情報に権限情報を付与するサービスクラス.
 * 
 * @author igamasayuki
 *
 */
@Service
public class MemberDetailsServiceImpl implements UserDetailsService {
	/** DBから情報を得るためのリポジトリ */
	@Autowired
	private MemberRepository memberRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String) DBから検索をし、ログイン情報を構成して返す。
	 */
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		Member member = memberRepository.findByMailAddress(email);
		if (member == null) {
			throw new UsernameNotFoundException("そのEmailは登録されていません。");
		}
		// 権限付与の例
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_MEMBER")); // ユーザ権限付与
//		if(member.isAdmin()) {
//			authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 管理者権限付与
//		}
		return new LoginMember(member,authorityList);
	}
}
