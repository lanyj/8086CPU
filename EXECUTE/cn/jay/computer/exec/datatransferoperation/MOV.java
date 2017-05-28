package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.utilexception.CopyArrayException;

public class MOV extends Execution {

	public MOV(String opcode, String operand, String describle, int index) {
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

			boolean D = getOperand("D").equals("1");
			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			if (D) {
				try {
					RegisterMgr.setDATA(REG, W, Memoryer.read(addr, env.getDATA(), W));
				} catch (Exception e) {
				}
			} else {
				try {
					Memoryer.write(addr, env.getDATA(), RegisterMgr.getDATA(REG, W), W);
				} catch (Exception e) {
				}
			}

			break;
		}
		case 1: {
			BaseRegister env = Environment.getDataSegment();
			BIU.getInstruction();

			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);

			byte[] value = null;
			byte[] low = BIU.getInstruction();
			if (W) {
				byte[] high = BIU.getInstruction();
				value = arrayConcat(low, high);
			} else {
				value = low;
			}
			if (addr == null) {
				try {
					RegisterMgr.setDATA(REG, W, value);
				} catch (Exception e) {
				}
			} else {
				Memoryer.write(addr, env.getDATA(), value, W);
			}
			break;
		}
		case 2: {
			boolean W = getOperand("W").equals("1");
			String REG = getOperand("REG");

			byte[] value = null;
			byte[] low = BIU.getInstruction();
			if (W) {
				byte[] high = BIU.getInstruction();
				value = arrayConcat(low, high);
			} else {
				value = low;
			}

			try {
				RegisterMgr.setDATA(REG, W, value);
			} catch (Exception e) {
			}
			break;
		}
		case 3: {
			BaseRegister env = Environment.getDataSegment();
			BIU.getInstruction();

			boolean W = getOperand("W").equals("1");
			boolean D = getOperand("D").equals("1");

			byte[] addr = null;
			byte[] low = BIU.getInstruction();
			if (W) {
				byte[] high = BIU.getInstruction();
				addr = arrayConcat(low, high);
			} else {
				addr = low;
			}

			if (D) {
				try {
					AX.setAX(Memoryer.read(addr, env.getDATA(), W));
				} catch (CopyArrayException e) {
				}
			} else {
				Memoryer.write(addr, env.getDATA(), AX.getAX(), W);
			}
			break;
		}
		case 4: {
			BaseRegister env = Environment.getDataSegment();
			BIU.getInstruction();
			boolean D = getOperand("D").equals("1");
			String MOD = getOperand("MOD");
			String SEG = getOperand("SEG");
			String RM = getOperand("R/M");

			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);

			if (D) {
				try {
					RegisterMgr.setDATA(SEG, true, Memoryer.read(addr, env.getDATA(), true));
				} catch (Exception e) {
				}
			} else {
				try {
					Memoryer.write(addr, env.getDATA(), RegisterMgr.getDATA(SEG, true), true);
				} catch (Exception e) {
				}
			}
			break;
		}
		}
	}

}
