package cn.jay.computer.gui;

import cn.jay.computer.memory.MemoryManager;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.BX;
import cn.jay.computer.register.dataregister.CX;
import cn.jay.computer.register.dataregister.DX;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.indexregister.BP;
import cn.jay.computer.register.indexregister.DI;
import cn.jay.computer.register.indexregister.SI;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;
import cn.jay.computer.register.segmentregister.DS;
import cn.jay.computer.register.segmentregister.ES;
import cn.jay.computer.register.segmentregister.SS;
import cn.jay.modelprovider.ModelMgr;

public class DataProvider {
	public static final BaseRegister getRegister(String name) {
		switch (name) {
		case "AX":
			return AX._AX;
		case "BX":
			return BX._BX;
		case "CX":
			return CX._CX;
		case "DX":
			return DX._DX;
		case "SP":
			return SP._SP;
		case "SS":
			return SS._SS;
		case "SI":
			return SI._SI;
		case "DI":
			return DI._DI;
		case "BP":
			return BP._BP;
		case "CS":
			return CS._CS;
		case "DS":
			return DS._DS;
		case "ES":
			return ES._ES;
		default:
			break;
		}
		return null;
	}
	public static final long getIP() {
		return IP.getIPLongValue();
	}
	public static final byte[] getFlags() {
		return FLAGS.getFLAGS();
	}
	public static final String getString(byte[] val) {
		long b = 0;
		String ret = "";
		for (int i = 0; i < 8; i++) {
			b |= (val[i] << i);
		}
		long c = b & 0x0F;
		long d = (b & 0xF0) >> 4;
		if(d > 9) {
			ret += (char)(d + 'A');
		} else {
			ret += (char)(d + '0');
		}
		if(c > 9) {
			ret += (char)(c + 'A');
		} else {
			ret += (char)(c + '0');
		}
		return ret;
	}
	public static final String getMemString(long addr, long size) {
		StringBuffer sb = new StringBuffer();
		MemoryManager mm = (MemoryManager) ModelMgr.getIOModel("MAIN_MEMORY");
		for(int i = 0;i < size;i++) {
			byte[] value = mm.read(addr++);
			sb.append(getString(value));
		}
		return sb.toString();
	}
}
