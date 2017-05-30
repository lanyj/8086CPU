package cn.jay.computer.register.ipregister;

import cfg.Configer;

public class IP {
	public static long IP = 0;
	public static int INSTRUCTION_LENGTH = Configer.getInstructionLength();
	
	public final static void setIP(byte[] data) {
		IP = 0;
		for(int i = 0;i < data.length;i++) {
			IP |= data[i] << i;
		}
	}
	
	public final static void setIP(long value) {
		System.out.println("IP = " + IP + " -> " + value);

		IP = value;
	}
	
	public final static byte[] getIP() {
		byte[] b = new byte[INSTRUCTION_LENGTH];
		for(int i = 0;i < b.length;i++) {
			b[i] = (byte) ((IP >> i) & 1);
		}
		return b;
	}
	
	public final static long getIPLongValue() {
		return IP;
	}
	
	public final static void updateIP() {
		IP += 1;
	}
	
}
