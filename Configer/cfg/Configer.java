package cfg;

import java.util.HashMap;

import cn.jay.computer.clk.CLK;
import cn.jay.computer.memory.MemoryManager;

public class Configer {
	public static final long BASE_CLK_DELAY = 1000;
	public static final MemoryManager MEMORY_MANAGER = new MemoryManager(10240000);
	public static final HashMap<Integer, CLK> CLK_POOL = new HashMap<>();
	
	/**
	 * 
	 * @param mul delay = mul * {@link BASE_CLK_DELAY}
	 * @return
	 */
	public static final CLK getCLK(float mul) {
		if(mul < 1 || mul > Integer.MAX_VALUE / 200) {
			return null;
		}
		int mills = (int) (mul * BASE_CLK_DELAY);
		if(!CLK_POOL.containsKey(mills)) {
			CLK_POOL.put(mills, new CLK(mills));
		}
		return CLK_POOL.get(mills);
	}
	
	public static final MemoryManager getMeomryManager() {
		return MEMORY_MANAGER;
	}
	
	public static final int getRegisterSize() {
		return 16;
	}
	public static final int getInstructionLength() {
		return 16;
	}
	public static final String getCPUConnectHost() {
		return "127.0.0.1";
	}
	public static final int getCPUConnectPort() {
		return 8000;
	}
	public static final int getCPUPortCount() {
		return 40;
	}
}
