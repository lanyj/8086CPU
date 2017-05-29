package cn.jay.computer.exec.processorcontroloperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.flagregister.FLAGS;

public class CLD extends Execution {

	private static final byte[] TWO16 = new byte[16];
	
	static {
		TWO16[1] = 1;
	}
	
	public CLD(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			FLAGS.setFLAGS(FLAGS.DF, false);
			break;
		}
		}
	}

}
