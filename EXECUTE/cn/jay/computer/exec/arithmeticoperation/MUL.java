package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.DX;

public class MUL extends Execution {
	public MUL(String opcode, String operand, String describle, int index) {
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

			byte[] a1 = null;
			byte[] a2 = null;
			if (addr == null) {
				a2 = RegisterMgr.getDATA(RM, W);
			} else {
				a2 = Memoryer.read(addr, env.getDATA(), W);
			}
			if (W) {
				a1 = AX.getAX();
				a1 = LongALU.mul(a1, a2);
				byte[] high = new byte[16];
				byte[] low = new byte[16];
				arraySplit(high, low, a1);
				AX.setAX(low);
				DX.setDX(high);
			} else {
				a1 = AX.getAL();
				a1 = LongALU.mul(a1, a2);
				AX.setAX(a1);
			}
			break;
		}
		}
	}

}
