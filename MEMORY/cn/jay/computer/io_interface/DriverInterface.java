package cn.jay.computer.io_interface;

public interface DriverInterface {
	public byte[] read(byte[] port) throws Exception;
	
	public boolean write(byte[] port, byte[] value) throws Exception;
}
