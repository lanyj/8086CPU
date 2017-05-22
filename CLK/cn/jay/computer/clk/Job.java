package cn.jay.computer.clk;

import java.util.TimerTask;

public abstract class Job extends TimerTask {
	private Counter counter = null;
	public void setCounter(Counter counter) {
		this.counter = counter;
	}
	public abstract void doJob();
	
	@Override
	public void run() {
		counter.add();
		doJob();
		counter.sub();
	}
	
}
