package cn.jay.computer.alu;

import cfg.Configer;
import cn.jay.computer.alu.exception.DivideException;
import cn.jay.computer.alu.exception.LengthNotMatchException;

public class LongALU {
	public static final int SIZE = Configer.getInstructionLength();

	public static void add(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.add8(des, src);
		} else {
			AuxLongALU.add16(des, src);
		}
	}

	public static void sub(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.sub8(des, src);
		} else {
			AuxLongALU.sub16(des, src);
		}
	}

	public static byte[] mul(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			return AuxLongALU.multi8(des, src);
		} else {
			return AuxLongALU.multi16(des, src);
		}
	}

	public static byte[] imul(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			return AuxLongALU.imulti8(des, src);
		} else {
			return AuxLongALU.imulti16(des, src);
		}
	}

	public static void div(byte[] des, byte[] src) throws LengthNotMatchException, DivideException {
		if (des.length == 16) {
			AuxLongALU.divide8(des, src);
		} else {
			AuxLongALU.divide16(des, src);
		}
	}

	public static void idiv(byte[] des, byte[] src) throws LengthNotMatchException, DivideException {
		if (des.length == 16) {
			AuxLongALU.idivide8(des, src);
		} else {
			AuxLongALU.idivide16(des, src);
		}
	}

	public static void xor(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.xor8(des, src);
		} else {
			AuxLongALU.xor16(des, src);
		}
	}

	public static void or(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.or8(des, src);
		} else {
			AuxLongALU.or16(des, src);
		}
	}

	public static void and(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.and8(des, src);
		} else {
			AuxLongALU.and16(des, src);
		}
	}

	public static void not(byte[] des) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.not8(des);
		} else {
			AuxLongALU.not16(des);
		}
	}

	public static void test(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.test8(des, src);
		} else {
			AuxLongALU.test16(des, src);
		}
	}

	public static void sal(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.sal8(des, src, useAL);
		} else {
			AuxLongALU.sal16(des, src, useAL);
		}
	}

	public static void shl(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.shl8(des, src, useAL);
		} else {
			AuxLongALU.shl16(des, src, useAL);
		}
	}

	public static void sar(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.sar8(des, src, useAL);
		} else {
			AuxLongALU.sar16(des, src, useAL);
		}
	}

	public static void shr(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.shr8(des, src, useAL);
		} else {
			AuxLongALU.shr16(des, src, useAL);
		}
	}

	public static void rol(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.rol8(des, src, useAL);
		} else {
			AuxLongALU.rol16(des, src, useAL);
		}
	}

	public static void ror(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.ror8(des, src, useAL);
		} else {
			AuxLongALU.ror16(des, src, useAL);
		}
	}

	public static void rcl(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.rcl8(des, src, useAL);
		} else {
			AuxLongALU.rcl16(des, src, useAL);
		}
	}

	public static void rcr(byte[] des, byte[] src, boolean useAL) throws LengthNotMatchException {
		if (des.length == 8) {
			AuxLongALU.rcr8(des, src, useAL);
		} else {
			AuxLongALU.rcr16(des, src, useAL);
		}
	}

}
