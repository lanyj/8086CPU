package cn.jay.computer.clk;

import java.util.Date;
import java.util.TimerTask;

import cn.jay.modelprovider.ModelMgr;
import modelinterface.ModelInterface;

public abstract class Job extends TimerTask implements ModelInterface {
	private boolean alive = false;
	private String modelName = new Date().toString();
	
	private long value = 1;
	
	public Job(String modelName, long value) {
		this.modelName = modelName;
		this.value = value;
	}
	
	public final void deploy() {
		alive = true;
		ModelMgr.addModel(this);
		
		CLK.registerTask(this);
	}
	
	public final void undeploy() {
		alive = false;
		CLK.removeTask(this);
	}
	
	public abstract void doJob();
	
	public final void setValue(long value) {
		this.value = value;
	}
	
	public final long getValue() {
		return this.value;
	}
	
	@Override
	public void run() {
		doJob();
	}

	public void setModelName(String name) {
		this.modelName = name;
	}
	
	public String getModelName() {
		return this.modelName;
	}
	
	public boolean isRunning() {
		return alive;
	}
	
}
