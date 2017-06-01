package cn.jay.computer.exec.controltransferoperation;

import java.util.Arrays;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;

public class INT extends Execution {

	private static final byte[] TWO16 = new byte[16];
	
	static {
		TWO16[1] = 1;
	}
	
	public INT(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] val = BIU.getInstruction();
			int type = (int) byteArrayToLong(val, false, 8);
			//TODO
			
			System.out.println("\nInterrupt, TYPE = " + type +  " - - - " + Arrays.toString(CS.getCS()) + ":" + IP.getIPLongValue());
			break;
		}
		case 1:{
			int type = 3;
			//TODO
			
			System.out.println("\nInterrupt, TYPE = " + type +  " - - - " + Arrays.toString(CS.getCS()) + ":" + IP.getIPLongValue());
			break;
		}
		}
	}

}
