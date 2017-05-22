package cn.jay.computer.biu;

import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;
import registercfg.Configer;

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
	
//	private static final byte[][] test_code = {{1,1,0,1,0,0,0,1},{0,1,1,0,0,0,1,0},{0,0,0,0,0,0,0,0} };
//	private static int p_i = 0;
	/**
	 * get next instruction(one byte), and auto update IP
	 * @return
	 */
	public static final byte[] getInstruction() {
		long address = generateIPAddress();
		byte[] low = Memoryer.read(address, false);
		return low;
//		return test_code[p_i++];
	}
	
	private static final long generateIPAddress() {
		long address = 0;
		long _cs = 0;
		long _ip = IP.getIPLongValue();
		byte[] cs = CS.getCS();
		for(int i = 0;i < cs.length;i++) {
			_cs |= cs[i] << i;
		}
		address = _cs * Configer.getInstructionLength() + _ip;
		IP.updateIP();
		return address;
	}
	
}
