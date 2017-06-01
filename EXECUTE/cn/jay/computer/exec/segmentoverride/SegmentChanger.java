package cn.jay.computer.exec.segmentoverride;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.ExecuterMgr;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.segmentregister.CS;
import cn.jay.computer.register.segmentregister.DS;
import cn.jay.computer.register.segmentregister.ES;
import cn.jay.computer.register.segmentregister.SS;

public class SegmentChanger extends Execution {

	public SegmentChanger(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec(byte code) throws Exception {
		int conn = getIndex();

		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			String SEG = getOperand("SEG");
			switch (SEG) {
			case "00": {
				Environment.setDataSegment(ES._ES);
				break;
			}
			case "01": {
				Environment.setDataSegment(CS._CS);
				break;
			}
			case "10": {
				Environment.setDataSegment(SS._SS);
				break;
			}
			case "11": {
				Environment.setDataSegment(DS._DS);
				break;
			}
			}
			ExecuterMgr.exec(BIU.getInstruction(), BIU.peekInstruction());
		}
		}
	}

}
