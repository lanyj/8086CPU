package cn.jay.computer.exec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public boolean match(String c1, String c2) {
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
		return describle;
	}
	
	public int getIndex() {
		return index;
	}

	public void exec() {}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(opcode + " + " + operand + " -> " + describle + "\n");

		sb.append(Arrays.toString(operandName.toArray(new String[]{})) + "\n");
		sb.append(Arrays.toString(operandValue.toArray(new String[]{})));
		return sb.toString();
	}
	
	public static boolean match(String c1, String c2, Execution exc) {
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

}
