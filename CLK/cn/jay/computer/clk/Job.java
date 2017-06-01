package cn.jay.computer.clk;

import java.util.Date;
import java.util.TimerTask;

import cn.jay.modelprovider.ModelMgr;

public abstract class Job extends TimerTask {
	
	private String modelName = new Date().toString();
	
	private long value = 1;
	
	public Job(String modelName, long value) {
		this.modelName = modelName;
		this.value = value;
	}
	
	public boolean deploy() {
		ModelMgr.addCLKJob(this);
		
		return CLK.registerTask(this);
	}
	
	public boolean undeploy() {
		ModelMgr.removeCLKJob(modelName);
		
		return CLK.removeTask(this);
	}
	
	public abstract void doJob();
	
	public void setValue(long value) {
		this.value = value;
	}
	
	public long getValue() {
		return this.value;
	}
	
	@Override
	public void run() {
		doJob();
	}

	public String getModelName() {
		return this.modelName;
	}
	
}
