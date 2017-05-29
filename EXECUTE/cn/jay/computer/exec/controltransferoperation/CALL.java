package cn.jay.computer.exec.controltransferoperation;

import java.util.Arrays;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.exec.RM_MOD_Analyzer;
import cn.jay.computer.exec.datatransferoperation.PUSH;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.baseregister.RegisterMgr;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;

public class CALL extends Execution {

	private static final byte[] TWO16 = new byte[16];

	static {
		TWO16[1] = 1;
	}

	public CALL(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		
		System.out.println( "\nCALL - - - " + Arrays.toString(CS.getCS()) + ":" + IP.getIPLongValue());
//		System.out.println(this);
		
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			byte[] low = BIU.getInstruction();
			byte[] high = BIU.getInstruction();

			byte[] ip = arrayConcat(low, high);

			long disp = byteArrayToLong(ip, true, 16);

			PUSH.push(IP.getIP());
			
			IP.setIP(IP.getIPLongValue() + disp);
			break;
		}
		case 1: {
			BaseRegister env = Environment.getDataSegment();
			BIU.getInstruction();

			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);

			PUSH.push(IP.getIP());
			
			if (addr == null) {
				IP.setIP(IP.getIPLongValue() + byteArrayToLong(RegisterMgr.getDATA(RM, true), true, 16));
			} else {
				IP.setIP(IP.getIPLongValue() + byteArrayToLong(Memoryer.read(addr, env.getDATA(), true), true, 16));
			}
			break;
		}
		case 2: {
			byte[] low = BIU.getInstruction();
			byte[] high = BIU.getInstruction();

			byte[] ip = arrayConcat(low, high);

			low = BIU.getInstruction();
			high = BIU.getInstruction();

			byte[] cs = arrayConcat(low, high);

			PUSH.push(CS.getCS());
			PUSH.push(IP.getIP());
			
			IP.setIP(IP.getIPLongValue() + byteArrayToLong(ip, true, 16));
			CS.setCS(cs);
			break;
		}
		case 3: {
			BaseRegister env = Environment.getDataSegment();
			BIU.getInstruction();
			
			String MOD = getOperand("MOD");
			String RM = getOperand("R/M");
			byte[] addr = RM_MOD_Analyzer.analyze(MOD, RM, true);

			PUSH.push(CS.getCS());
			PUSH.push(IP.getIP());
			
			if (addr == null) {
			} else {
				byte[] ip = Memoryer.read(addr, env.getDATA(), true);

				byte[] cs = Memoryer.read(add16(addr, TWO16), env.getDATA(), true);

				IP.setIP(IP.getIPLongValue() + byteArrayToLong(ip, true, 16));
				CS.setCS(cs);
			}
			break;
		}
		}
	}

}
