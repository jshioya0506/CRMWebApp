package jp.co.nexus.crm.form;

/**
 * ログイン情報
 * @author jshioya
 *
 */
public class LoginForm {
	
	/** ユーザID */
	private String userId;
	/** パスワード */
	private String password;
	/** メッセージID */
	private String messsageId;
	/** メッセージ */
	private String message;

	/**
	 * ユーザIDを取得
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * パスワードを取得
	 * @return パスワード
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * パスワードを設定
	 * @param password パスワード
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * メッセージIDを取得
	 * @return メッセージID
	 */
	public String getMesssageId() {
		return messsageId;
	}

	/**
	 * メッセージIDを設定
	 * @param messsageId メッセージID
	 */
	public void setMesssageId(String messsageId) {
		this.messsageId = messsageId;
	}

	/**
	 * メッセージを取得
	 * @return メッセージ
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * メッセージIDを設定
	 * @param message メッセージ
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
