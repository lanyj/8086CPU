package cn.jay.computer.alu;

import java.util.Arrays;

import cfg.Configer;
import cn.jay.computer.alu.exception.DivideException;
import cn.jay.computer.alu.exception.LengthNotMatchException;
import cn.jay.computer.register.flagregister.FLAGS;

public class LongALU {
	public static final int SIZE = Configer.getInstructionLength();

	public static void add16(byte[] des, byte[] src) throws LengthNotMatchException  {
		if (des.length != src.length || des.length != 16) {
			throw new LengthNotMatchException("add16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 16);

		setFlags16(des, src, ret);
		MathUtils.copyArray(des, ret);
	}

	public static void add8(byte[] des, byte[] src) throws Exception {
		if (des.length != src.length || des.length != 8) {
			throw new LengthNotMatchException("add8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, 8);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 8);

		setFlags8(des, src, ret);
		MathUtils.copyArray(des, ret);
	}

	public static void sub16(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != src.length || des.length != 16) {
			throw new LengthNotMatchException("sub16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		byte[] ret = MathUtils.longToByteArray(a - b, false, 16);

		setFlags16(des, src, ret);
		MathUtils.copyArray(des, ret);
	}

	public static void sub8(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != src.length || des.length != 8) {
			throw new LengthNotMatchException("sub8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, 8);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a - b, false, 8);

		setFlags8(des, src, ret);
		MathUtils.copyArray(des, ret);
	}

	public static byte[] multi16(byte[] a, byte[] b) throws LengthNotMatchException {
		if (a.length != b.length || a.length != 16) {
			throw new LengthNotMatchException("mul16:" + a.length + ", " + b.length);
		}
		long _a = MathUtils.byteArrayToLong(a, false, 16);
		long _b = MathUtils.byteArrayToLong(b, false, 16);
		byte[] ret = MathUtils.longToByteArray(_a * _b, false, 32);

		boolean cf = false;
		for (int i = 16; i < 32; i++) {
			if (ret[i] != 0) {
				cf = true;
				break;
			}
		}
		if (cf) {
			FLAGS.setFLAGS(FLAGS.CF, true);
			FLAGS.setFLAGS(FLAGS.OF, true);
		} else {
			FLAGS.setFLAGS(FLAGS.CF, false);
			FLAGS.setFLAGS(FLAGS.OF, false);
		}
		return Arrays.copyOf(ret, 32);
	}

	public static byte[] multi8(byte[] a, byte[] b) throws LengthNotMatchException {
		if (a.length != b.length || a.length != 8) {
			throw new LengthNotMatchException("mul8:" + a.length + ", " + b.length);
		}
		long _a = MathUtils.byteArrayToLong(a, false, 8);
		long _b = MathUtils.byteArrayToLong(b, false, 8);
		byte[] ret = MathUtils.longToByteArray(_a * _b, false, 16);

		boolean cf = false;
		for (int i = 8; i < 16; i++) {
			if (ret[i] != 0) {
				cf = true;
				break;
			}
		}
		if (cf) {
			FLAGS.setFLAGS(FLAGS.CF, true);
			FLAGS.setFLAGS(FLAGS.OF, true);
		} else {
			FLAGS.setFLAGS(FLAGS.CF, false);
			FLAGS.setFLAGS(FLAGS.OF, false);
		}
		return Arrays.copyOf(ret, 16);
	}

	public static byte[] imulti16(byte[] a, byte[] b) throws LengthNotMatchException {
		if (a.length != b.length || a.length != 16) {
			throw new LengthNotMatchException("imul16:" + a.length + ", " + b.length);
		}
		long _a = MathUtils.byteArrayToLong(a, true, 16);
		long _b = MathUtils.byteArrayToLong(b, true, 16);
		byte[] ret = MathUtils.longToByteArray(_a * _b, true, 32);

		boolean one = true;
		for (int i = 16; i < 32; i++) {
			if (ret[i] != 0) {
				one = false;
				break;
			}
		}
		boolean zero = true;
		for (int i = 16; i < 32; i++) {
			if (ret[i] != 0) {
				zero = false;
				break;
			}
		}
		if (one || zero) {
			FLAGS.setFLAGS(FLAGS.CF, false);
			FLAGS.setFLAGS(FLAGS.OF, false);
		} else {
			FLAGS.setFLAGS(FLAGS.CF, true);
			FLAGS.setFLAGS(FLAGS.OF, true);
		}
		return Arrays.copyOf(ret, 32);
	}

	public static byte[] imulti8(byte[] a, byte[] b) throws LengthNotMatchException {
		if (a.length != b.length || a.length != 8) {
			throw new LengthNotMatchException("imul8:" + a.length + ", " + b.length);
		}
		long _a = MathUtils.byteArrayToLong(a, true, 8);
		long _b = MathUtils.byteArrayToLong(b, true, 8);
		byte[] ret = MathUtils.longToByteArray(_a * _b, true, 16);

		boolean one = true;
		for (int i = 8; i < 16; i++) {
			if (ret[i] != 0) {
				one = false;
				break;
			}
		}
		boolean zero = true;
		for (int i = 8; i < 16; i++) {
			if (ret[i] != 0) {
				zero = false;
				break;
			}
		}
		if (one || zero) {
			FLAGS.setFLAGS(FLAGS.CF, false);
			FLAGS.setFLAGS(FLAGS.OF, false);
		} else {
			FLAGS.setFLAGS(FLAGS.CF, true);
			FLAGS.setFLAGS(FLAGS.OF, true);
		}
		return Arrays.copyOf(ret, 16);
	}

	public static void divide16(byte[] des, byte[] src) throws LengthNotMatchException, DivideException {
		if (des.length != 32 && src.length != 16) {
			throw new LengthNotMatchException("div16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, 32);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		if (b == 0) {
			throw new DivideException("div16 ,divided by zero");
		}
		byte[] q = MathUtils.longToByteArray(a / b, false, 16);
		byte[] r = MathUtils.longToByteArray(a % b, false, 16);

		for (int i = 0; i < 16; i++) {
			des[i] = q[i];
		}
		for (int i = 16; i < 32; i++) {
			des[i] = r[i - 16];
		}
	}

	public static void idivide16(byte[] des, byte[] src) throws LengthNotMatchException, DivideException {
		if (des.length != 32 && src.length != 16) {
			throw new LengthNotMatchException("idiv16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, true, 32);
		long b = MathUtils.byteArrayToLong(src, true, 16);
		if (b == 0) {
			throw new DivideException("idiv16 ,divided by zero");
		}
		byte[] q = MathUtils.longToByteArray(a / b, true, 16);
		byte[] r = MathUtils.longToByteArray(a % b, true, 16);

		for (int i = 0; i < 16; i++) {
			des[i] = q[i];
		}
		for (int i = 16; i < 32; i++) {
			des[i] = r[i - 16];
		}
	}

	public static void divide8(byte[] des, byte[] src) throws LengthNotMatchException, DivideException {
		if (des.length != 16 && src.length != 8) {
			throw new LengthNotMatchException("div8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		if (b == 0) {
			throw new DivideException("div16 ,divided by zero");
		}
		byte[] q = MathUtils.longToByteArray(a / b, false, 8);
		byte[] r = MathUtils.longToByteArray(a % b, false, 8);

		for (int i = 0; i < 8; i++) {
			des[i] = q[i];
		}
		for (int i = 8; i < 16; i++) {
			des[i] = r[i - 8];
		}
	}

	public static void idivide8(byte[] des, byte[] src) throws LengthNotMatchException, DivideException {
		if (des.length != 16 && src.length != 8) {
			throw new LengthNotMatchException("idiv8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, true, 16);
		long b = MathUtils.byteArrayToLong(src, true, 8);
		if (b == 0) {
			throw new DivideException("idiv16 ,divided by zero");
		}
		byte[] q = MathUtils.longToByteArray(a / b, true, 8);
		byte[] r = MathUtils.longToByteArray(a % b, true, 8);

		for (int i = 0; i < 8; i++) {
			des[i] = q[i];
		}
		for (int i = 8; i < 16; i++) {
			des[i] = r[i - 8];
		}
	}

	public static void xor16(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 16 && src.length != 16) {
			throw new LengthNotMatchException("xor16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a ^ b, false, des.length);

		logicalSetFlags16(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void xor8(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 8 && src.length != 8) {
			throw new LengthNotMatchException("xor8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a ^ b, false, des.length);

		logicalSetFlags8(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void or16(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 16 && src.length != 16) {
			throw new LengthNotMatchException("or16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a | b, false, des.length);

		logicalSetFlags16(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void or8(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 8 && src.length != 8) {
			throw new LengthNotMatchException("or8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a | b, false, des.length);

		logicalSetFlags8(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void and16(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 16 && src.length != 16) {
			throw new LengthNotMatchException("and16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a & b, false, des.length);

		logicalSetFlags16(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void and8(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 8 && src.length != 8) {
			throw new LengthNotMatchException("and8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a & b, false, des.length);

		logicalSetFlags8(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void not16(byte[] des) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("not16:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		byte[] ret = MathUtils.longToByteArray(~a, false, des.length);

		logicalSetFlags16(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void not8(byte[] des) throws LengthNotMatchException {
		if (des.length != 8) {
			throw new LengthNotMatchException("not8:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		byte[] ret = MathUtils.longToByteArray(~a, false, des.length);

		logicalSetFlags8(ret);
		MathUtils.copyArray(des, ret);
	}

	public static void test16(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 16 && src.length != 16) {
			throw new LengthNotMatchException("test16:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a & b, false, des.length);

		logicalSetFlags16(ret);
	}
	
	public static void test8(byte[] des, byte[] src) throws LengthNotMatchException {
		if (des.length != 8 && src.length != 8) {
			throw new LengthNotMatchException("test8:" + des.length + ", " + src.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a & b, false, des.length);

		logicalSetFlags8(ret);
	}
	
	public static void sal16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("sal16:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a << b, false, des.length);

		saslSetFlags16(ret,useAL);
		MathUtils.copyArray(des, ret);
	}
	
	public static void sal8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8){
			throw new LengthNotMatchException("sal8:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a << b, false, des.length);

		saslSetFlags8(ret,useAL);
		MathUtils.copyArray(des, ret);
	}
	
	public static void shl16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("shl16:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a << b, false, des.length);

		saslSetFlags16(ret,useAL);
		MathUtils.copyArray(des, ret);
	}
	
	public static void shl8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8){
			throw new LengthNotMatchException("shl8:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte[] ret = MathUtils.longToByteArray(a << b, false, des.length);

		saslSetFlags8(ret,useAL);
		MathUtils.copyArray(des, ret);
	}
	
	public static void sar16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("sar16:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		boolean cf = (a >> (b - 1) & 1) > 0;
		
		byte[] ret = MathUtils.longToByteArray(a >> b, false, des.length);

		saslSetFlags16(ret,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf);
		MathUtils.copyArray(des, ret);
	}
	
	public static void sar8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8) {
			throw new LengthNotMatchException("sar8:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		boolean cf = (a >> (b - 1) & 1) > 0;
		
		byte[] ret = MathUtils.longToByteArray(a >> b, false, des.length);

		saslSetFlags8(ret,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf);
		MathUtils.copyArray(des, ret);
	}
	
	public static void shr16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16){
			throw new LengthNotMatchException("shr16:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		boolean cf = (a >> (b - 1) & 1) > 0;
		
		byte[] ret = MathUtils.longToByteArray(a >>> b, false, des.length);

		saslSetFlags16(ret,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf);
		MathUtils.copyArray(des, ret);
	}
	
	public static void shr8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8) {
			throw new LengthNotMatchException("shr8:" + des.length);
		}
		long a = MathUtils.byteArrayToLong(des, false, des.length);
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		boolean cf = (a >> (b - 1) & 1) > 0;
		
		byte[] ret = MathUtils.longToByteArray(a >> b, false, des.length);

		saslSetFlags8(ret,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf);
		MathUtils.copyArray(des, ret);
	}
	
	public static void rol16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("rol16:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte cf = 0;
		for(int i = 0;i < b % 16;i++) {
			cf = des[15];
			for(int j = 15;j > 0;j--) {
				des[j] = des[(j + 15) % 16];
			}
			des[0] = cf;
		}
		saslSetFlags16(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	public static void rol8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8) {
			throw new LengthNotMatchException("rol8:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte cf = 0;
		for(int i = 0;i < b % 8;i++) {
			cf = des[7];
			for(int j = 7;j > 0;j--) {
				des[j] = des[(j + 7) % 8];
			}
			des[0] = cf;
		}
		saslSetFlags8(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	public static void ror16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("ror16:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte cf = 0;
		for(int i = 0;i < b % 16;i++) {
			cf = des[0];
			for(int j = 0;j < 15;j++) {
				des[j] = des[(j + 17) % 16];
			}
			des[15] = cf;
		}
		saslSetFlags16(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	public static void ror8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8) {
			throw new LengthNotMatchException("ror8:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte cf = 0;
		for(int i = 0;i < b % 8;i++) {
			cf = des[0];
			for(int j = 0;j < 8;j++) {
				des[j] = des[(j + 9) % 8];
			}
			des[7] = cf;
		}
		saslSetFlags16(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	public static void rcl16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("rcl16:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte old = (byte) (FLAGS.getFLAGS(FLAGS.CF) ? 1 : 0);
		byte cf = 0;
		for(int i = 0;i < b % 16;i++) {
			cf = des[15];
			for(int j = 15;j > 0;j--) {
				des[j] = des[(j + 15) % 16];
			}
			des[0] = old;
			old = cf;
		}
		saslSetFlags16(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	public static void rcl8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8) {
			throw new LengthNotMatchException("rcl8:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte old = (byte) (FLAGS.getFLAGS(FLAGS.CF) ? 1 : 0);
		byte cf = 0;
		for(int i = 0;i < b % 8;i++) {
			cf = des[7];
			for(int j = 7;j > 0;j--) {
				des[j] = des[(j + 7) % 8];
			}
			des[0] = old;
			old = cf;
		}
		saslSetFlags8(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	public static void rcr16(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 16) {
			throw new LengthNotMatchException("rcr16:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte old = (byte) (FLAGS.getFLAGS(FLAGS.CF) ? 1 : 0);
		byte cf = 0;
		for(int i = 0;i < b % 16;i++) {
			cf = des[0];
			for(int j = 0;j < 15;j++) {
				des[j] = des[(j + 17) % 16];
			}
			des[15] = old;
			old = cf;
		}
		saslSetFlags16(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	public static void rcr8(byte[] des, byte[] src,boolean useAL) throws LengthNotMatchException {
		if (des.length != 8) {
			throw new LengthNotMatchException("rcr8:" + des.length);
		}
		long b = MathUtils.byteArrayToLong(src, false, src.length);
		byte old = (byte) (FLAGS.getFLAGS(FLAGS.CF) ? 1 : 0);
		byte cf = 0;
		for(int i = 0;i < b % 8;i++) {
			cf = des[0];
			for(int j = 0;j < 8;j++) {
				des[j] = des[(j + 9) % 8];
			}
			des[7] = old;
			old = cf;
		}
		saslSetFlags16(des,useAL);
		FLAGS.setFLAGS(FLAGS.CF, cf > 0);
	}
	
	private static void saslSetFlags16(byte[] ret,boolean useAL) {
		FLAGS.setFLAGS(FLAGS.CF, ret[16 % ret.length] > 0);
		FLAGS.setFLAGS(FLAGS.OF, ret[16 % ret.length] != ret[ret.length - 1]);
		FLAGS.setFLAGS(FLAGS.SF, ret[ret.length - 1] > 0);
		boolean zf = true;
		for (int i = 0; i < ret.length - 1; i++) {
			if (ret[i] > 0) {
				zf = false;
				break;
			}
		}
		FLAGS.setFLAGS(FLAGS.ZF, zf);
		if(useAL) {
			boolean pf = true;
			for (int i = 0; i < 8; i++) {
				if (ret[i] > 0) {
					pf = !pf;
				}
			}
			FLAGS.setFLAGS(FLAGS.PF, pf);
		}
	}
	
	private static void saslSetFlags8(byte[] ret,boolean useAL) {
		FLAGS.setFLAGS(FLAGS.CF, ret[8 % ret.length] > 0);
		FLAGS.setFLAGS(FLAGS.OF, ret[8 % ret.length] != ret[7]);
		FLAGS.setFLAGS(FLAGS.SF, ret[7] > 0);
		boolean zf = true;
		for (int i = 0; i < 7; i++) {
			if (ret[i] > 0) {
				zf = false;
				break;
			}
		}
		FLAGS.setFLAGS(FLAGS.ZF, zf);
		if(useAL) {
			boolean pf = true;
			for (int i = 0; i < 8; i++) {
				if (ret[i] > 0) {
					pf = !pf;
				}
			}
			FLAGS.setFLAGS(FLAGS.PF, pf);
		}
	}

	private static void setFlags16(byte[] des, byte[] src, byte[] ret) {
		boolean _a15 = des[15] > 0;
		boolean _b15 = src[15] > 0;
		boolean _c15 = ret[15] > 0;
		FLAGS.setFLAGS(FLAGS.CF, (_a15 & _b15) | (_a15 ^ _b15) & (!_c15));

		boolean pf = true;
		for (int i = 0; i < 8; i++) {
			if (ret[i] > 0) {
				pf = !pf;
			}
		}
		FLAGS.setFLAGS(FLAGS.PF, pf);

		boolean _a3 = des[3] > 0;
		boolean _b3 = src[3] > 0;
		boolean _c3 = ret[3] > 0;
		FLAGS.setFLAGS(FLAGS.AF, (_a3 & _b3) | (_a3 ^ _b3) & (!_c3));

		boolean zf = true;
		for (int i = 0; i < 16; i++) {
			if (ret[i] > 0) {
				zf = false;
				break;
			}
		}
		FLAGS.setFLAGS(FLAGS.ZF, zf);

		FLAGS.setFLAGS(FLAGS.SF, ret[15] > 0);

		FLAGS.setFLAGS(FLAGS.OF, (_a15 == _b15) & (_a15 != _c15));
	}

	private static void setFlags8(byte[] des, byte[] src, byte[] ret) {
		boolean _a7 = des[7] > 0;
		boolean _b7 = src[7] > 0;
		boolean _c7 = ret[7] > 0;
		FLAGS.setFLAGS(FLAGS.CF, (_a7 & _b7) | (_a7 ^ _b7) & (!_c7));

		boolean pf = true;
		for (int i = 0; i < 8; i++) {
			if (ret[i] > 0) {
				pf = !pf;
			}
		}
		FLAGS.setFLAGS(FLAGS.PF, pf);

		boolean _a3 = des[3] > 0;
		boolean _b3 = src[3] > 0;
		boolean _c3 = ret[3] > 0;
		FLAGS.setFLAGS(FLAGS.AF, (_a3 & _b3) | (_a3 ^ _b3) & (!_c3));

		boolean zf = true;
		for (int i = 0; i < 8; i++) {
			if (ret[i] > 0) {
				zf = false;
				break;
			}
		}
		FLAGS.setFLAGS(FLAGS.ZF, zf);

		FLAGS.setFLAGS(FLAGS.SF, ret[7] > 0);

		FLAGS.setFLAGS(FLAGS.OF, (_a7 == _b7) & (_a7 != _c7));
	}

	private static void logicalSetFlags16(byte[] ret) {
		boolean pf = true;
		for (int i = 0; i < 8; i++) {
			if (ret[i] > 0) {
				pf = !pf;
			}
		}
		FLAGS.setFLAGS(FLAGS.PF, pf);

		boolean zf = true;
		for (int i = 0; i < 16; i++) {
			if (ret[i] > 0) {
				zf = false;
				break;
			}
		}
		FLAGS.setFLAGS(FLAGS.ZF, zf);

		FLAGS.setFLAGS(FLAGS.SF, ret[15] > 0);

		FLAGS.setFLAGS(FLAGS.CF, false);
		FLAGS.setFLAGS(FLAGS.OF, false);
	}

	private static void logicalSetFlags8(byte[] ret) {
		boolean pf = true;
		for (int i = 0; i < 8; i++) {
			if (ret[i] > 0) {
				pf = !pf;
			}
		}
		FLAGS.setFLAGS(FLAGS.PF, pf);

		boolean zf = true;
		for (int i = 0; i < 8; i++) {
			if (ret[i] > 0) {
				zf = false;
				break;
			}
		}
		FLAGS.setFLAGS(FLAGS.ZF, zf);

		FLAGS.setFLAGS(FLAGS.SF, ret[7] > 0);

		FLAGS.setFLAGS(FLAGS.CF, false);
		FLAGS.setFLAGS(FLAGS.OF, false);
	}

	public static void main(String[] args) throws Exception {
		byte[] a = {0,1,1,1,1,0,1,0};
		byte[] b = {0,0,1,1,1,1,0,0};
		
		add8(a, b);
		System.out.println(Arrays.toString(a));
		System.out.println(FLAGS.getStateString());
		
		byte[] c = {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		byte[] d = {1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		
		System.out.println(Arrays.toString(multi16(c, d)));
		System.out.println(FLAGS.getStateString());
		
		byte[] e = {0,1,0,1,1,0,1,1,1,1,1,1,1,1,1,1};
		byte[] f = {1,1,0,0,0,0,0,0};
		
		idivide8(e, f);
		System.out.println(Arrays.toString(e));
		System.out.println(FLAGS.getStateString());
		
		System.out.println();
		byte[] g = {1,0,1,0,0,0,0,1};
		shr8(g, f, true);
		System.out.println(Arrays.toString(g));
		System.out.println(FLAGS.getStateString());
		
		FLAGS.setFLAGS(FLAGS.CF, true);
		byte[] h = {0,0,1,0,1,1,0,1};
		rcl8(h, f, true);
		System.out.println(Arrays.toString(h));
		System.out.println(FLAGS.getStateString());
	}
	
}
