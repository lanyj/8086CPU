package cn.jay.computer.exec.logicaloperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;

public class NOT extends Execution {
	public NOT(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BaseRegister env = Environment.getDataSegment();

			BIU.getInstruction();

			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			byte[] a2 = null;
			if (addr == null) {
				try {
					a2 = RegisterMgr.getDATA(RM, W);
					if (W) {
						LongALU.not16(a2);
					} else {
						LongALU.not8(a2);
					}
					RegisterMgr.setDATA(RM, W, a2);
				} catch (Exception e) {
				}
			} else {
				a2 = Memoryer.read(addr, env.getDATA(), W);

				try {
					if (W) {
						LongALU.not16(a2);
					} else {
						LongALU.not8(a2);
					}
				} catch (Exception e) {
				}
				Memoryer.write(addr, env.getDATA(), a2, W);
			}

			break;
		}
		}
	}

}