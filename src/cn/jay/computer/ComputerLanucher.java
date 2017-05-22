package cn.jay.computer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cn.jay.computer.clk.CLK;
import cn.jay.computer.eu.EU;
import registercfg.Configer;

public class ComputerLanucher {
	static {
		Configer.getCLK(1).registerTask(EU.JOB);
		
		Set<Entry<Integer, CLK>>  clk = Configer.CLK_POOL.entrySet();
		for(Iterator<Entry<Integer, CLK>>  itr = clk.iterator();itr.hasNext();) {
			itr.next().getValue().open();
		}
	}
	
	public static void main(String[] args) {
		
	}
	
}
