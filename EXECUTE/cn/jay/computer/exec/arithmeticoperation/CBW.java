package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.utilexception.CopyArrayException;

public class CBW extends Execution {

	private static final byte[] ONE = { 1, 1, 1, 1, 1, 1, 1, 1 };
	private static final byte[] ZERO = { 0, 0, 0, 0, 0, 0, 0, 0 };

	public CBW(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] a = AX.getAL();
			try {
				if (a[7] == 1) {
					AX.setAH(ONE);
				} else {
					AX.setAH(ZERO);
				}
			} catch (CopyArrayException e) {
			}
			break;
		}
		}
	}
}
