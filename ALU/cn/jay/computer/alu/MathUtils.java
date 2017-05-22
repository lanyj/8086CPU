package cn.jay.computer.alu;

public class MathUtils {
	public static byte[] longToByteArray(long value, boolean signed, int length) {
		byte[] b = new byte[64];
		for(int i = 0;i < length;i++) {
			b[i] = (byte) ((value >> i) & 1);
		}
		if(signed) {
			for(int i = length;i < 64;i++) {
				b[i] = b[length - 1];
			}
		}
		return b;
	}

	public static long byteArrayToLong(byte[] value,boolean signed,int length) {
		long p = 0;
		long t = 0;
		for(int i = 0;i < length;i++) {
			t = value[i];
			p |= t << i;
		}
		if(signed) {
			for(int i = length;i < 64;i++) {
				p |= t << i;
			}
		}
		return p;
	}

	public static void copyArray(byte[] des, byte[] src) {
		for (int i = 0; i < des.length; i++) {
			des[i] = src[i];
		}
	}
	
}
