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

public class SUB extends Execution {
	public SUB(String opcode, String operand, String describle, int index) {
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

			boolean D = getOperand("D").equals("1");
			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			byte[] a1 = null;
			byte[] a2 = null;
			if (addr == null) {
				a2 = RegisterMgr.getDATA(RM, W);
			} else {
				a2 = Memoryer.read(addr, env.getDATA(), W);
			}
			a1 = RegisterMgr.getDATA(REG, W);

			if (D) {
				if (W) {
					LongALU.sub16(a1, a2);
				} else {
					LongALU.sub8(a1, a2);
				}
				RegisterMgr.setDATA(REG, W, a1);
			} else {
				if (W) {
					LongALU.sub16(a2, a1);
				} else {
					LongALU.sub8(a2, a1);
				}
				if(addr == null) {
					RegisterMgr.setDATA(RM, W, a2);
				} else{
					Memoryer.write(addr, env.getDATA(), a2, W);
				}
			}

			break;
		}
		case 1: {
			BaseRegister env = Environment.getDataSegment();

			BIU.getInstruction();

			@SuppressWarnings("unused")
			boolean S = getOperand("D").equals("1");
			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);
			byte[] a1 = null;
			byte[] a2 = null;

			if (addr == null) {
				a1 = RegisterMgr.getDATA(RM, W);
				if (W) {
					a2 = arrayConcat(BIU.getInstruction(), BIU.getInstruction());
					LongALU.sub16(a1, a2);
				} else {
					a2 = BIU.getInstruction();
					LongALU.sub8(a1, a2);
				}
				RegisterMgr.setDATA(RM, W, a1);
			} else {
				a1 = Memoryer.read(addr, env.getDATA(), W);
				if (W) {
					a2 = arrayConcat(BIU.getInstruction(), BIU.getInstruction());
					LongALU.sub16(a1, a2);
				} else {
					a2 = BIU.getInstruction();
					LongALU.sub8(a1, a2);
				}
				Memoryer.write(addr, env.getDATA(), a1, W);
			}
			break;
		}
		case 2: {
			boolean W = getOperand("W").equals("1");

			byte[] a1 = null;
			byte[] a2 = null;

			if (W) {
				a1 = AX.getAX();
				a2 = arrayConcat(BIU.getInstruction(), BIU.getInstruction());
				LongALU.sub16(a1, a2);
				AX.setAX(a1);
			} else {
				a1 = AX.getAL();
				a2 = BIU.getInstruction();
				LongALU.sub8(a1, a2);
				AX.setAL(a1);
			}
			break;
		}
		}
	}

}
