package cn.jay.computer.register.indexregister;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class SP extends BaseRegister {
	public static final SP _SP = new SP();
	
	public static final void setSP(byte[] data) throws CopyArrayException {
		_SP.setDATA(data);
	}
	public static final byte[] getSP() {
		return _SP.getDATA();
	}
}
