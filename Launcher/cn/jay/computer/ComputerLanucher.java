package cn.jay.computer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cn.jay.computer.clk.CLK;
import cn.jay.computer.eu.EU;
import cn.jay.computer.memory.MemoryManager;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;
import cn.jay.computer.utilexception.CopyArrayException;
import cn.jay.modelprovider.ModelMgr;

public class ComputerLanucher {
	static {
		try{
			Class.forName("cn.jay.io_bridge.IOBridge");
			Class.forName("cn.jay.computer.port.PortMgr");
			
			Class.forName("cn.jay.computer.eu.EU");
			Class.forName("cn.jay.computer.eu.Environment");

			Class.forName("cfg.Configer");
			Class.forName("cn.jay.modelprovider.ModelMgr");
			
			Class.forName("cn.jay.computer.exec.ExecuterMgr");
			Class.forName("cn.jay.computer.exec.RM_MOD_Analyzer");
			
			Class.forName("cn.jay.computer.alu.MathUtils");
			Class.forName("cn.jay.computer.alu.LongALU");
			Class.forName("cn.jay.computer.arrayutils.Utils");
			
			Class.forName("cn.jay.computer.biu.BIU");
			
			Class.forName("cn.jay.computer.register.baseregister.RegisterMgr");
			Class.forName("cn.jay.computer.register.flagregister.FLAGS");
			Class.forName("cn.jay.computer.register.ipregister.IP");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		new MemoryManager("MAIN_MEMORY", 0x00ffffff).deploy();
	}
	
	public static void start() {
		ModelMgr.getCLK(1).registerTask(EU.JOB);

		Set<Entry<Integer, CLK>> clk = ModelMgr.CLK_POOL.entrySet();
		for (Iterator<Entry<Integer, CLK>> itr = clk.iterator(); itr.hasNext();) {
			itr.next().getValue().open();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		MemoryManager mm = (MemoryManager) ModelMgr.getIOModel("MAIN_MEMORY");
		
		try {
			CS.setCS(new byte[16]);
			IP.setIP(2 * 256);
		} catch (CopyArrayException e) {
		}
		mm.test(System.getProperty("user.dir") + "/TEST.EXE");
		
		start();
	}

}
