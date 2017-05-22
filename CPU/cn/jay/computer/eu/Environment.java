package cn.jay.computer.eu;

import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.segmentregister.CS;
import cn.jay.computer.register.segmentregister.DS;

public class Environment {
	public static BaseRegister DATA_SEGMENT = DS._DS;

	public static void setDataSegment(BaseRegister seg) {
		if(DATA_SEGMENT != DS._DS) {
			return;
		}
		DATA_SEGMENT = seg;
	}

	public static BaseRegister getDataSegment() {
		BaseRegister ret = DATA_SEGMENT;
		DATA_SEGMENT = DS._DS;
		return ret;
	}
	
	public static BaseRegister CODE_SEGMENT = CS._CS;

	public static void setCodeSegment(BaseRegister seg) {
		if(CODE_SEGMENT != CS._CS) {
			return;
		}
		CODE_SEGMENT = seg;
	}

	public static BaseRegister getCodeSegment() {
		BaseRegister ret = CODE_SEGMENT;
		CODE_SEGMENT = CS._CS;
		return ret;
	}
}
