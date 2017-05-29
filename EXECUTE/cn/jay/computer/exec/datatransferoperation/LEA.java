package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.register.baseregister.RegisterMgr;

public class LEA extends Execution {

	public LEA(String opcode, String operand, String describle, int index) {
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

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);

			if(addr == null) {
				RegisterMgr.setDATA(REG, true, RegisterMgr.getDATA(RM, true));
			} else {
				RegisterMgr.setDATA(REG, true, addr);
			}

			break;
		}
		}
	}

}
