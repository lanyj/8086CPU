package cn.jay.computer.register.indexregister;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class BP extends BaseRegister {
	public static final BP _BP = new BP();
	
	public static final void setBP(byte[] data) throws CopyArrayException {
		_BP.setDATA(data);
	}
	public static final byte[] getBP() {
		return _BP.getDATA();
	}
}
