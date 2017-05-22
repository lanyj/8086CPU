package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.AbstractExecuter;
import cn.jay.computer.exec.ExecuterMgr;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.RegisterMgr;

public class MOV extends AbstractExecuter {

	private static final byte[][] FEATURE = 
		{{(byte) 0x88,6},
		 {(byte) 0x8E,7},
		 {(byte) 0xB0,4},
		 {(byte) 0xA0,7},
		 {(byte) 0xA2,7},
		 {(byte) 0x8E,8},
		 {(byte) 0x8C,8}};
	
	public MOV() {
		ExecuterMgr.addExecuter(this);
		setFeature(FEATURE);
	}
	
	public void exec(byte code) {
		int conn = isSame(code);
		switch (conn) {
		case -1:{
			break;
		}
		case 0:{
			byte c2 = arrayToByte(BIU.getInstruction());
			boolean D = (code & 0x02) > 0;
			boolean W = (code & 0x01) > 0;

			String MOD = getString(c2, 7, 2, false);
			String REG = getString(c2, 5, 3, false);
			String RM = getString(c2, 2, 3, false);
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, W);
			
			try {
				Memoryer.generateMemoryAddress(addr, addr, Environment.getDataSegment().getDATA());
			} catch (Exception e) {
			}
			
			if(D) {
				try {
					RegisterMgr.setDATA(REG, W, Memoryer.read(addr, W));
				} catch (Exception e) {
				}
			} else {
				try {
					Memoryer.write(addr, RegisterMgr.getDATA(REG, W), W);
				} catch (Exception e) {
				}
			}
			
			break;
		}
		}
	}
	
}
