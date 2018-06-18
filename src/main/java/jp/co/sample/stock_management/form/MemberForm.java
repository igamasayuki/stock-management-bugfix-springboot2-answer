package jp.co.sample.stock_management.form;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * メンバー関連のリクエストパラメータが入るフォーム.
 * 
 * @author igamasayuki
 *
 */
public class MemberForm {
	/** 名前 */
	@NotEmpty( message = "値を入力してください")
	private String name;
	/** メールアドレス */
	@Email( message = "メールアドレスの形式ではありません" )
	@NotEmpty( message = "値を入力してください")
	private String mailAddress;
	/** パスワード */
	@NotEmpty( message = "値を入力してください")
	private String password;
	/** 確認用パスワード */
	@NotEmpty( message = "値を入力してください")
	private String validationPassword;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMailAddress() {
		return mailAddress;
	}
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getValidationPassword() {
		return validationPassword;
	}
	public void setValidationPassword(String validationPassword) {
		this.validationPassword = validationPassword;
	}
}
