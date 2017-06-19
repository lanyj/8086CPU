package cn.jay.computer.exec.stringmanipulationoperation;

import cn.jay.computer.alu.LongALU;
import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.indexregister.DI;
import cn.jay.computer.register.segmentregister.ES;

public class SCAS extends Execution {
	public SCAS(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() throws Exception {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			boolean W = getOperand("W").equals("1");

			byte[] src = null;
			byte[] des = Memoryer.read(DI.getDI(), ES.getES(), W);
			if (W) {
				src = AX.getAX();
				LongALU.sub(src, des);
			} else {
				src = AX.getAL();
				LongALU.sub(src, des);
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
