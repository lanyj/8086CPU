package cn.jay.computer.eu;

import java.util.Arrays;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.clk.Job;
import cn.jay.computer.exec.ExecuterMgr;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;

public class EU {

	public static final EU_JOB JOB = new EU_JOB();

	public static void deploy() {
		JOB.deploy();
	}

	public static void undeploy() {
		JOB.undeploy();
	}

}

class EU_JOB extends Job {

	public EU_JOB() {
		super("EU_CONTROLLER", 1);
	}

	@Override
	public void doJob() {
		Execution exec = null;
		try {
//			System.out.println("\n**********EU : Current CS:IP = " + MathUtils.byteArrayToLong(CS.getCS(), false, 16)
//					+ " : " + IP.getIPLongValue() + "**********\n");
			byte[] ins = BIU.getInstruction();

			exec = ExecuterMgr.exec(ins, BIU.peekInstruction());
			if (exec == null) {
				throw new Exception("Unknown instruction : CS:IP = " + MathUtils.byteArrayToLong(CS.getCS(), false, 16)
						+ ":" + IP.getIPLongValue() + " -> " + Arrays.toString(ins)
						+ Arrays.toString(BIU.peekInstruction()));
			}
		} catch (Exception e) {
			System.out.println();
			System.out.println(exec + " - - - " + Arrays.toString(CS.getCS()) + ":" + IP.getIPLongValue());
			e.printStackTrace();
			System.out.println();
		}
	}

}