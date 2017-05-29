package cn.jay.computer.memory;

import java.io.Serializable;
import java.util.ArrayList;

public class Memory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1543669920668591215L;
	private ArrayList<byte[]> memory = new ArrayList<byte[]>();
	private long size = 0;

	public Memory() {
	}

	public Memory(long size) {
		this.size = size;
		init();
	}

	private void init() {
		for (int i = 0; i < 1.0 * size / Integer.MAX_VALUE - 1; i++) {
			memory.add(new byte[Integer.MAX_VALUE]);
		}
		memory.add(new byte[(int) (size % Integer.MAX_VALUE)]);
	}

	public byte[] getByte(long address) throws Exception {
		if (size < address) {
			throw new Exception("Address lager than memory size." + "[" + address + " > " + size + "]");
		}
		byte b = memory.get((int) (address / Integer.MAX_VALUE))[(int) (address % Integer.MAX_VALUE)];
		return byteToArray(b);
	}

	public byte[] read(long address) throws Exception {
		return getByte(address);
	}
	
	public void write(long address, byte value) throws Exception {
		if (size < address) {
			throw new Exception("Address lager than memory size." + "[" + address + " > " + size + "]");
		}
		memory.get((int) (address / Integer.MAX_VALUE))[(int) (address % Integer.MAX_VALUE)] = value;
	}
	
	public void write(long address, byte[] value) throws Exception {
		if (size < address) {
			throw new Exception("Address lager than memory size." + "[" + address + " > " + size + "]");
		}
		memory.get((int) (address / Integer.MAX_VALUE))[(int) (address % Integer.MAX_VALUE)] = arrayToByte(value);
	}

	public long getSize() {
		return size;
	}

	public static byte[] byteToArray(byte b) {
		byte[] buf = new byte[8];
		for (int i = 0; i < 8; i++) {
			buf[i] = (byte) ((b >> i) & 1);
		}
		return buf;
	}
	public static byte arrayToByte(byte buf[]) {
		byte b = 0;
		byte k = 1;
		for (int i = 0; i < 8; i++) {
			k = (byte) (buf[i] << i);
			b |= k;
		}
		return b;
	}
}
