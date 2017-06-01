package cn.jay.computer.register.dataregister;

import cn.jay.computer.arrayutils.CopyArrayException;
import cn.jay.computer.register.baseregister.BaseRegister;

public class AX extends BaseRegister {
	public final static AX _AX = new AX();
	public static final void setAX(byte[] data) throws CopyArrayException {
		_AX.setDATA(data);
	}
	public static final byte[] getAX() {
		return _AX.getDATA();
	}
	public static final void setAH(byte[] data) throws CopyArrayException {
		_AX.setHIGH(data);
	}
	public static final byte[] getAH() {
		return _AX.getHIGH();
	}
	public static final void setAL(byte[] data) throws CopyArrayException {
		_AX.setLOW(data);
	}
	public static final byte[] getAL() {
		return _AX.getLOW();
	}
}
