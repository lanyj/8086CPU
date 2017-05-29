package cn.jay.computer.exec.stringmanipulationoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.exec.ExecuterMgr;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.CX;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.ipregister.IP;

public class REP extends Execution {
	public REP(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] c2 = BIU.getInstruction();

			boolean Z = getOperand("D").equals("1");
			boolean zf = FLAGS.getFLAGS(FLAGS.ZF) && Z;
			
			if(CX._CX.isZero() || zf) {
				return;
			}
			CX._CX.dec();
			long ip = IP.getIPLongValue();
			IP.setIP(ip - 2);
			ExecuterMgr.exec(c2, c2);
			
			break;
		}
		}
	}

}
