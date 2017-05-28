package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.dataregister.AX;

public class XCHG extends Execution {

	public XCHG(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BIU.getInstruction();

			BaseRegister env = Environment.getDataSegment();
			
			boolean W = getOperand("W").equals("1");

			String MOD = getOperand("MOD");
			String REG = getOperand("REG");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);
			try {
				if(addr == null) {
					byte[] v1 = RegisterMgr.getDATA(REG, W);
					byte[] v2 = RegisterMgr.getDATA(RM, W);
					
					try {
						RegisterMgr.setDATA(REG, W, v2);
						RegisterMgr.setDATA(RM, W, v1);
					} catch (Exception e) {
					}
				} else {
					byte[] v1 = RegisterMgr.getDATA(REG, W);
					byte[] v2 = Memoryer.read(addr, env.getDATA(), W);
					
					Memoryer.write(addr, env.getDATA(), v1, W);
					RegisterMgr.setDATA(REG, W, v2);
				}
			}catch (Exception e) {
			}
			break;
		}
		case 1:{
			String REG = getOperand("REG");
			try {
				byte[] v1 = RegisterMgr.getDATA(REG, true);
				byte[] v2 = AX.getAX();
				
				AX.setAX(v1);
				RegisterMgr.setDATA(REG, true, v2);				
			}catch (Exception e) {
			}
			break;
		}
		}
	}

}
