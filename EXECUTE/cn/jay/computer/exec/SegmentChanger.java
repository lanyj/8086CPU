package cn.jay.computer.exec;

import cn.jay.computer.eu.Environment;
import cn.jay.computer.register.segmentregister.CS;
import cn.jay.computer.register.segmentregister.DS;
import cn.jay.computer.register.segmentregister.ES;
import cn.jay.computer.register.segmentregister.SS;

public class SegmentChanger extends AbstractExecuter {

	private static final byte[][] FEATURE = { { (byte) 0x26, 8 }, { (byte) 0x2E, 8 }, { (byte) 0x36, 8 },
			{ (byte) 0x3E, 8 } };

	public SegmentChanger() {
		ExecuterMgr.addExecuter(this);
		setFeature(FEATURE);
	}

	public void exec(byte code) {
		int conn = isSame(code);
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			Environment.setDataSegment(ES._ES);
			break;
		}
		case 1: {
			Environment.setDataSegment(CS._CS);
			break;
		}
		case 2: {
			Environment.setDataSegment(SS._SS);
			break;
		}
		case 3: {
			Environment.setDataSegment(DS._DS);
			break;
		}
		}
	}

}
