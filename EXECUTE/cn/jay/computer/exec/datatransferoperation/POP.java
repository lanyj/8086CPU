package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.segmentregister.SS;

public class POP extends Execution {

	public POP(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public static byte[] pop() throws Exception {
		byte[] ret = Memoryer.read(SP.getSP(), SS.getSS(), true);
		SP.add2();

		return ret;
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BaseRegister env = Environment.getDataSegment();
			BIU.getInstruction();

			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");

			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);

			if (addr == null) {
				RegisterMgr.setDATA(RM, true, Memoryer.read(SP.getSP(), SS.getSS(), true));
			} else {
				Memoryer.write(addr, env.getDATA(), Memoryer.read(SP.getSP(), SS.getSS(), true), true);
			}

			SP.add2();

			break;
		}
		case 1: {
			String REG = getOperand("REG");

			RegisterMgr.setDATA(REG, true, Memoryer.read(SP.getSP(), SS.getSS(), true));

			SP.add2();

			break;
		}
		case 2: {
			String SEG = getOperand("SEG");

			RegisterMgr.setDATA(SEG, true, Memoryer.read(SP.getSP(), SS.getSS(), true));

			SP.add2();

			break;
		}
		}
	}

}
