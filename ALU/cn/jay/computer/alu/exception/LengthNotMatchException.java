package cn.jay.computer.alu.exception;

public class LengthNotMatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125427709674567040L;
	private String exp = null;
	
	public LengthNotMatchException(String exp) {
		this.exp = exp;
	}
	
	@Override
	public String getMessage() {
		return "Length not match:[" + exp + "]";
	}
}
