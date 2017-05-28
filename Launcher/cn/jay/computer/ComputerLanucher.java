package cn.jay.computer;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import cfg.Configer;
import cn.jay.computer.clk.CLK;
import cn.jay.computer.eu.EU;
import cn.jay.computer.memory.MemoryManager;
import cn.jay.computer.register.dataregister.BX;
import cn.jay.computer.register.indexregister.BP;
import cn.jay.computer.register.segmentregister.SS;
import cn.jay.computer.utilexception.CopyArrayException;

public class ComputerLanucher {
	public static void start() {
		Configer.getCLK(1).registerTask(EU.JOB);

		Set<Entry<Integer, CLK>> clk = Configer.CLK_POOL.entrySet();
		for (Iterator<Entry<Integer, CLK>> itr = clk.iterator(); itr.hasNext();) {
			itr.next().getValue().open();
		}
	}
	
	public static final byte[][] MAP = {{0,0,0,0},
										{1,0,0,0},
										{0,1,0,0},
										{1,1,0,0},
										{0,0,1,0},
										{1,0,1,0},
										{0,1,1,0},
										{1,1,1,0},
										{0,0,0,1},
										{1,0,0,1},
										{0,1,0,1},
										{1,1,0,1},
										{0,0,1,1},
										{1,0,1,1},
										{0,1,1,1},
										{1,1,1,1}};
	
	public static final byte[] getArray(char c) {
		if(c >= '0' && c <= '9') {
			return MAP[c - '0'];
		} else if(c >= 'A' && c <= 'F') {
			return MAP[c - 'A' + 10];
		} else if(c >= 'a' && c <= 'f') {
			return MAP[c - 'a' + 10];
		}
		return null;
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
	
	public static void main(String[] args) {
		
		String mem = "B8FA0A8ED8B82100BB2A00";
		
//		PUSH DS
//		byte[][] test_code = { { 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 0, 0, 0, 1, 1, 0, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0 },
//				{ 0, 1, 0, 0, 1, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1 } };
		
//		MOV [BX], 123FH
//		byte[][] test_code = { { 1, 1, 1, 0, 0, 0, 1, 1 }, { 1, 1, 1, 0, 0, 0, 0, 0 },
//				{ 1, 1, 1, 1, 1, 1, 0, 0 }, { 0, 1, 0, 0, 1, 0, 0, 0 },
//				{ 1, 1, 1, 1, 1, 1, 1, 1 }, { 0, 1, 0, 0, 1, 0, 0, 0 },
//				{ 1, 1, 1, 1, 1, 1, 1, 1 } };
		
		//MOV [BP + 123FH],123FH
		//C7863F123F12
		//A - 10(1010),B - 11(1011),C - 12(1100),D - 13(1101),E - 14(1110),F - 15(1111)
		//11000111,10001010,00111111,00010010,00111111,00010010
		byte[][] test_code = {{1,1,1,0,0,0,1,1},{0,1,1,0,0,0,0,1},
				{1,1,1,1,1,1,0,0},{0,1,0,0,1,0,0,0},{1,1,1,1,1,1,0,0},{0,1,0,0,1,0,0,0}};
		
		try {
			BP.setBP(new byte[]{1,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0});
			SS.setSS(new byte[]{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1});
			BX.setBL(new byte[]{1,0,1,0,0,0,0,0});
		} catch (CopyArrayException e) {
		}
		MemoryManager mm = Configer.getMeomryManager();
//		for (int i = 0; i < 10000 * test_code.length; i++) {
//			mm.write(i, test_code[i % test_code.length]);
//		}
		
		for(int i = 0;i < mem.length();i++) {
			char a = mem.charAt(i++);
			char b = mem.charAt(i);
			byte[] low = getArray(b);
			byte[] high = getArray(a);
			mm.write(i >>> 1, arrayConcat(low, high));
		}
		
		start();
	}

}
