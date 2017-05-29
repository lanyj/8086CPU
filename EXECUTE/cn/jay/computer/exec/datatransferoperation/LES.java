package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.segmentregister.ES;

public class LES extends Execution {
	private static final byte[] TWO = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public LES(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BIU.getInstruction();

			BaseRegister env = Environment.getDataSegment();

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);

			byte[] low = null;
			if(addr == null) {
				low = RegisterMgr.getDATA(RM, true);
			} else {
				low = Memoryer.read(addr, env.getDATA(), true);
			}
			byte[] high = Memoryer.read(add16(addr, TWO), env.getDATA(), true);
			RegisterMgr.setDATA(REG, true, low);
			ES.setES(high);

			break;
		}
		}
	}

}
