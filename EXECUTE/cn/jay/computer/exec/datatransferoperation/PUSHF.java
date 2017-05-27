package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.segmentregister.SS;

public class PUSHF extends Execution {

	public PUSHF(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			SP.sub2();
			Memoryer.write(SP.getSP(), SS.getSS(), FLAGS.getFLAGS(), true);
			break;
		}
		}
	}

}
