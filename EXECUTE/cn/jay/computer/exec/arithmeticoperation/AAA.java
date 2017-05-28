package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;

public class AAA extends Execution {
	
	private static final byte[] ONE8 = new byte[8];
	private static final byte[] EIGHT8 = new byte[8];

	static {
		ONE8[0] = 1;
		EIGHT8[3] = 1;
	}

	public AAA(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			try {
				byte[] a = AX.getAL();
				
				boolean A = a[3] > 0;
				boolean B = a[2] > 0;
				boolean C = a[1] > 0;

				boolean P = A & (B | (!B & C));
				
				if(FLAGS.getFLAGS(FLAGS.AF) || P) {
					a = add8(a, EIGHT8);
					a[7] = a[6] = a[5] = a[4] = 0;
					FLAGS.setFLAGS(FLAGS.AF, true);
					FLAGS.setFLAGS(FLAGS.CF, true);
					AX.setAL(a);
					a = AX.getAH();
					add8(a, ONE8);
					AX.setAH(a);
				} else {
					a[7] = a[6] = a[5] = a[4] = 0;
					AX.setAL(a);
				}
			} catch (Exception e) {
			}

			break;
		}
		}
	}
	public static byte[] add8(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 8);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 8);
		
		return ret;
	}

}
