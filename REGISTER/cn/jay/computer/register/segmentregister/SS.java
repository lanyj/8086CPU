package cn.jay.computer.register.segmentregister;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class SS extends BaseRegister {
	public static final SS _SS = new SS();
	
	public static final void setSS(byte[] data) throws CopyArrayException {
		_SS.setDATA(data);
	}
	public static final byte[] getSS() {
		return _SS.getDATA();
	}
}
