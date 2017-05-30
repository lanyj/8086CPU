package cn.jay.computer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cfg.Configer;
import cn.jay.computer.clk.CLK;
import cn.jay.computer.eu.EU;
import cn.jay.computer.memory.MemoryManager;
import cn.jay.computer.register.ipregister.IP;
import cn.jay.computer.register.segmentregister.CS;
import cn.jay.computer.utilexception.CopyArrayException;

public class ComputerLanucher {
	public static void start() {
		Configer.getCLK(1).registerTask(EU.JOB);

		Set<Entry<Integer, CLK>> clk = Configer.CLK_POOL.entrySet();
		for (Iterator<Entry<Integer, CLK>> itr = clk.iterator(); itr.hasNext();) {
			itr.next().getValue().open();
		}
	}
	
	
	public static void main(String[] args) throws Exception {
		MemoryManager mm = Configer.getMeomryManager();
		try {
			CS.setCS(new byte[16]);
			IP.setIP(2 * 256);
		} catch (CopyArrayException e) {
		}
		mm.test(System.getProperty("user.dir") + "/COMPUT~1.EXE");
		
		start();
	}

}
