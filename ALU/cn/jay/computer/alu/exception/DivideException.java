package cn.jay.computer.alu.exception;

public class DivideException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8983337088607989107L;
	private String exp = null;
	public DivideException(String exp) {
		this.exp = exp;
	}
	
	@Override
	public String getMessage() {
		return "Divide exception:[" + exp + "]";
	}
}
