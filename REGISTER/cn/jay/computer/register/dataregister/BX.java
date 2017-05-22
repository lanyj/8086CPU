package cn.jay.computer.register.dataregister;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class BX extends BaseRegister {
	public final static BX _BX = new BX();
	public static final void setBX(byte[] data) throws CopyArrayException {
		_BX.setDATA(data);
	}
	public static final byte[] getBX() {
		return _BX.getDATA();
	}
	public static final void setBH(byte[] data) throws CopyArrayException {
		_BX.setHIGH(data);
	}
	public static final byte[] getBH() {
		return _BX.getHIGH();
	}
	public static final void setBL(byte[] data) throws CopyArrayException {
		_BX.setLOW(data);
	}
	public static final byte[] getBL() {
		return _BX.getLOW();
	}
}
