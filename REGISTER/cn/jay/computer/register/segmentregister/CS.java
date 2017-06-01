package cn.jay.computer.register.segmentregister;

import java.util.Arrays;

import cn.jay.computer.arrayutils.CopyArrayException;
import cn.jay.computer.register.baseregister.BaseRegister;

public class CS extends BaseRegister {
	public static final CS _CS = new CS();
	
	public static final void setCS(byte[] data) throws CopyArrayException {
		
		System.out.println("\nSet CS = " + Arrays.toString(data) + "\n");
		
		_CS.setDATA(data);
	}
	public static final byte[] getCS() {
		return _CS.getDATA();
	}
}
