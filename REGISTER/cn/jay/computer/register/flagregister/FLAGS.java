package cn.jay.computer.register.flagregister;

import registercfg.Configer;

public class FLAGS {
	public static final int OF = 11;
	public static final int DF = 10;
	public static final int IF = 9;
	public static final int TF = 8;
	public static final int SF = 7;
	public static final int ZF = 6;
	public static final int AF = 4;
	public static final int PF = 2;
	public static final int CF = 0;
	
	private static boolean ENABLE = true;
	
	public static void setEnable(boolean enb) {
		ENABLE = enb;
	}
	
	/**
	 * 11:OF,10:DF,9:IF,8:TF,7:SF,6:ZF,4:AF,2:PF,0:CF
	 * 
	 */
	public static final byte[] FLAGS = new byte[Configer.getRegisterSize()];
	
	public static boolean getFLAGS(int index) {
		return FLAGS[index] > 0;
	}

	public static void setFLAGS(int index,boolean value) {
		if(ENABLE)
			FLAGS[index] = (byte) (value ? 1 : 0);
	}
	
	public static String getStateString() {
		String state = "OF = " + getFLAGS(OF) + ", ";
		state += "DF = " + getFLAGS(DF) + ", ";
		state += "IF = " + getFLAGS(IF) + ", ";
		state += "TF = " + getFLAGS(TF) + ", ";
		state += "SF = " + getFLAGS(SF) + ", ";
		state += "ZF = " + getFLAGS(ZF) + ", ";
		state += "AF = " + getFLAGS(AF) + ", ";
		state += "PF = " + getFLAGS(PF) + ", ";
		state += "CF = " + getFLAGS(CF);
		return state;
	}
	
}
