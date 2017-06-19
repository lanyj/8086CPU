package cfg;

public class Configer {
	public static final long getBaseCLKDelay() {
		return 1;
	}
	public static final int getRegisterSize() {
		return 16;
	}
	public static final int getInstructionLength() {
		return 16;
	}
	public static final String getCPUConnectHost() {
		return "127.0.0.1";
	}
	public static final int getCPUConnectPort() {
		return 8956;
	}
	public static final int getCPUPortCount() {
		return 40;
	}
}
