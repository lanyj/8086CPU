package cn.jay.computer.memory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import cn.jay.computer.io_interface.IOInterface;
import cn.jay.computer.tcp.ModelClient;

public class MemoryManager extends ModelClient implements IOInterface {
	Memory memory = null;
	
	public MemoryManager(String modelName, long size) {
		super(modelName);
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
	
	public boolean write(long address, byte[] value) throws Exception {
		memory.write(address, value);
		return true;
	}

	public byte[] read(long address) throws Exception {
		return memory.read(address);
	}

	public void doJob() {
		//TODO
	}

}
