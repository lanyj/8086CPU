package cn.jay.computer.register.baseregister;

import java.util.Arrays;

import cn.jay.computer.register.dataregister.AX;
import cn.jay.computer.register.dataregister.BX;
import cn.jay.computer.register.dataregister.CX;
import cn.jay.computer.register.dataregister.DX;
import cn.jay.computer.register.indexregister.BP;
import cn.jay.computer.register.indexregister.DI;
import cn.jay.computer.register.indexregister.SI;
import cn.jay.computer.register.indexregister.SP;
import cn.jay.computer.register.segmentregister.CS;
import cn.jay.computer.register.segmentregister.DS;
import cn.jay.computer.register.segmentregister.ES;
import cn.jay.computer.register.segmentregister.SS;

public class RegisterMgr {
	public static final void setDATA(String REG, boolean W, byte[] value) throws Exception {
		
		System.out.println("Set REG = " + REG + ", W = " + W + ", VALUE = " + Arrays.toString(value));
		
		switch (REG) {
		case "000":{
			if(W)
				AX.setAX(value);
			else
				AX.setAL(value);
			break;
		}
		case "011":{
			if(W)
				BX.setBX(value);
			else
				BX.setBL(value);
			break;
		}
		case "001":{
			if(W)
				CX.setCX(value);
			else
				CX.setCL(value);
			break;
		}
		case "010":{
			if(W)
				DX.setDX(value);
			else
				DX.setDL(value);
			break;
		}
		case "100":{
			if(W)
				SP.setSP(value);
			else
				AX.setAH(value);
			break;
		}
		case "111":{
			if(W)
				DI.setDI(value);
			else
				BX.setBH(value);
			break;
		}
		case "101":{
			if(W)
				BP.setBP(value);
			else
				CX.setCH(value);
			break;
		}
		case "110":{
			if(W)
				SI.setSI(value);
			else
				DX.setDH(value);
			break;
		}
		case "01":{
			CS.setCS(value);
			break;
		}
		case "11":{
			DS.setDS(value);
			break;
		}
		case "00":{
			ES.setES(value);
			break;
		}
		case "10":{
			SS.setSS(value);
			break;
		}
		default:{
			throw new Exception("Unknow register:[" + REG + ", " + W + "]");
		}
		}
	}
	public static final byte[] getDATA(String REG, boolean W) throws Exception {
		System.out.println("Get register data: REG = " + REG + ", W = " + W);

		switch (REG) {
		case "000":{
			if(W)
				return AX.getAX();
			else
				return AX.getAL();
		}
		case "011":{
			if(W)
				return BX.getBX();
			else
				return BX.getBL();
		}
		case "001":{
			if(W)
				return CX.getCX();
			else
				return CX.getCL();
		}
		case "010":{
			if(W)
				return DX.getDX();
			else
				return DX.getDL();
		}
		case "100":{
			if(W)
				return SP.getSP();
			else
				return AX.getAH();
		}
		case "111":{
			if(W)
				return DI.getDI();
			else
				return BX.getBH();
		}
		case "101":{
			if(W)
				return BP.getBP();
			else
				return CX.getCH();
		}
		case "110":{
			if(W)
				return SI.getSI();
			else
				return DX.getDH();
		}
		case "01":{
			return CS.getCS();
		}
		case "11":{
			return DS.getDS();
		}
		case "00":{
			return ES.getES();
		}
		case "10":{
			return SS.getSS();
		}
		default:{
			throw new Exception("Unknow register:[" + REG + ", " + W + "]");
		}
		}
	}
}
