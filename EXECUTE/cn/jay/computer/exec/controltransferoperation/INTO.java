package cn.jay.computer.exec.controltransferoperation;

import cn.jay.computer.exec.Execution;

public class INTO extends Execution {

	private static final byte[] TWO16 = new byte[16];
	
	static {
		TWO16[1] = 1;
	}
	
	public INTO(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			int type = 4;
			//TODO
			
			System.out.println("Interrupt, TYPE = " + type);
			break;
		}
		}
	}

}
