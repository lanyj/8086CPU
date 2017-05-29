package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;

public class DAA extends Execution {

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

	public DAA(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] a = AX.getAL();

			boolean A = a[3] > 0;
			boolean B = a[2] > 0;
			boolean C = a[1] > 0;

			boolean P = A & (B | (!B & C));

			if (FLAGS.getFLAGS(FLAGS.AF) || P) {
				a = add8(a, SIX8);
			}

			A = a[7] > 0;
			B = a[6] > 0;
			C = a[5] > 0;

			P = A & (B | (!B & C));

			if (FLAGS.getFLAGS(FLAGS.CF) || P) {
				a = add8(a, SIXTEN8);
				FLAGS.setFLAGS(FLAGS.CF, true);
			} else {
				FLAGS.setFLAGS(FLAGS.CF, false);
			}

			AX.setAL(a);

			break;
		}
		}
	}

}
