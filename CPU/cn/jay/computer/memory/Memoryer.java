package cn.jay.computer.memory;

import java.util.Arrays;

import cn.jay.computer.alu.MathUtils;

public class Memoryer {

	// dev.csdn.net/article/2/2300.shtm CISC
	/*
	 * 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 
	 * 0 1 2 3 4 5 6 7 8 9  A  B  C  D  E  F
	 * 8A8F 3412 -> MOV CL,[BX+1234H] C787 0021 50FA -> MOV [BX+2100H],0FA50H
	 * 1100 0111 1000 0111 0000 0000 0010 0001 0101 0000 1111 1010 0101 0000
	 * 8ED8 -> MOV DS,AX
	 */

	// private static final byte[][] test_code = { { 0, 1, 0, 1, 0, 0, 0, 1 }, {
	// 1, 1, 1, 1, 0, 0, 0, 1 }, { 1, 1, 1, 1, 0, 0, 0, 1 },
	// { 0, 0, 1, 0, 1, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0, 0, 0 }, { 1, 1, 1, 1, 1,
	// 1, 1, 1 } };
	
	//MOV [BX], 123FH
//	private static final byte[][] test_code = { { 1, 1, 1, 0, 0, 0, 1, 1 }, { 1, 1, 1, 0, 0, 0, 0, 0 },
//			{ 1, 1, 1, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0, 0, 0 },
//			{ 1, 1, 1, 1, 1, 1, 1, 1 } };
	
	//MOV BX, 123FH
//	private static final byte[][] test_code = { { 1, 1, 0, 1, 1, 1, 0, 1 }, { 1, 1, 0, 1, 1, 1, 0, 1 },
//				{ 1, 1, 0, 1, 1, 1, 0, 1 }, { 0, 1, 0, 0, 1, 0, 0, 0 },
//				{ 1, 1, 1, 1, 1, 1, 1, 1 } };
	// private static final byte[][] test_code =
	// {{1,0,0,0,1,0,1,1},{0,1,0,0,0,1,1,0},{0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0},{1,1,1,1,1,1,1,1}
	// };
	
	
//	PUSH DS
	private static final byte[][] test_code = { { 1, 1, 1, 1, 1, 1, 1, 1 },{ 0,0,0,0,1,1,0,1 }, { 0,0,0,0,1,1,0,1},{1,1,1,1,1,1,0,0},{0,1,0,0,1,0,0,0},{1,1,1,1,1,1,1,1} };
	private static int p_i = 0;

	public static final byte[] read(long address, boolean W) {
		return read(longToByteArray(address, 20), W);
	}

	public static final boolean write(long address, byte[] value, boolean W) {
		return write(longToByteArray(address, 20), value, W);
	}

	public static final byte[] read(byte[] address, boolean W) {
		// TODO
		System.out.println("Read memory: addr = " + Arrays.toString(address) + ", W = " + W + ", ret = "
				+ Arrays.toString(test_code[p_i]));
		return test_code[p_i++];
	}

	public static final boolean write(byte[] address, byte[] value, boolean W) {
		// TODO
		System.out.println("Write memory: addr = " + Arrays.toString(address) + ", W = " + W + ", val = "
				+ Arrays.toString(value));
		return false;
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

}
