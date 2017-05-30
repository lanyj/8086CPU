package cn.jay.modelprovider;

import java.util.HashMap;

import cfg.Configer;
import cn.jay.computer.clk.CLK;
import cn.jay.computer.memory.MemoryManager;

public class Provider {
	public static final HashMap<Integer, CLK> CLK_POOL = new HashMap<>();
	
	public static final MemoryManager MEMORY_MANAGER = new MemoryManager(0xfffffff);

	/**
	 * 
	 * @param mul
	 *            delay = mul * {@link BASE_CLK_DELAY}
	 * @return
	 */
	public static final CLK getCLK(float mul) {
		if (mul < 1 || mul > Integer.MAX_VALUE / 200) {
			return null;
		}
		int mills = (int) (mul * Configer.getBaseCLKDelay());
		if (!CLK_POOL.containsKey(mills)) {
			CLK_POOL.put(mills, new CLK(mills));
		}
		return CLK_POOL.get(mills);
	}
	
	public static final MemoryManager getMeomryManager() {
		return MEMORY_MANAGER;
	}
}
