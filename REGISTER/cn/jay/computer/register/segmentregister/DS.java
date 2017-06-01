package cn.jay.computer.register.segmentregister;

import cn.jay.computer.arrayutils.CopyArrayException;
import cn.jay.computer.register.baseregister.BaseRegister;

public class DS extends BaseRegister {
	public static final DS _DS = new DS();
	
	public static final void setDS(byte[] data) throws CopyArrayException {
		_DS.setDATA(data);
	}
	public static final byte[] getDS() {
		return _DS.getDATA();
	}
}
