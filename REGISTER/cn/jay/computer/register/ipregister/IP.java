package cn.jay.computer.register.ipregister;

import cfg.Configer;

public class IP {
	public static long IP = 0;
	public static int INSTRUCTION_LENGTH = Configer.getInstructionLength();
	
	public final static void setIP(byte[] data) {
//		long old = IP;
		
		IP = 0;
		for(int i = 0;i < data.length;i++) {
			IP |= data[i] << i;
		}
		
//		System.out.println("\nSet IP = " + old + " -> " + IP + "\n");
	}
	
	public final static void setIP(long value) {
//		System.out.println("\nSet IP = " + IP + " -> " + value + "\n");

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
