package cn.jay.computer.eu;

import cn.jay.computer.biu.BIU;
import cn.jay.computer.clk.Job;
import cn.jay.computer.exec.ExecuterMgr;
import cn.jay.computer.exec.SegmentChanger;
import cn.jay.computer.exec.datatransferoperation.MOV;

public class EU {
	
	public static final EU_JOB JOB = new EU_JOB();

	static{
		new SegmentChanger();
		new MOV();
	}
	
}
class EU_JOB extends Job {

	@Override
	public void doJob() {
		byte[] ins = BIU.getInstruction();
		ExecuterMgr.exec(ins);
	}
	
}