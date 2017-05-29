package cn.jay.computer.driver;

import java.util.Arrays;

public class DriverMgr {

	public static byte[] read(byte[] port) {
		// TODO Auto-generated method stub
		System.out.println("Driver read:port = " + Arrays.toString(port));
		return null;
	}

	public static boolean write(byte[] port, byte[] value) {
		// TODO Auto-generated method stub
		
		System.out.println("Driver write:port = " + Arrays.toString(port) + ", val = " + Arrays.toString(value));
		
		return false;
	}

}
