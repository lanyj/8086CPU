package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.utilexception.CopyArrayException;

public class LAHF extends Execution {

	public LAHF(String opcode, String operand, String describle, int index) {
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
				AX.setAH(FLAGS.getFLAGS());
			} catch (CopyArrayException e) {
			}
			
			break;
		}
		}
	}

}
