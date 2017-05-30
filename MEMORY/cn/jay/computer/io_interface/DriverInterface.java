package cn.jay.computer.io_interface;

public interface DriverInterface {
	public byte[] read(byte[] port);
	
	public boolean write(byte[] port, byte[] value);
}
