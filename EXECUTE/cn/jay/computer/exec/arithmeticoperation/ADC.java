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
import cn.jay.computer.register.flagregister.FLAGS;

public class ADC extends Execution {

	private static final byte[] ONE16 = new byte[16];
	private static final byte[] ONE8 = new byte[8];

	static {
		ONE16[0] = 1;
		ONE8[0] = 1;
	}

	public ADC(String opcode, String operand, String describle, int index) {
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
			if (FLAGS.getFLAGS(FLAGS.CF)) {
				LongALU.add(a2, ONE16);
			}
			a1 = RegisterMgr.getDATA(REG, W);
				LongALU.add(a1, a2);

			if (D) {
				RegisterMgr.setDATA(REG, W, a1);
			} else {
				if(addr == null) {
					RegisterMgr.setDATA(RM, W, a1);
				} else{
					Memoryer.write(addr, env.getDATA(), a1, W);
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
					if (FLAGS.getFLAGS(FLAGS.CF)) {
						LongALU.add(a2, ONE16);
					}
					LongALU.add(a1, a2);
				} else {
					a2 = BIU.getInstruction();
					if (FLAGS.getFLAGS(FLAGS.CF)) {
						LongALU.add(a2, ONE8);
					}
					LongALU.add(a1, a2);
				}
				RegisterMgr.setDATA(RM, W, a1);
			} else {
				a1 = Memoryer.read(addr, env.getDATA(), W);
				if (W) {
					a2 = arrayConcat(BIU.getInstruction(), BIU.getInstruction());
					if (FLAGS.getFLAGS(FLAGS.CF)) {
						LongALU.add(a2, ONE16);
					}
					LongALU.add(a1, a2);
				} else {
					a2 = BIU.getInstruction();
					if (FLAGS.getFLAGS(FLAGS.CF)) {
						LongALU.add(a2, ONE8);
					}
					LongALU.add(a1, a2);
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
				if (FLAGS.getFLAGS(FLAGS.CF)) {
					LongALU.add(a2, ONE16);
				}
				LongALU.add(a1, a2);
				AX.setAX(a1);
			} else {
				a1 = AX.getAL();
				a2 = BIU.getInstruction();
				if (FLAGS.getFLAGS(FLAGS.CF)) {
					LongALU.add(a2, ONE8);
				}
				LongALU.add(a1, a2);
				AX.setAL(a1);
			}
			break;
		}
		}
	}

}
