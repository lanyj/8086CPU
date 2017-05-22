package cn.jay.computer.io_interface;

public interface IOInterface {
	/**
	 * Write memory,and at first of all<code>value = (value != 0)</code>
	 * 
	 * @param address
	 *            Address of byte
	 * @param value
	 *            Value to write
	 */
	public boolean write(long address, byte[] value);

	/**
	 * Read memory,return 0 or 1 or -1
	 * 
	 * @param address
	 *            Address of byte
	 * @return Value,if no such address,return -1
	 */
	public byte[] read(long address);

}
