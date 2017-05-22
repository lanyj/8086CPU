package cn.jay.computer.register.segmentregister;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class CS extends BaseRegister {
	public static final CS _CS = new CS();
	
	public static final void setCS(byte[] data) throws CopyArrayException {
		_CS.setDATA(data);
	}
	public static final byte[] getCS() {
		return _CS.getDATA();
	}
}
