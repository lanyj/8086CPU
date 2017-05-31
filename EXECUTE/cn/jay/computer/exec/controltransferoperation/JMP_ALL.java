package cn.jay.computer.exec.controltransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.ipregister.IP;

public class JMP_ALL extends Execution {

	private static final byte[] TWO16 = new byte[16];
	
	static {
		TWO16[1] = 1;
	}
	
	public JMP_ALL(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] ip = BIU.getInstruction();
			
			String index = getOperand("D") + getOperand("REG");
			boolean p = JMP_Analyzer.canJmp(index);
			
			System.out.println("jmp_all_test" + index + " - " + p);
			if(!p) {
				return;
			}
			
			IP.setIP(IP.getIPLongValue() + byteArrayToLong(ip, true, 8));
			break;
		}
		}
	}

}
