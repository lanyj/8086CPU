package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.BX;

public class XLAT extends Execution {

	public XLAT(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			BaseRegister env = Environment.getDataSegment();

			byte[] addr = BX.getBX();
			addr = add16(addr, AX.getAL());
			AX.setAL(Memoryer.read(addr, env.getDATA(), false));
			break;
		}
		}
	}
}
