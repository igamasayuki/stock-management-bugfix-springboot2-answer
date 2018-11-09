package jp.co.sample.stock_management.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.stock_management.domain.Book;

/**
 * booksテーブル操作用のリポジトリクラス.
 * 
 * @author igamasayuki
 */
@Repository
public class BookRepository {
	/**
	 * ResultSetオブジェクトからBookオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String author = rs.getString("author");
		String publisher = rs.getString("publisher");
		Integer price = rs.getInt("price");
		String isbncode = rs.getString("isbncode");
		Date saledate = rs.getDate("saledate");
		String explanation = rs.getString("explanation");
		String image = rs.getString("image");
		Integer stock = rs.getInt("stock");
		return new Book(id, name, author, publisher, price, isbncode, saledate, explanation, image, stock);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Book> findAll() {
		List<Book> books = jdbcTemplate.query(
				"SELECT id,name,author,publisher,price,isbncode,saledate,explanation,image,stock FROM books ORDER BY name ASC;",
				BOOK_ROW_MAPPER);
		return books;
	}

	public Book findOne(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Book book = jdbcTemplate.queryForObject(
				"SELECT id,name,author,publisher,price,isbncode,saledate,explanation,image,stock FROM books WHERE id=:id",
				param, BOOK_ROW_MAPPER);
		return book;
	}

	public Book update(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);
		if (book.getId() == null) {
			throw new NullPointerException();
		}
		jdbcTemplate.update("UPDATE books SET stock=:stock WHERE id=:id", param);
		return book;
	}

	/**
	 * 新規の書籍情報を登録する.
	 * 
	 * @return 登録したBookエンティティ
	 */
	public Book insert(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);

		if (book.getId() == null) {
			throw new NullPointerException();
		}

		jdbcTemplate.update(
				"INSERT INTO books (id, name, author, publisher, price, isbncode, saledate, explanation, image, stock)"
						+ " VALUES (:id, :name, :author, :publisher, :price, :isbncode, :saledate, :explanation, :image, :stock)",
				param);
		return book;
	}
	
	/**
	 * booksテーブルの中で一番大きいIDを取得する.
	 * 
	 * @return テーブル内で一番値が大きいID.データがない場合はnull
	 */
	synchronized public Integer getMaxId() {
		try {
			Integer maxId = jdbcTemplate.queryForObject("SELECT MAX(id) FROM books;", new MapSqlParameterSource(),
					Integer.class);
			return maxId;
		} catch (DataAccessException e) {
			// データが存在しない場合
			return null;
		}
	}
}
