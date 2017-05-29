package cn.jay.computer.exec;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ExecuterMgr implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1787905806224859260L;
	
	public static ArrayList<Execution> abstractExecuters = new ArrayList<>();
	public static ArrayList<String> executerNames = new ArrayList<>();
	
	private static Document document = null;
	static {
		try {
			document = Jsoup.parse(new File(System.getProperty("user.dir") + "/EXECUTE/execute.cfg.xml"),"utf-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		{
			Elements es = document.getElementsByTag("segmentoverride");
			Elements cs = es.get(0).children();
			for(int i = 0;i < cs.size();i++) {
				String name = cs.get(i).attr("name");
				Elements ss = cs.get(i).children();
				for(int j = 0;j < ss.size();j++) {
					Execution ec = null;
					try {
						ec = (Execution) Class.forName("cn.jay.computer.exec.segmentoverride." + name).getConstructors()[0].newInstance(ss.get(j).attr("opcode"), ss.get(j).attr("operand"), ss.get(j).text(), j);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ec != null)
						addExecuter(name, ec);
				}
			}
		}
		{
			Elements es = document.getElementsByTag("datatransferoperation");
			Elements cs = es.get(0).children();
			for(int i = 0;i < cs.size();i++) {
				String name = cs.get(i).attr("name");
				Elements ss = cs.get(i).children();
				for(int j = 0;j < ss.size();j++) {
					Execution ec = null;
					try {
						ec = (Execution) Class.forName("cn.jay.computer.exec.datatransferoperation." + name).getConstructors()[0].newInstance(ss.get(j).attr("opcode"), ss.get(j).attr("operand"), ss.get(j).text(), j);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ec != null)
						addExecuter(name, ec);
				}
			}
		}
		{
			Elements es = document.getElementsByTag("arithmeticoperation");
			Elements cs = es.get(0).children();
			for(int i = 0;i < cs.size();i++) {
				String name = cs.get(i).attr("name");
				Elements ss = cs.get(i).children();
				for(int j = 0;j < ss.size();j++) {
					Execution ec = null;
					try {
						ec = (Execution) Class.forName("cn.jay.computer.exec.arithmeticoperation." + name).getConstructors()[0].newInstance(ss.get(j).attr("opcode"), ss.get(j).attr("operand"), ss.get(j).text(), j);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ec != null)
						addExecuter(name, ec);
				}
			}
		}
		{
			Elements es = document.getElementsByTag("logicaloperation");
			Elements cs = es.get(0).children();
			for(int i = 0;i < cs.size();i++) {
				String name = cs.get(i).attr("name");
				Elements ss = cs.get(i).children();
				for(int j = 0;j < ss.size();j++) {
					Execution ec = null;
					try {
						ec = (Execution) Class.forName("cn.jay.computer.exec.logicaloperation." + name).getConstructors()[0].newInstance(ss.get(j).attr("opcode"), ss.get(j).attr("operand"), ss.get(j).text(), j);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ec != null)
						addExecuter(name, ec);
				}
			}
		}
		{
			Elements es = document.getElementsByTag("stringmanipulationoperation");
			Elements cs = es.get(0).children();
			for(int i = 0;i < cs.size();i++) {
				String name = cs.get(i).attr("name");
				Elements ss = cs.get(i).children();
				for(int j = 0;j < ss.size();j++) {
					Execution ec = null;
					try {
						ec = (Execution) Class.forName("cn.jay.computer.exec.stringmanipulationoperation." + name).getConstructors()[0].newInstance(ss.get(j).attr("opcode"), ss.get(j).attr("operand"), ss.get(j).text(), j);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ec != null)
						addExecuter(name, ec);
				}
			}
		}
		{
			Elements es = document.getElementsByTag("controltransferoperation");
			Elements cs = es.get(0).children();
			for(int i = 0;i < cs.size();i++) {
				String name = cs.get(i).attr("name");
				Elements ss = cs.get(i).children();
				for(int j = 0;j < ss.size();j++) {
					Execution ec = null;
					try {
						ec = (Execution) Class.forName("cn.jay.computer.exec.controltransferoperation." + name).getConstructors()[0].newInstance(ss.get(j).attr("opcode"), ss.get(j).attr("operand"), ss.get(j).text(), j);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ec != null)
						addExecuter(name, ec);
				}
			}
		}
		{
			Elements es = document.getElementsByTag("processorcontroloperation");
			Elements cs = es.get(0).children();
			for(int i = 0;i < cs.size();i++) {
				String name = cs.get(i).attr("name");
				Elements ss = cs.get(i).children();
				for(int j = 0;j < ss.size();j++) {
					Execution ec = null;
					try {
						ec = (Execution) Class.forName("cn.jay.computer.exec.processorcontroloperation." + name).getConstructors()[0].newInstance(ss.get(j).attr("opcode"), ss.get(j).attr("operand"), ss.get(j).text(), j);
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
							| InvocationTargetException | SecurityException | ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(ec != null)
						addExecuter(name, ec);
				}
			}
		}
	}
	
	public static Elements getElements(String name) {
		Elements elements = document.getElementsByAttributeValue("name", name);
		return elements;
	}
	
	/**
	 * add executer to executerManager
	 * 
	 */
	public static void addExecuter(String name, Execution executer) {
		ExecuterMgr.executerNames.add(name);
		ExecuterMgr.abstractExecuters.add(executer);
	}

	/**
	 * remove executer
	 * 
	 */
	public static void removeExecuter(String name) {
		int i = executerNames.indexOf(name);
		if(i >= 0) {
			executerNames.remove(i);
			abstractExecuters.remove(i);
		}
	}

	/**
	 * execute code by executers which contains in executerManager
	 * 
	 * @param code
	 */
	public static void exec(byte[] c1, byte[] c2) {
		String s1 = arrayToString(c1);
		String s2 = arrayToString(c2);
		for(Execution e : abstractExecuters) {
			if(e.match(s1,s2)) {
				System.out.println(e);
				e.exec();
			}
		}
	}
	
	public static String arrayToString(byte[] code) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0;i < code.length;i++) {
			sb.append(code[7 - i]);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
	}

}
