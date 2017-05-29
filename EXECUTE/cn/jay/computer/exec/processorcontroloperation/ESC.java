package cn.jay.computer.exec.processorcontroloperation;

import cn.jay.computer.exec.Execution;

public class ESC extends Execution {

	private static final byte[] TWO16 = new byte[16];
	
	static {
		TWO16[1] = 1;
	}
	
	public ESC(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			//TODO
			System.out.println("ESC");
			break;
		}
		}
	}

}
