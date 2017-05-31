package cn.jay.modelprovider;

import java.util.HashMap;

import cfg.Configer;
import cn.jay.computer.clk.CLK;
import cn.jay.computer.tcp.Client;

public class ModelMgr {
	public static final HashMap<String, Client> IO_MODELS = new HashMap<>();
	
	public static final HashMap<Integer, CLK> CLK_POOL = new HashMap<>();
	
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
	
	public static final boolean addIO_Model(Client ioModel) {
		System.out.println(ioModel.getModelName() + " - - - - " + ioModel + " - - -- - " + IO_MODELS);
		return IO_MODELS.put(ioModel.getModelName(), ioModel) != null;
	}
	
	public static final Client getIOModel(String modelName) {
		return IO_MODELS.get(modelName);
	}
	
}
