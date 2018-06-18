package jp.co.sample.stock_management.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.stock_management.domain.Member;
import jp.co.sample.stock_management.form.MemberForm;
import jp.co.sample.stock_management.service.MemberService;

/**
 * メンバー関連処理を行うコントローラー.
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/member")
@Transactional
public class MemberController {

	@Autowired
	private MemberService memberService;

	/**
	 * フォームを初期化します.
	 * @return フォーム
	 */
	@ModelAttribute
	public MemberForm setUpForm() {
		return new MemberForm();
	}

	/**
	 * メンバー情報登録画面を表示します.
	 * @return メンバー情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form(Model model) {
		return "/member/form";
	}
	
	/**
	 * メンバー情報を登録します.
	 * @param form フォーム
	 * @param result リザルト
	 * @param model モデル
	 * @return ログイン画面
	 */
	@RequestMapping(value = "create")
	public String create(@Validated MemberForm form,
							BindingResult result,
							Model model) {

		// パスワード確認
		if(!form.getPassword().equals(form.getValidationPassword())){
			result.rejectValue("validationPassword", "", "パスワードを確認してください");
		}

		// メールアドレスが重複している場合の処理
		Member valMember = memberService.findByMailAddress(form.getMailAddress());
		if(valMember != null){
			result.rejectValue("mailAddress", "", "すでにメールアドレスが登録されています");
		}
		if(result.hasErrors()){
			return form(model);
		}

		// パスワードを暗号化し、formに設定
		form.setPassword(memberService.encodePassword(form.getPassword()));
		
		// フォームの内容をエンティティに格納
		Member member = new Member();
		BeanUtils.copyProperties(form, member);
		member = memberService.save(member);
		
		return "redirect:/";
	}
	
}
