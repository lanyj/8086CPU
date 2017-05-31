package cn.jay.computer.eu;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.clk.Job;
import cn.jay.computer.exec.ExecuterMgr;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;

public class EU {

	public static final EU_JOB JOB = new EU_JOB();

}

class EU_JOB extends Job {

	@Override
	public void doJob() {
		System.out.println("\n**********EU : Current CS:IP = " + MathUtils.byteArrayToLong(CS.getCS(), false, 16)
				+ " : " + IP.getIPLongValue() + "**********\n");
		byte[] ins = BIU.getInstruction();
		ExecuterMgr.exec(ins, BIU.peekInstruction());
	}

}