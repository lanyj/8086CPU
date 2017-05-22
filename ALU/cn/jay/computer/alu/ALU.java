package cn.jay.computer.alu;

public interface ALU {
	public static int INT_SIZE = 64;
	public static int DOUBLE_SIZE = 64;
	
	public void add(byte[] des, byte[] src);

	public void sub(byte[] des, byte[] src);

	public void multi(byte[] des, byte[] src);

	public void divide(byte[] des, byte[] src);

	/**
	 * Compare a with b,and write result to des.If a > b,des = 1;a == b,des =
	 * 0;a < b,des = -1
	 * 
	 * @param des
	 * @param a
	 * @param b
	 */
	public void compare(byte[] des, byte[] a, byte[] b);
}
