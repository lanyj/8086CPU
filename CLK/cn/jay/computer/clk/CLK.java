package cn.jay.computer.clk;

import java.util.ArrayList;

import cfg.Configer;

public class CLK extends Thread {
	private static long times = 0;
	
	private static ArrayList<Job> tasks = new ArrayList<>();
	
	private static long delay = Configer.getBaseCLKDelay();
	private static boolean alive = false;
	
	private static CLK instance = new CLK();
	
	protected static boolean registerTask(Job task) {
		if(tasks.contains(task)) {
			return false;
		}
		tasks.add(task);
		return true;
	}
	
	protected static boolean removeTask(Job job) {
		return tasks.remove(job);
	}
	
	public static void open() {
		times = 0;
		if(!alive) {
			alive = true;
			instance.start();
		} else {
			return;
		}
	}
	
	public static void close() {
		alive = false;
	}
	
	public void run() {
		while(alive) {
			times++;
			for(Job t : tasks) {
				if(times % t.getValue() == 0) {
					new Thread(t).start();
				}
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			}
		}
	}
	
}