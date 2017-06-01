package cn.jay.computer.register.indexregister;

import cn.jay.computer.arrayutils.CopyArrayException;
import cn.jay.computer.register.baseregister.BaseRegister;

public class DI extends BaseRegister {
	public static final DI _DI = new DI();
	
	public static final void setDI(byte[] data) throws CopyArrayException {
		_DI.setDATA(data);
	}
	public static final byte[] getDI() {
		return _DI.getDATA();
	}
}
