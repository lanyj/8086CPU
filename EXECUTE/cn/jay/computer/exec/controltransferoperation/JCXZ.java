package cn.jay.computer.exec.controltransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.CX;
import cn.jay.computer.register.ipregister.IP;

public class JCXZ extends Execution {

	private static final byte[] TWO16 = new byte[16];
	
	static {
		TWO16[1] = 1;
	}
	
	public JCXZ(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			if(CX._CX.isZero()) {
				return;
			}
			byte[] ip = BIU.getInstruction();
			IP.setIP(IP.getIPLongValue() + byteArrayToLong(ip, true, 8));
			break;
		}
		}
	}

}
