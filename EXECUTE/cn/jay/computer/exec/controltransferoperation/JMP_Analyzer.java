package cn.jay.computer.exec.controltransferoperation;

import cn.jay.computer.register.flagregister.FLAGS;

public class JMP_Analyzer {
	public static boolean canJmp(String index) {
		byte[] flags = FLAGS.getFLAGS();
		boolean o = flags[11] > 0;
		boolean s = flags[7] > 0;
		boolean z = flags[6] > 0;
		boolean p = flags[2] > 0;
		boolean c = flags[0] > 0;
		switch(index) {
		case "0000":{
			//JO
			return o;
		}
		case "0001":{
			//JNO
			return !o;
		}
		case "0010":{
			//JB
			return c;
		}
		case "0011":{
			//JNB
			return !c;
		}
		case "0100":{
			//JE
			return z;
		}
		case "0101":{
			//JNE
			return !z;
		}
		case "0110":{
			//JBE
			return c & z;
		}
		case "0111":{
			//JNBE
			return !(c & z);
		}
		case "1000":{
			//JS
			return s;
		}
		case "1001":{
			//JNS
			return !s;
		}
		case "1010":{
			//JP
			return p;
		}
		case "1011":{
			//JNP
			return !p;
		}
		case "1100":{
			//JL
			return s ^ o;
		}
		case "1101":{
			//JNL
			return !(s ^ o);
		}
		case "1110":{
			//JLE
			return (s ^ o) | z;
		}
		case "1111":{
			//JNLE
			return !((s ^ o) | z);
		}
		}
		return false;
	}
}
