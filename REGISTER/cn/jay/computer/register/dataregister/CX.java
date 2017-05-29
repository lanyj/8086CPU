package cn.jay.computer.register.dataregister;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.utilexception.CopyArrayException;

public class CX extends BaseRegister {
	public final static CX _CX = new CX();

	public static final void setCX(byte[] data) throws CopyArrayException {
		_CX.setDATA(data);
	}
	public static final byte[] getCX() {
		return _CX.getDATA();
	}
	public static final void setCH(byte[] data) throws CopyArrayException {
		_CX.setHIGH(data);
	}
	public static final byte[] getCH() {
		return _CX.getHIGH();
	}
	public static final void setCL(byte[] data) throws CopyArrayException {
		_CX.setLOW(data);
	}
	public static final byte[] getCL() {
		return _CX.getLOW();
	}
}
