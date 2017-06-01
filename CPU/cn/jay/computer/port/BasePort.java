package cn.jay.computer.port;

public class BasePort {
	
	private byte value = 0;
	
	/**
	 * 0 - no change
	 * 1 - 0 -> 1
	 * -1 - 1 -> 0
	 */
	private byte state = 0;

	public void setValue(byte value) {
		value = (byte) (value > 0 ? 1 : (value < 0 ? -1 : 0));
		this.value = value;
		if(value == this.value) {
			state = 0;
		}
		if(value > this.value) {
			state = 1;
		}
		if(value < this.value) {
			state = -1;
		}
	}
	
	public byte getValue() {
		return value;
	}
	
	public byte getState() {
		return state;
	}
	
}
