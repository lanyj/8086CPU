package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;

public class SAHF extends Execution {

	public SAHF(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			FLAGS.setFLAGS(AX.getAX());
			break;
		}
		}
	}

}
