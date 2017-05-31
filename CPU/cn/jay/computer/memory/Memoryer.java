package cn.jay.computer.memory;

import cn.jay.computer.alu.MathUtils;
import cn.jay.modelprovider.ModelMgr;

public class Memoryer {

	private static final MemoryManager mm = (MemoryManager) ModelMgr.getIOModel("MAIN_MEMORY");
	
	public static final byte[] read(long address, boolean W) {
		if(W) {
			return arrayConcat(mm.read(address), mm.read(address + 1));
		} else {
			return mm.read(address);
		}
	}

	public static final boolean write(long address, byte[] value, boolean W) {
		if(W) {
			byte[] low = new byte[8];
			byte[] high = new byte[8];
			arraySplit16(low, high, value);
			if(mm.write(address, low))
				return mm.write(address + 1, high);
		} else {
			return mm.write(address, value);
		}
		return false;
	}

	public static final byte[] read(byte[] address, boolean W) {
		// TODO
		byte[] ret = read(byteArrayToLong(address, 20), W);
//		System.out.println("Read memory: addr = " + Arrays.toString(address) + ", W = " + W + ", ret = "
//				+ Arrays.toString(ret));
		return ret;
	}

	public static final boolean write(byte[] address, byte[] value, boolean W) {
		// TODO
//		System.out.println("Write memory: addr = " + Arrays.toString(address) + ", W = " + W + ", val = "
//				+ Arrays.toString(value));
		return write(byteArrayToLong(address, 20), value, W);
	}

	public static final byte[] read(byte[] index, byte[] base, boolean W) {
		byte[] address = new byte[20];
		generateMemoryAddress(address, index, base);

		return read(address, W);
	}

	public static final boolean write(byte[] index, byte[] base, byte[] value, boolean W) {
		byte[] address = new byte[20];
		generateMemoryAddress(address, index, base);

		return write(address, value, W);
	}

	public static byte[] longToByteArray(long value, int length) {
		byte[] b = new byte[64];
		for (int i = 0; i < length; i++) {
			b[i] = (byte) ((value >> i) & 1);
		}
		return b;
	}
	
	public static long byteArrayToLong(byte[] value,int length) {
		long p = 0;
		long t = 0;
		for(int i = 0;i < length;i++) {
			t = value[i];
			p |= t << i;
		}
		return p;
	}

	/**
	 * des <- 16 * base + index
	 * 
	 * @param des
	 * @param index
	 * @param base
	 * @throws Exception
	 */
	public static void generateMemoryAddress(byte[] des, byte[] index, byte[] base) {
		long a = MathUtils.byteArrayToLong(index, false, 16);
		long b = MathUtils.byteArrayToLong(base, false, 16);
		byte[] ret = MathUtils.longToByteArray(a + b * 16, false, 64);

		copyArray(des, ret);
	}

	public static void copyArray(byte[] des, byte[] src) {
		for (int i = 0; i < des.length; i++) {
			des[i] = src[i];
		}
	}

	public static byte[] arrayConcat(byte[] low,byte[] high) {
		byte[] ret = new byte[low.length + high.length];
		for(int i = 0;i < low.length;i++) {
			ret[i] = low[i];
		}
		for(int i = 0;i < high.length;i++) {
			ret[low.length + i] = high[i];
		}
		return ret;
	}
	
	public static void arraySplit16(byte[] low, byte[] high, byte[] src) {
		for(int i = 0;i < 8;i++) {
			low[i] = src[i];
		}
		for(int i = 0;i < 8;i++) {
			high[i] = src[i + 8];
		}
	}
	
}
