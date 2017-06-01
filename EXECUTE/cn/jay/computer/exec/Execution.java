package cn.jay.computer.exec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.jay.computer.alu.MathUtils;

//64 bit
public class Execution {
	private static final String REGEX = "\\(([A-Z_a-z_0-9_/]*)\\)*";
	private static final Pattern PATTERN = Pattern.compile(REGEX);

	private String opcode;
	private String operand;
	private String describle;
	private int index;

	public List<String> operandName = new ArrayList<>();
	public List<String> operandValue = new ArrayList<>();

	public Execution(String opcode, String operand, String describle, int index) {
		this.opcode = opcode;
		this.operand = operand;
		this.describle = describle;
		this.index = index;
		
		init();
	}

	private void init() {
		Matcher matcher = PATTERN.matcher(operand);
		int end = operand.length();
		for (int i = 0; i < end; i = matcher.end()) {
			matcher.region(i, end);
			if (matcher.find())
				operandName.add(matcher.group());
		}
	}

	public boolean match(String c1, String c2) throws Exception {
		operandValue.clear();
		String cs = c1 + c2;
		String pat = "";
		for (int i = 0; i < opcode.length(); i++) {
			char c = opcode.charAt(i);
			char p = cs.charAt(i);
			if(c != '*' && c != p) {
				return false;
			}
			if(c == '*') {
				pat += p;
			}
		}
		if(opcode.length() == 8) {
			pat += c2;
		}
		int index = 0;
		for(int i = 0;i < operandName.size();i++) {
			switch (operandName.get(i)) {
			case "(MOD)":{
				operandValue.add(pat.substring(index, index + 2));
				index += 2;
				break;
			}
			case "(W)":{
				operandValue.add(pat.substring(index, index + 1));
				index++;
				break;
			}
			case "(D)":{
				operandValue.add(pat.substring(index, index + 1));
				index++;
				break;
			}
			case "(REG)":{
				operandValue.add(pat.substring(index, index + 3));
				index += 3;
				break;
			}
			case "(SEG)":{
				operandValue.add(pat.substring(index, index + 2));
				index += 2;
				break;
			}
			case "(R/M)":{
				operandValue.add(pat.substring(index, index + 3));
				index += 3;
				break;
			}
			}
		}
		return true;
	}
	
	public String getOperand(int index) {
		return operandValue.get(index);
	}

	public String getOperand(String index) {
		int i = operandName.indexOf("(" + index + ")");
		if(i >= 0) {
			return operandValue.get(i);
		}
		return null;
	}
	
	public String getDescrible() {
		return this.getClass().getSimpleName() + "\t" + describle;
	}
	
	public int getIndex() {
		return index;
	}

	public void exec() throws Exception {}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(this.getClass().getSimpleName() + "\t" + opcode + " + " + operand + " -> " + describle + "\n");

		sb.append(Arrays.toString(operandName.toArray(new String[]{})) + "\n");
		sb.append(Arrays.toString(operandValue.toArray(new String[]{})));
		return sb.toString();
	}
	
	public static boolean match(String c1, String c2, Execution exc) throws Exception {
		return exc.match(c1, c2);
	}
	
	public static byte[] arrayConcat(byte[] low,byte[] high) {
		byte[] ret = new byte[low.length + high.length];
		for(int i = 0;i < low.length;i++) {
			ret[i] = low[i];
		}
		for(int i = 0;i < high.length;i++) {
			ret[low.length + i] = high[i];
		}
		return ret;
	}
	public static void arraySplit(byte[] high,byte[] low,byte[] src) {
		for(int i = 0;i < low.length;i++) {
			low[i] = src[i];
		}
		for(int i = 0;i < high.length;i++) {
			high[i] = src[i + low.length];
		}
	}
	
	public static byte[] longToByteArray(long value, boolean signed, int length) {
		byte[] b = new byte[64];
		for(int i = 0;i < length;i++) {
			b[i] = (byte) ((value >> i) & 1);
		}
		if(signed) {
			for(int i = length;i < 64;i++) {
				b[i] = b[length - 1];
			}
		}
		return b;
	}

	public static long byteArrayToLong(byte[] value,boolean signed,int length) {
		long p = 0;
		long t = 0;
		for(int i = 0;i < length;i++) {
			t = value[i];
			p |= t << i;
		}
		if(signed) {
			for(int i = length;i < 64;i++) {
				p |= t << i;
			}
		}
		return p;
	}
	
	public static byte[] add16(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 16);

		MathUtils.copyArray(des, ret);
		return des;
	}	

	public static byte[] add8(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 8);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a + b, false, 8);
		
		MathUtils.copyArray(des, ret);
		return des;
	}

	public static byte[] sub16(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 16);
		long b = MathUtils.byteArrayToLong(src, false, 16);
		byte[] ret = MathUtils.longToByteArray(a - b, false, 16);

		MathUtils.copyArray(des, ret);
		return des;
	}

	public static byte[] sub8(byte[] des, byte[] src) {
		long a = MathUtils.byteArrayToLong(des, false, 8);
		long b = MathUtils.byteArrayToLong(src, false, 8);
		byte[] ret = MathUtils.longToByteArray(a - b, false, 8);
		
		MathUtils.copyArray(des, ret);
		return des;
	}
}
