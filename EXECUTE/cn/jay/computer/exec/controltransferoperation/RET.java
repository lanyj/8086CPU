package cn.jay.computer.exec.controltransferoperation;

import java.util.Arrays;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.datatransferoperation.POP;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;

public class RET extends Execution {

	private static final byte[] TWO16 = new byte[16];

	static {
		TWO16[1] = 1;
	}

	public RET(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		
		System.out.println( "\nRET - - - " + Arrays.toString(CS.getCS()) + ":" + IP.getIPLongValue());
//		System.out.println(this);
		
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			IP.setIP(POP.pop());
			break;
		}
		case 1: {
			IP.setIP(POP.pop());
			byte[] low = BIU.getInstruction();
			byte[] high = BIU.getInstruction();

			byte[] ip = arrayConcat(low, high);
			SP.setSP(add16(SP.getSP(), ip));
			break;
		}
		case 2: {
			IP.setIP(POP.pop());
			CS.setCS(POP.pop());
			break;
		}
		case 3: {
			IP.setIP(POP.pop());
			CS.setCS(POP.pop());
			byte[] low = BIU.getInstruction();
			byte[] high = BIU.getInstruction();

			byte[] ip = arrayConcat(low, high);
			SP.setSP(add16(SP.getSP(), ip));
			break;
		}
		}
	}

}
