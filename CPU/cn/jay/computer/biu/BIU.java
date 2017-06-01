package cn.jay.computer.biu;

import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;

public class BIU {
	/*private static final Queue<byte[]> INSTRUCTIONS = new ArrayDeque<byte[]>(6);
	
	public static final int addInstruction(byte[] data) {
		INSTRUCTIONS.add(data);
		return INSTRUCTIONS.size();
	}
	
	public static final byte[] getInstruction() {
		byte[] b = INSTRUCTIONS.poll();
		return b;
	}
	
	public static final void destoryInstructionQueue() {
		INSTRUCTIONS.clear();
	}
	*/
	
	/**
	 * get next instruction(one byte), and auto update IP
	 * @return
	 * @throws Exception 
	 */
	public static final byte[] getInstruction() throws Exception {
		byte[] low = Memoryer.read(IP.getIP(), CS.getCS(), false);
		IP.updateIP();
		return low;
	}
	/**
	 * get next instruction(one byte), without auto update IP
	 * @return
	 * @throws Exception 
	 */
	public static final byte[] peekInstruction() throws Exception {
		byte[] low = Memoryer.read(IP.getIP(), CS.getCS(), false);
		return low;
	}
	
}
