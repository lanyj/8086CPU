package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;

public class INC extends Execution {

	private static final byte[] ONE16 = new byte[16];
	private static final byte[] ONE8 = new byte[8];

	static {
		ONE16[0] = 1;
		ONE8[0] = 1;
	}

	public INC(String opcode, String operand, String describle, int index) {
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

			try {
				if (addr == null) {
					byte[] a = RegisterMgr.getDATA(RM, W);

					if (W) {
						LongALU.add16(a, ONE16);
					} else {
						LongALU.add8(a, ONE8);
					}
					RegisterMgr.setDATA(RM, W, a);
				} else {
					byte[] a = null;
					if (W) {
						a = Memoryer.read(addr, env.getDATA(), W);
						LongALU.add16(a, ONE16);
					} else {
						LongALU.add8(a, ONE8);
					}
					Memoryer.write(addr, env.getDATA(), a, W);
				}
			} catch (Exception e) {
			}

			break;
		}
		case 1: {
			String REG = getOperand("REG");
			try {
				byte[] a = RegisterMgr.getDATA(REG, true);
				LongALU.add16(a, ONE16);
				RegisterMgr.setDATA(REG, true, a);
			} catch (Exception e1) {
			}
			break;
		}
		}
	}

}