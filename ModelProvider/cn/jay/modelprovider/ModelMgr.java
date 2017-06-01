package cn.jay.modelprovider;

import java.util.HashMap;

import cn.jay.computer.clk.Job;
import cn.jay.computer.tcp.Client;

public class ModelMgr {
	public static final HashMap<String, Client> IO_MODELS = new HashMap<>();
	
	public static final boolean addIO_Model(Client ioModel) {
		return IO_MODELS.put(ioModel.getModelName(), ioModel) != null;
	}
	
	public static final Client removeIO_Model(String modelName) {
		return IO_MODELS.remove(modelName);
	}
	
	public static final Client getIOModel(String modelName) {
		return IO_MODELS.get(modelName);
	}
	
	public static final HashMap<String, Job> CLK_JOB_LIST = new HashMap<>();
	
	public static final Job addCLKJob(Job job) {
		return CLK_JOB_LIST.put(job.getModelName(), job);
	}
	
	public static final Job removeCLKJob(String modelName) {
		return CLK_JOB_LIST.remove(modelName);
	}
	
	public static final Job getCLKJob(String modelName) {
		return CLK_JOB_LIST.get(modelName);
	}
	
}
