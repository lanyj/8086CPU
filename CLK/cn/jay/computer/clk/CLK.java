package cn.jay.computer.clk;

import java.util.ArrayList;
import java.util.TimerTask;

public class CLK extends Thread {
	protected Counter COUNTER = new Counter();
	
	private ArrayList<Job> tasks = new ArrayList<>();
	private long delay = 0;
	private boolean alive = false;
	
	public CLK(long delay) {
		this.delay = delay;
	}
	
	public void registerTask(Job task) {
		tasks.add(task);
		task.setCounter(COUNTER);
	}
	
	public void open() {
		alive = true;
		run();
	}
	
	public void close() {
		alive = false;
	}
	
	public void run() {
		while(alive) {
			synchronized (COUNTER) {
				if(!COUNTER.isZero()) {
					continue;
				}
			}
			for(TimerTask t : tasks) {
				new Thread(t).start();
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
		}
	}
	
}
class Counter {
	private Object locker = new Object();
	private int value = 0;

	public void add() {
		synchronized (locker) {
			value++;
		}
	}
	public void sub() {
		synchronized (locker) {
			value--;
		}
	}
	public boolean isZero() {
		synchronized (locker) {
			return value == 0;
		}
	}
}