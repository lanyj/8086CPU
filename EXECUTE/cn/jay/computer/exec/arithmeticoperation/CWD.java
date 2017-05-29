package cn.jay.computer.exec.arithmeticoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.DX;

public class CWD extends Execution {

	private static final byte[] ONE = { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
	private static final byte[] ZERO = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public CWD(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] a = AX.getAX();
			if (a[15] == 1) {
				DX.setDX(ONE);
			} else {
				DX.setDX(ZERO);
			}
			break;
		}
		}
	}
}
