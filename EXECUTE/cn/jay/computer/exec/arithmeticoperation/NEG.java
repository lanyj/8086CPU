package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;

public class NEG extends Execution {

	private static final byte[] ZERO16 = new byte[16];
	private static final byte[] ZERO8 = new byte[8];

	public NEG(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
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

			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			if (addr == null) {
				byte[] ret = null;
				byte[] a = RegisterMgr.getDATA(RM, W);

				if (W) {
					ret = ZERO16.clone();
					LongALU.sub(ret, a);
				} else {
					ret = ZERO8.clone();
					LongALU.sub(ret, a);
				}
				RegisterMgr.setDATA(RM, W, ret);
			} else {
				byte[] ret = null;
				byte[] a = null;
				if (W) {
					a = Memoryer.read(addr, env.getDATA(), W);
					ret = ZERO16.clone();
					LongALU.sub(ret, a);
				} else {
					ret = ZERO8.clone();
					LongALU.sub(ret, a);
				}
				Memoryer.write(addr, env.getDATA(), ret, W);
			}

			break;
		}
		}
	}

}
