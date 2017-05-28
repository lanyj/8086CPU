package cn.jay.computer.exec;

import cn.jay.computer.alu.MathUtils;
import cn.jay.computer.biu.BIU;
import cn.jay.computer.eu.Environment;
import cn.jay.computer.register.dataregister.BX;
import cn.jay.computer.register.indexregister.BP;
import cn.jay.computer.register.indexregister.DI;
import cn.jay.computer.register.indexregister.SI;
import cn.jay.computer.register.segmentregister.SS;

public class RM_MOD_Analyzer {
	public static byte[] analyze(String MOD,String RM,boolean W) {
		byte[] ret = null;
		if(!MOD.equals("11") && RM.equals("110") && !MOD.equals("00")) {
			Environment.setDataSegment(SS._SS);
		}
		switch(MOD) {
		case "00":{
			ret = RM_Analyze(RM);
			if(RM.equals("110")) {
				ret = add(ret, arrayConcat(BIU.getInstruction(), BIU.getInstruction()), 16);
			}
			break;
		}
		case "01":{
			ret = RM_Analyze(RM);
			ret = add(ret, BIU.getInstruction(), 16);
			break;
		}
		case "10":{
			ret = RM_Analyze(RM);
			ret = add(ret, arrayConcat(BIU.getInstruction(), BIU.getInstruction()), 16);
			break;
		}
		case "11":{
			return null;
		}
 		}
		return ret;
	}
	private static byte[] RM_Analyze(String RM) {
		byte[] ret = null;
		switch(RM) {
		case "000":{
			ret = add(BX.getBX(), SI.getSI(), 16);
			break;
		}
		case "001":{
			ret = add(BX.getBX(), DI.getDI(), 16);
			break;
		}
		case "010":{
			ret = add(BP.getBP(), SI.getSI(), 16);
			Environment.setDataSegment(SS._SS);
			break;
		}
		case "011":{
			ret = add(BP.getBP(), DI.getDI(), 16);
			Environment.setDataSegment(SS._SS);
			break;
		}
		case "100":{
			ret = SI.getSI();
			break;
		}
		case "101":{
			ret = DI.getDI();
			break;
		}
		case "110":{
			ret = new byte[16];
			break;
		}
		case "111":{
			ret = BX.getBX();
			break;
		}
		}
		return ret;
	}
	
	public static byte[] add(byte[] right, byte[] left,int length) {
		byte[] ret = new byte[length];
		long a = MathUtils.byteArrayToLong(right, false, right.length);
		long b = MathUtils.byteArrayToLong(left, false,left.length);
		MathUtils.copyArray(ret, MathUtils.longToByteArray(a + b, false, length));;
		return ret;
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
