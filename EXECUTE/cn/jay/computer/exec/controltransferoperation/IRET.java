package cn.jay.computer.exec.controltransferoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.datatransferoperation.POP;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;

public class IRET extends Execution {

	private static final byte[] TWO16 = new byte[16];

	static {
		TWO16[1] = 1;
	}

	public IRET(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			IP.setIP(POP.pop());
			CS.setCS(POP.pop());
			FLAGS.setFLAGS(POP.pop());
			break;
		}
		}
	}

}
