package cn.jay.computer.register.indexregister;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class SP extends BaseRegister {
	public static final SP _SP = new SP();

	private static final byte[] TWO = { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static final void setSP(byte[] data) throws CopyArrayException {
		_SP.setDATA(data);
	}

	public static final byte[] getSP() {
		return _SP.getDATA();
	}

	public static final void sub2() {
		try {
			setSP(sub(getSP(), TWO));
		} catch (CopyArrayException e) {
		}
	}

	public static final void add2() {
		try {
			setSP(add(getSP(), TWO));
		} catch (CopyArrayException e) {
		}
	}

	public static byte[] add(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 16);

		return ret;
	}
	
	public static byte[] sub(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		byte[] ret = MathUtils.longToByteArray(a - b, false, 16);

		return ret;
	}
}
