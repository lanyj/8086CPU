package cn.jay.computer;

import java.io.Serializable;

import cn.jay.computer.exec.ExecuterMgr;
import cn.jay.computer.memory.Memory;
import cn.jay.computer.program.Program;

//64 bit
public class Computer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 431830161784217526L;

	public static final int SIZE = 64;

	/**
	 * 内存
	 */
	public static Memory MEMORY = null;


	/**
	 * 指令集
	 */
	public static ExecuterMgr EXECUTER_MANAGER;

	public static long POINTER;

	static {
		// 内存
		MEMORY = new Memory(102400);

		// 指令
		EXECUTER_MANAGER = new ExecuterMgr();
	}

	public static void loadProgram(Program program) {
	}

	public static void exec() {
	}

}
