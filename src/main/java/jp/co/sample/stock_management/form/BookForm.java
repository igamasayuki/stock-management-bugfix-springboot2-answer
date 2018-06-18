package jp.co.sample.stock_management.form;

import javax.validation.constraints.NotNull;

/**
 * 書籍関連のリクエストパラメータが入るフォーム.
 * @author igamasayuki
 *
 */
public class BookForm {
	/** id  */
    @NotNull
	private Integer id;
	/** 在庫  */
    @NotNull(message = "値を入力してください")
	private Integer stock;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
}
