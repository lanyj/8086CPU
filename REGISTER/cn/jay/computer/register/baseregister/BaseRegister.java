package cn.jay.computer.register.baseregister;

import java.util.Arrays;

import cfg.Configer;
import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.arrayutils.Utils;
import cn.jay.computer.utilexception.CopyArrayException;

public abstract class BaseRegister {
	private final byte[] DATA = new byte[Configer.getRegisterSize()];

	private static final byte[] ONE = new byte[Configer.getRegisterSize()];
	static {
		ONE[0] = 1;
	}

	public final void setDATA(byte[] data) throws CopyArrayException {
		Utils.copyArray(DATA, data);
	}

	public final void setHIGH(byte[] data) throws CopyArrayException {
		int size = DATA.length / 2;
		if (data == null || data.length != size) {
			throw new CopyArrayException(Arrays.toString(data));
		}
		for (int i = DATA.length - 1; i >= 0; i--) {
			DATA[i] = data[i - size];
		}
	}

	public final void setLOW(byte[] data) throws CopyArrayException {
		int size = DATA.length / 2;
		if (data == null || data.length != size) {
			throw new CopyArrayException(Arrays.toString(data));
		}
		for (int i = size - 1; i >= 0; i--) {
			DATA[i] = data[i];
		}
	}

	public final byte[] getDATA() {
		return DATA.clone();
	}

	public final byte[] getHIGH() {
		int size = DATA.length / 2;
		byte[] b = new byte[size];
		for (int i = DATA.length - 1; i >= DATA.length / 2; i--) {
			b[i - size] = DATA[i];
		}
		return b;
	}

	public final byte[] getLOW() {
		int size = DATA.length / 2;
		byte[] b = new byte[size];
		for (int i = size - 1; i >= 0; i--) {
			b[i] = DATA[i];
		}
		return b;
	}

	public final byte indexOf(int index) {
		return DATA[index];
	}

	@Override
	public String toString() {
		return Arrays.toString(DATA);
	}

	public final void dec() {
		sub16(DATA, ONE);
	}

	public final void inc() {
		add16(DATA, ONE);
	}

	public final boolean isZero() {
		for (int i = 0; i < DATA.length; i++) {
			if (DATA[i] != 0) {
				return false;
			}
		}
		return true;
	}

	public static void sub16(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		byte[] ret = MathUtils.longToByteArray(a - b, false, 16);

		MathUtils.copyArray(des, ret);
	}

	public static void add16(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 16);

		MathUtils.copyArray(des, ret);
	}

}
