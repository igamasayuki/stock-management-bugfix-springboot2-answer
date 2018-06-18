package jp.co.sample.stock_management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.sample.stock_management.domain.Member;
import jp.co.sample.stock_management.repository.MemberRepository;

/**
 * メンバー関連サービスクラス.
 * 
 * @author igamasayuki
 *
 */
@Service
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// public List<Member> findAll(){
	// return memberRepository.findAll();
	// }
	//
	// public Member findOne(Integer id) {
	// return memberRepository.findOne(id);
	// }

//	/**
//	 * メールアドレスとパスワードに該当するもの見つける.
//	 * 
//	 * @param mailAddress
//	 *            メールアドレス
//	 * @param password
//	 *            パスワード
//	 * @return Memberエンティティを返す. 該当するものがなければnullを返す
//	 */
//	public Member findOneByMailAddressAndPassword(String mailAddress, String password) {
//		Member member = memberRepository.findByMailAddress(mailAddress);
//		
//		// メールアドレスが登録されていない場合
//		if (member == null) {
//			return null;
//		}
//
//		if (!isMatchPassword(password, member.getPassword())) {
//			return null;
//		}
//		return member;
//	}

	/**
	 * 入力された登録情報を保存する.
	 * 
	 * @param member
	 *            入力された登録情報
	 * @return
	 */
	public Member save(Member member) {
		return memberRepository.save(member);
	}

	public Member findByMailAddress(String mailAddress) {
		return memberRepository.findByMailAddress(mailAddress);
	}

	/**
	 * 暗号化する.
	 * 
	 * @param rowPassword
	 *            暗号化前のパスワード(元のパスワード)
	 * @return 暗号化後のパスワード
	 */
	public String encodePassword(String rowPassword) {
		String encodedPassword = passwordEncoder.encode(rowPassword);
		return encodedPassword;
	}

//	/**
//	 * 入力したパスワードがDBにあるパスワードと一致するかどうか.
//	 * 
//	 * @param rowPassword
//	 *            入力されたパスワード
//	 * @param encodedPassword
//	 *            DB上にある暗号化されたパスワード
//	 * @return 正しければtrue
//	 */
//	public boolean isMatchPassword(String rowPassword, String encodedPassword) {
//		return passwordEncoder.matches(rowPassword, encodedPassword);
//	}

	// public Member update(Member member){
	// return memberRepository.save(member);
	// }
	//
	// public void delete(Integer id){
	// memberRepository.delete(id);
	// }
}
