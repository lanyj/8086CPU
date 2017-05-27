package cn.jay.computer.driver;

public interface DriverInterface {
	public byte[] read(byte[] port);
	
	public boolean write(byte[] port, byte[] value);
}
