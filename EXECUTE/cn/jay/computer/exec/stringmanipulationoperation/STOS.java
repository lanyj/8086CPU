package cn.jay.computer.exec.stringmanipulationoperation;

import cn.jay.computer.exec.Execution;
import cn.jay.computer.memory.Memoryer;
import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.flagregister.FLAGS;
import cn.jay.computer.register.indexregister.DI;
import cn.jay.computer.register.segmentregister.ES;

public class STOS extends Execution {
	public STOS(String opcode, String operand, String describle, int index) {
		super(opcode, operand, describle, index);
	}

	public void exec() {
		int conn = getIndex();
		switch (conn) {
		case -1: {
			break;
		}
		case 0: {
			boolean W = getOperand("W").equals("1");

			byte[] src = null;
			if (W) {
				src = AX.getAX();
			} else {
				src = AX.getAL();
			}

			Memoryer.write(DI.getDI(), ES.getES(), src, W);

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
