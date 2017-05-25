package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.segmentregister.SS;

public class PUSH extends Execution {

	public PUSH(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			BIU.getInstruction();
			
			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");
			
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);
			
			SP.sub2();
			
			if(addr == null) {
				try {
					Memoryer.write(SP.getSP(), SS.getSS(), RegisterMgr.getDATA(RM, true), true);
				} catch (Exception e) {
				}
			} else {
				Memoryer.write(SP.getSP(), SS.getSS(), Memoryer.read(addr, Environment.getDataSegment().getDATA(), true), true);
			}
			
			break;
		}
		case 1:{
			String REG = getOperand("REG");
			
			SP.sub2();
			try {
				Memoryer.write(SP.getSP(), SS.getSS(), RegisterMgr.getDATA(REG, true), true);
			} catch (Exception e) {
			}
			break;
		}
		case 2:{
			String SEG = getOperand("SEG");
			
			SP.sub2();
			try {
				Memoryer.write(SP.getSP(), SS.getSS(), RegisterMgr.getDATA(SEG, true), true);
			} catch (Exception e) {
			}
			break;
		}
		}
	}

}