package cn.jay.computer.eu;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.clk.Job;
import cn.jay.computer.exec.ExecuterMgr;

public class EU {
	
	public static final EU_JOB JOB = new EU_JOB();

}
class EU_JOB extends Job {

	@Override
	public void doJob() {
		System.out.println("Load next");
		byte[] ins = BIU.getInstruction();
		ExecuterMgr.exec(ins, BIU.peekInstruction());
	}
	
}