package cn.jay.computer.arrayutils;

public class CopyArrayException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5583503826553068906L;
	private String exp = null;
	public CopyArrayException(String exp) {
		this.exp = exp;
	}
	@Override
	public String getMessage() {
		return "When copying array, an exception was throwed:[" + exp + "]";
	}
}
