package jp.co.sample.stock_management.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sample.stock_management.domain.Book;
import jp.co.sample.stock_management.form.BookForm;
import jp.co.sample.stock_management.form.RegistBookForm;
import jp.co.sample.stock_management.service.BookService;

/**
 * 書籍関連処理を行うコントローラー.
 * 
 * @author igamasayuki
 * 
 */
@Controller
@RequestMapping("/book")
@Transactional
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private ServletContext application;
	/**
	 * フォームを初期化します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public BookForm setUpBookForm() {
		return new BookForm();
	}

	@ModelAttribute
	public RegistBookForm setUpRegistBookForm() {
		return new RegistBookForm();
	}

	/**
	 * 書籍リスト情報を取得し書籍リスト画面を表示します.
	 * 
	 * @param model
	 *            モデル
	 * @return 書籍リスト表示画面
	 */
	@RequestMapping(value = "/list")
	public String list(Model model) {
		List<Book> bookList = bookService.findAll();
		model.addAttribute("bookList", bookList);
		return "book/list";
	}

	/**
	 * 書籍詳細情報を取得し書籍詳細画面を表示します.
	 * 
	 * @param id
	 *            書籍ID
	 * @param model
	 *            モデル
	 * @return 書籍詳細画面
	 */
	@RequestMapping(value = "show/{bookId}")
	public String show(@PathVariable("bookId") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "book/show";
	}

	/**
	 * 書籍更新を行います.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト情報
	 * @param model
	 *            モデル
	 * @return 書籍リスト画面
	 */
	@RequestMapping(value = "update")
	public String update(@Validated BookForm form, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return show(form.getId(), model);
		}
		Book book = bookService.findOne(form.getId());
		book.setStock(form.getStock());
		bookService.update(book);
		return list(model);
	}

	/**
	 * 書籍情報登録画面を表示します.
	 * 
	 * @return 書籍情報登録画面
	 */
	@RequestMapping(value = "form")
	public String form(Model model) {
		return "/book/form";
	}

	/**
	 * 書籍情報登録を行います.
	 * 
	 * @param form
	 *            フォーム
	 * @param result
	 *            リザルト情報
	 * @param model
	 *            モデル
	 * @return 書籍リスト画面
	 */
	@RequestMapping(value = "regist")
	synchronized public String regist(@Validated RegistBookForm form, BindingResult result, Model model) {
		
		if (result.hasErrors()) {
			return form(model);
		}

		Book book = new Book();
		BeanUtils.copyProperties(form, book);
		
		/** IDをbookに格納する */
		book.setId(bookService.createId());
		
		/** 入力フォームで与えられた、Stirng型のsaledateをDate型に変換する */
		Date date = bookService.convertStringToDate(form.getSaledate(), "yyyy-MM-dd");
		book.setSaledate(date);
		
		/** 画像の処理を行う */
		MultipartFile imageFile = form.getImageFile();
		if(imageFile.isEmpty()){
			model.addAttribute("imageError","画像は必須です");
			return form(model);
		}
		// ファイルの名前を取得
		String filename = imageFile.getOriginalFilename();
		// 画像を保存する
		try {
			String destPath = application.getRealPath("/img/" + filename);
			File destFile = new File(destPath);
			imageFile.transferTo(destFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return form(model);
		} catch (IOException e) {
			e.printStackTrace();
			return form(model);
		}
		// 画像のパスをセットする
		book.setImage(filename);

		book = bookService.insert(book);
		return "redirect:list";
	}
}
