package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.segmentregister.SS;

public class POP extends Execution {

	public POP(String opcode, String operand, String describle, int index) {
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
			
			if(addr == null) {
				try {
					RegisterMgr.setDATA(RM, true, Memoryer.read(SP.getSP(), SS.getSS(), true));
				} catch (Exception e) {
				}
			} else {
				Memoryer.write(addr, Environment.getDataSegment().getDATA(), Memoryer.read(SP.getSP(), SS.getSS(), true), true);
			}
			
			SP.add2();
			
			break;
		}
		case 1:{
			String REG = getOperand("REG");
			
			try {
				RegisterMgr.setDATA(REG, true, Memoryer.read(SP.getSP(), SS.getSS(), true));
			} catch (Exception e) {
			}
			
			SP.add2();
			
			break;
		}
		case 2:{
			String SEG = getOperand("SEG");
			
			try {
				RegisterMgr.setDATA(SEG, true, Memoryer.read(SP.getSP(), SS.getSS(), true));
			} catch (Exception e) {
			}
			
			SP.add2();
			
			break;
		}
		}
	}

}
