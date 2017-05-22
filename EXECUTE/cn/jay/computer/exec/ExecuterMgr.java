package cn.jay.computer.exec;

import java.io.Serializable;
import java.util.concurrent.CopyOnWriteArrayList;

public class ExecuterMgr implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787905806224859260L;
	public static CopyOnWriteArrayList<AbstractExecuter> executer = new CopyOnWriteArrayList<AbstractExecuter>();

	/**
	 * add executer to executerManager
	 * 
	 * @param executer
	 */
	public static void addExecuter(AbstractExecuter executer) {
		ExecuterMgr.executer.add(executer);
	}

	/**
	 * remove executer
	 * 
	 * @param executer
	 */
	public static void removeExecuter(AbstractExecuter executer) {
		ExecuterMgr.executer.remove(executer);
	}

	/**
	 * execute code by executers which contains in executerManager
	 * 
	 * @param code
	 */
	public static void exec(byte[] code) {
		for (AbstractExecuter executer : ExecuterMgr.executer) {
			if (executer.isSame(code) >= 0) {
				System.out.println(executer);
				executer.exec(code);
			}
		}
	}

}
