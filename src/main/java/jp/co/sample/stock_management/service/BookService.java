package jp.co.sample.stock_management.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.stock_management.domain.Book;
import jp.co.sample.stock_management.repository.BookRepository;

/**
 * 書籍関連サービスクラス.
 * 
 * @author igamasayuki
 *
 */
@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findOne(Integer id) {
		return bookRepository.findOne(id);
	}

	// public Book save(Book book){
	// return bookRepository.save(book);
	// }

	public Book insert(Book book) {
		return bookRepository.insert(book);
	}

	public Book update(Book book) {
		return bookRepository.update(book);
	}

	// public void delete(Integer id){
	// bookRepository.delete(id);
	// }

	/**
	 * String型の日付を与えられたパターンに基づいてDate型に変換する.
	 * 
	 * @param before
	 *            文字列型の日付
	 * @param pattern
	 *            beforeの日付のパターン
	 * @return after Date型の日付.変換できなかった場合はnullを返す
	 */
	public Date convertStringToDate(String before, String pattern) {
		Date after = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			after = sdf.parse(before);
		} catch (ParseException e) {
			System.out.println("日付の形式が異なります");
			return null;
		}
		return after;
	}

	/**
	 * 新規の書籍情報のIDを作る.
	 * 
	 * @return id ID
	 */
	public Integer createId() {
		Integer currentMaxId = bookRepository.getMaxId();
		if (currentMaxId == null) {
			// テーブルにデータがない場合 -> IDを付与する
			currentMaxId = 1;
			return currentMaxId;
		}
		return currentMaxId + 1;
	}
}
