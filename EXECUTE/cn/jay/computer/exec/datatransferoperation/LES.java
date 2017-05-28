package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.alu.MathUtils;
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

	public void exec() {
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

			byte[] low = Memoryer.read(addr, env.getDATA(), true);
			byte[] high = Memoryer.read(add16(addr, TWO), env.getDATA(), true);
			try {
				RegisterMgr.setDATA(REG, true, low);
				ES.setES(high);
			} catch (Exception e) {
			}
			
			break;
		}
		}
	}
	
	public static byte[] add16(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 16);

		return ret;
	}	
}
