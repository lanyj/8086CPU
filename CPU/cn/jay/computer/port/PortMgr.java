package cn.jay.computer.port;

public class PortMgr {
	
	public static Server PORT_SERVER = null;
	
	public static final BasePort PORT_1 = new BasePort();
	public static final BasePort PORT_2 = new BasePort();
	public static final BasePort PORT_3 = new BasePort();
	public static final BasePort PORT_4 = new BasePort();
	public static final BasePort PORT_5 = new BasePort();
	public static final BasePort PORT_6 = new BasePort();
	public static final BasePort PORT_7 = new BasePort();
	public static final BasePort PORT_8 = new BasePort();
	public static final BasePort PORT_9 = new BasePort();
	public static final BasePort PORT_10 = new BasePort();
	public static final BasePort PORT_11 = new BasePort();
	public static final BasePort PORT_12 = new BasePort();
	public static final BasePort PORT_13 = new BasePort();
	public static final BasePort PORT_14 = new BasePort();
	public static final BasePort PORT_15 = new BasePort();
	public static final BasePort PORT_16 = new BasePort();
	public static final BasePort PORT_17 = new BasePort();
	public static final BasePort PORT_18 = new BasePort();
	public static final BasePort PORT_19 = new BasePort();
	public static final BasePort PORT_20 = new BasePort();
	public static final BasePort PORT_21 = new BasePort();
	public static final BasePort PORT_22 = new BasePort();
	public static final BasePort PORT_23 = new BasePort();
	public static final BasePort PORT_24 = new BasePort();
	public static final BasePort PORT_25 = new BasePort();
	public static final BasePort PORT_26 = new BasePort();
	public static final BasePort PORT_27 = new BasePort();
	public static final BasePort PORT_28 = new BasePort();
	public static final BasePort PORT_29 = new BasePort();
	public static final BasePort PORT_30 = new BasePort();
	public static final BasePort PORT_31 = new BasePort();
	public static final BasePort PORT_32 = new BasePort();
	public static final BasePort PORT_33 = new BasePort();
	public static final BasePort PORT_34 = new BasePort();
	public static final BasePort PORT_35 = new BasePort();
	public static final BasePort PORT_36 = new BasePort();
	public static final BasePort PORT_37 = new BasePort();
	public static final BasePort PORT_38 = new BasePort();
	public static final BasePort PORT_39 = new BasePort();
	public static final BasePort PORT_40 = new BasePort();
	
	public static final BasePort[] PORT = new BasePort[40];
	static {
		PORT[0] = PORT_1;
		PORT[1] = PORT_2;
		PORT[2] = PORT_3;
		PORT[3] = PORT_4;
		PORT[4] = PORT_5;
		PORT[5] = PORT_6;
		PORT[6] = PORT_7;
		PORT[7] = PORT_8;
		PORT[8] = PORT_9;
		PORT[9] = PORT_10;
		PORT[10] = PORT_11;
		PORT[11] = PORT_12;
		PORT[12] = PORT_13;
		PORT[13] = PORT_14;
		PORT[14] = PORT_15;
		PORT[15] = PORT_16;
		PORT[16] = PORT_17;
		PORT[17] = PORT_18;
		PORT[18] = PORT_19;
		PORT[19] = PORT_20;
		PORT[20] = PORT_21;
		PORT[21] = PORT_22;
		PORT[22] = PORT_23;
		PORT[23] = PORT_24;
		PORT[24] = PORT_25;
		PORT[25] = PORT_26;
		PORT[26] = PORT_27;
		PORT[27] = PORT_28;
		PORT[28] = PORT_29;
		PORT[29] = PORT_30;
		PORT[30] = PORT_31;
		PORT[31] = PORT_32;
		PORT[32] = PORT_33;
		PORT[33] = PORT_34;
		PORT[34] = PORT_35;
		PORT[35] = PORT_36;
		PORT[36] = PORT_37;
		PORT[37] = PORT_38;
		PORT[38] = PORT_39;
		PORT[39] = PORT_40;
		
		try {
			PORT_SERVER = new Server();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void setValue(byte[] value) {
		synchronized (PORT) {
			for(int i = 0;i < PORT.length;i++) {
				PORT[value[i * 2]].setValue(value[i * 2 + 1]);
			}	
		}
	}
	
	public static void post() {
		synchronized (PORT) {
			PORT_SERVER.post();
		}
	}
	
}
