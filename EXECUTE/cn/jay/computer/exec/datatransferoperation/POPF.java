package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.segmentregister.SS;

public class POPF extends Execution {

	public POPF(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			try {
				FLAGS.setFLAGS(Memoryer.read(SP.getSP(), SS.getSS(), true));
			} catch (Exception e) {
			}
			
			SP.add2();
			
			break;
		}
		}
	}

}
