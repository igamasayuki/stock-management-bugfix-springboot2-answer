package jp.co.sample.stock_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.sample.stock_management.domain.Member;

/**
 * ログイン関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/")
@SessionAttributes( types = {Member.class})
public class LoginController {

//	@Autowired
//	private MemberService memberService;

//※ログイン認証フィルターが機能するためいらなくなりました。
//	/**
//	 * フォームを初期化します.
//	 * @return フォーム
//	 */
//	@ModelAttribute
//	public LoginForm setUpForm() {
//		return new LoginForm();
//	}

	/**
	 * ログイン画面を表示します.
	 * @return ログイン画面
	 */
	@RequestMapping("/")
	public String index(/*LoginForm form, BindingResult result, */Model model,
			@RequestParam(required = false) String error) {
		System.err.println("login error:" + error);
		if (error != null) {
			System.err.println("member: login failed");
			//result.addError(new ObjectError("loginError", "メールアドレスまたはパスワードが不正です。"));
			model.addAttribute("loginError", "メールアドレスまたはパスワードが不正です。");
		}
		return "loginForm";
	}
	
//	/**
//	 * ログイン処理を行います.
//	 * @param form　フォーム
//	 * @param result　リザルト
//	 * @param model　モデル
//	 * @return　ログイン成功時：書籍リスト画面
//	 */
//	@RequestMapping(value = "/login")
//	public String login(@Validated LoginForm form,
//			BindingResult result, Model model) {
//		if (result.hasErrors()){
//			return index();
//		}
//		String mailAddress = form.getMailAddress();
//		String password =form.getPassword();
//		Member member = memberService.findOneByMailAddressAndPassword(mailAddress, password);
//		if (member == null) {
//			ObjectError error = new ObjectError("loginerror", "メールアドレスまたはパスワードが違います。");
//            result.addError(error);
//			return index();
//		}
//		model.addAttribute("member", member);
//		return "redirect:/book/list";
//	}
}
