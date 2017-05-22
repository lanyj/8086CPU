package cn.jay.computer.program;

public class Program {
	private byte[] codes;

	public Program(byte[] codes) {
		this.codes = codes;
	}

	public long getSize() {
		return codes.length;
	}

	public byte[] getCodes() {
		return codes;
	}

}
