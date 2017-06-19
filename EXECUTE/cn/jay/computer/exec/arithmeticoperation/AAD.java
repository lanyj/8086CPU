package cn.jay.computer.exec.arithmeticoperation;

import java.util.Arrays;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;

public class AAD extends Execution {

	private static final byte[] TEN8 = new byte[8];

	static {
		TEN8[3] = 1;
		TEN8[1] = 1;
	}

	public AAD(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] low = multi8(AX.getAH(), TEN8);

			LongALU.add(low, AX.getAL());
			AX.setAL(low);

			AX.setAH(new byte[8]);

			break;
		}
		}
	}

	public static byte[] multi8(byte[] a, byte[] b) {
		long _a = MathUtils.byteArrayToLong(a, false, 8);
		long _b = MathUtils.byteArrayToLong(b, false, 8);
		byte[] ret = MathUtils.longToByteArray(_a * _b, false, 16);

		return Arrays.copyOf(ret, 8);
	}

}
