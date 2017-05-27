package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.BX;
import cn.jay.computer.utilexception.CopyArrayException;

public class XLAT extends Execution {

	public XLAT(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			byte[] addr = BX.getBX();
			addr = add16(addr, AX.getAL());
			try {
				AX.setAL(Memoryer.read(addr, Environment.getDataSegment().getDATA(), false));
			} catch (CopyArrayException e) {
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
