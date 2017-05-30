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
import cn.jay.modelprovider.Provider;

public class ComputerLanucher {
	static {
		try{
			Class.forName("cn.jay.computer.port.PortMgr");
			Class.forName("cn.jay.computer.eu.EU");
			Class.forName("cn.jay.computer.eu.Environment");
			Class.forName("cfg.Configer");
			Class.forName("cn.jay.modelprovider.Provider");
			
			Class.forName("cn.jay.computer.exec.ExecuterMgr");
			Class.forName("cn.jay.computer.alu.LongALU");
			
			Class.forName("");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public static void start() {
		Provider.getCLK(1).registerTask(EU.JOB);

		Set<Entry<Integer, CLK>> clk = Provider.CLK_POOL.entrySet();
		for (Iterator<Entry<Integer, CLK>> itr = clk.iterator(); itr.hasNext();) {
			itr.next().getValue().open();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		MemoryManager mm = Provider.getMeomryManager();
		try {
			CS.setCS(new byte[16]);
			IP.setIP(2 * 256);
		} catch (CopyArrayException e) {
		}
		mm.test(System.getProperty("user.dir") + "/TEST.EXE");
		
		start();
	}

}
