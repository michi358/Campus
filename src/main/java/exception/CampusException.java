package exception;

public class CampusException extends Exception {
	//エラーメッセージを受け取るコンストラクタ
		public CampusException(String message) {
			super(message);
		}
}
