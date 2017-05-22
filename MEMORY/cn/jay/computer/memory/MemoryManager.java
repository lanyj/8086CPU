package cn.jay.computer.memory;

import cn.jay.computer.io_interface.IOInterface;
import cn.jay.computer.tcp.Client;

public class MemoryManager extends Client implements IOInterface {
	Memory memory = null;
	
	public MemoryManager(long size) {
		memory = new Memory(size);
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
