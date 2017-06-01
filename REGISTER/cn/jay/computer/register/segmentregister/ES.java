package cn.jay.computer.register.segmentregister;

import cn.jay.computer.arrayutils.CopyArrayException;
import cn.jay.computer.register.baseregister.BaseRegister;

public class ES extends BaseRegister {
	public static final ES _ES = new ES();
	
	public static final void setES(byte[] data) throws CopyArrayException {
		_ES.setDATA(data);
	}
	public static final byte[] getES() {
		return _ES.getDATA();
	}
}
