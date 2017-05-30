package cn.jay.computer.memory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import cn.jay.computer.io_interface.IOInterface;
import cn.jay.computer.tcp.Client;

public class MemoryManager extends Client implements IOInterface {
	Memory memory = null;
	
	public MemoryManager(long size) {
		memory = new Memory(size);
	}
	
	
	public void test(String path) throws Exception {
		InputStream is = new FileInputStream(new File(path));
		int b = is.read();
		int i = 0;
		for(;b != -1;i++) {
			memory.write(i, (byte) b);
			b = is.read();
		}
		System.out.println("Memory Size = " + i + " bytes.");
		is.close();
	}
	
	public boolean write(long address, byte[] value) {
		try {
			memory.write(address, value);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public byte[] read(long address) {
		try {
			return memory.read(address);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void doJob() {
		//TODO
	}
	
}
