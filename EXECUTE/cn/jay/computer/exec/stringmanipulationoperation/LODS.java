package cn.jay.computer.exec.stringmanipulationoperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.baseregister.BaseRegister;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.indexregister.DI;
import cn.jay.computer.register.indexregister.SI;

public class LODS extends Execution {
	public LODS(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			BaseRegister env = Environment.getDataSegment();
			
			boolean W = getOperand("W").equals("1");

			byte[] src = null;
			byte[] des = Memoryer.read(SI.getSI(), env.getDATA(), W);
			try {
				if (W) {
					src = AX.getAX();
					LongALU.sub16(src, des);
				} else {
					src = AX.getAL();
					LongALU.sub8(src, des);
				}
			} catch (Exception e) {
			}

			boolean df = FLAGS.getFLAGS(FLAGS.DF);
			if (df) {
				if (W) {
					DI._DI.dec();
				}
				DI._DI.dec();
			} else {
				if (W) {
					DI._DI.inc();
				}
				DI._DI.inc();
			}

			break;
		}
		}
	}

}
