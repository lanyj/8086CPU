package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;

public class AAM extends Execution {
	
	private static final byte[] TEN8 = new byte[8];

	static {
		TEN8[3] = 1;
		TEN8[1] = 1;
	}

	public AAM(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[][] ret = divide8(AX.getAL(), TEN8);
			AX.setAH(ret[0]);
			AX.setAL(ret[1]);

			break;
		}
		}
	}
	public static byte[][] divide8(byte[] des, byte[] src)  {
		byte[][] ret = new byte[2][8];
		long a = MathUtils.byteArrayToLong(des, false, 8);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] q = MathUtils.longToByteArray(a / b, false, 8);
		byte[] r = MathUtils.longToByteArray(a % b, false, 8);

		ret[0] = q;
		ret[1] = r;
		return ret;
	}

}
