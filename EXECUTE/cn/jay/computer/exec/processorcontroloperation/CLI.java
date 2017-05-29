package cn.jay.computer.exec.processorcontroloperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.flagregister.FLAGS;

public class CLI extends Execution {

	private static final byte[] TWO16 = new byte[16];
	
	static {
		TWO16[1] = 1;
	}
	
	public CLI(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			FLAGS.setFLAGS(FLAGS.IF, false);
			break;
		}
		}
	}

}
