package cn.jay.computer.arrayutils;
import java.util.Arrays;

public class Utils {
	public static void copyArray(byte [] des, byte [] src) throws CopyArrayException {
		if(des == null || src == null || des.length != src.length) {
			throw new CopyArrayException(Arrays.toString(des) + ", " + Arrays.toString(src));
		}
		for(int i = 0;i < src.length;i++) {
			des[i] = src[i];
		}
	}
	public static void arrayConcat(byte[] des,byte[] high,byte[] low) throws CopyArrayException {
		if(des == null || high == null || low == null || (des.length != high.length + low.length)) {
			throw new CopyArrayException(Arrays.toString(high) + ", " + Arrays.toString(low));
		}
		int i = 0;
		for(;i < low.length;i++) {
			des[i] = low[i];
		}
		for(;i < high.length;i++) {
			des[i] = high[i - low.length];
		}
	}
}
