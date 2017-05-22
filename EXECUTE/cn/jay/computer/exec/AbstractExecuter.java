package cn.jay.computer.exec;

//64 bit
public abstract class AbstractExecuter {

	protected byte[][] feature = null;
	
	public final void setFeature(byte[][] feature) {
		this.feature = feature;
	}
	
	/**
	 * judge code is can be executed by self
	 * 
	 * @param code
	 * @return
	 */
	public final int isSame(byte code) {
		return match(feature, code);
	}
	
	/**
	 * judge code is can be executed by self
	 * 
	 * @param code
	 * @return
	 */
	public final int isSame(byte[] code) {
		return isSame(arrayToByte(code));
	}

	/**
	 * execute code
	 * 
	 * @param code
	 */
	public abstract void exec(byte code);
	
	/**
	 * execute code
	 * 
	 * @param code
	 */
	public final void exec(byte[] code) {
		exec(arrayToByte(code));
	}
	
	public static int match(byte[][] feature, byte code) {
		for(int i = 0;i < feature.length;i++) {
			byte c = code;
			byte b = feature[i][0];
			byte l = feature[i][1];
			b >>= (8 - l);
			c >>= (8 - l);
			if(c == b) {
				return i;
			}
		}
		return -1;
	}
	
	public static byte[] byteToArray(byte b) {
		byte[] buf = new byte[8];
		for (int i = 0; i < 8; i++) {
			buf[i] = (byte) ((b >> i) & 1);
		}
		return buf;
	}
	public static byte arrayToByte(byte buf[]) {
		byte b = 0;
		byte k = 1;
		for (int i = 0; i < 8; i++) {
			k = (byte) (buf[i] << i);
			b |= k;
		}
		return b;
	}
	/**
	 * src = 11110000B,beg = 3,len = 2,inc = true,then result is "01"
	 * 
	 * @param src
	 * @param beg
	 * @param len
	 * @param inc 0->7:true,7->0:false
	 * @return
	 */
	public static String getString(byte src,int beg,int len,boolean inc) {
		String ret = "";
		int end;
		if(inc) {
			end = beg + len;
			for(int i = beg;i < end;i++) {
				ret += (src >> i) & 1;
			}
		} else{
			end = beg - len;
			for(int i = beg;i > end;i--) {
				ret += (src >> i) & 1;
			}
			
		}
		return ret;
	}
}
