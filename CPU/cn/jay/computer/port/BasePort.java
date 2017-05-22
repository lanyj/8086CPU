package cn.jay.computer.port;

import java.util.ArrayList;

public class BasePort {
	
	private byte value = 0;
	
	/**
	 * 0 - no change
	 * 1 - 0 -> 1
	 * -1 - 1 -> 0
	 */
	private byte state = 0;

	private ArrayList<Job> jobs_up = new ArrayList<Job>();
	private ArrayList<Job> jobs_down = new ArrayList<Job>();
	
	private ArrayList<Job> jobs_high = new ArrayList<Job>();
	private ArrayList<Job> jobs_low = new ArrayList<Job>();
	
	public void setValue(byte value) {
		value = (byte) (value > 0 ? 1 : (value < 0 ? -1 : 0));
		this.value = value;
		if(value == this.value) {
			state = 0;
		}
		if(value > this.value) {
			state = 1;
			for(Job j : jobs_up) {
				if(!j.isAlive())
					j.start();
			}
		}
		if(value < this.value) {
			state = -1;
			for(Job j : jobs_down) {
				if(!j.isAlive())
					j.start();
			}
		}
		if(value == 1) {
			for(Job j : jobs_high) {
				if(!j.isAlive())
					j.start();
			}
		}
		if(value == 0) {
			for(Job j : jobs_low) {
				if(!j.isAlive())
					j.start();
			}
		}
	}
	
	public byte getValue() {
		return value;
	}
	
	public byte getState() {
		return state;
	}
	
	public boolean registerUp(Job j) {
		return jobs_up.add(j);
	}
	
	public boolean registerDown(Job j) {
		return jobs_down.add(j);
	}
	
	public boolean registerHigh(Job j) {
		return jobs_high.add(j);
	}
	
	public boolean registerLow(Job j) {
		return jobs_low.add(j);
	}
	
}
