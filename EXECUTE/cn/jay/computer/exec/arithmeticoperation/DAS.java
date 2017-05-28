package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;

public class DAS extends Execution {
	
	private static final byte[] ONE8 = new byte[8];
	private static final byte[] SIX8 = new byte[8];
	private static final byte[] SIXTEN8 = new byte[8];
	
	static {
		ONE8[0] = 1;
		SIX8[2] = 1;
		SIX8[1] = 1;
		
		SIXTEN8[6] = 1;
		SIXTEN8[5] = 1;
	}

	public DAS(String opcode, String operand, String describle, int index) {
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
					a = sub8(a, SIX8);
					FLAGS.setFLAGS(FLAGS.AF, true);
				}
				
				A = a[7] > 0;
				B = a[6] > 0;
				C = a[5] > 0;
				
				P = A & (B | (!B & C));
				
				if(FLAGS.getFLAGS(FLAGS.CF) || P) {
					a = sub8(a, SIXTEN8);
					FLAGS.setFLAGS(FLAGS.CF, true);
				}
				
				AX.setAL(a);
			} catch (Exception e) {
			}

			break;
		}
		}
	}
	public static byte[] sub8(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 8);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a - b, false, 8);
		
		return ret;
	}

}
