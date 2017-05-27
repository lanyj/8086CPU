package cn.jay.computer.exec.datatransferoperation;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.driver.DriverMgr;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.DX;

public class OUT extends Execution {

	public OUT(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0:{
			byte[] port = BIU.getInstruction();
			
			boolean W = getOperand("W").equals("1");
			
			if(W) {
				DriverMgr.write(port, AX.getAX());
			} else {
				DriverMgr.write(port, AX.getAL());
			}
			
			break;
		}
		case 1:{
			byte[] port = DX.getDX();
			
			boolean W = getOperand("W").equals("1");
			
			if(W) {
				DriverMgr.write(port, AX.getAX());
			} else {
				DriverMgr.write(port, AX.getAL());
			}
			break;
		}
		}
	}

}
