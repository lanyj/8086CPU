package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.DX;
import cn.jay.computer.utilexception.CopyArrayException;

public class CWD extends Execution {

	private static final byte[] ONE = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	private static final byte[] ZERO = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public CWD(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] a = AX.getAX();
			try {
				if (a[15] == 1) {
					DX.setDX(ONE);
				} else {
					DX.setDX(ZERO);
				}
			} catch (CopyArrayException e) {
			}
			break;
		}
		}
	}
}
