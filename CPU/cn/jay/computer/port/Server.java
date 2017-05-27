package cn.jay.computer.port;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import cfg.Configer;
import cn.jay.computer.utilexception.CopyArrayException;  
  
public class Server {
	ArrayList<ServerThread> serverThreads = new ArrayList<ServerThread>();
	public Server() throws Exception {
		init();
	}
	
	private void init() throws Exception {
        ServerSocket server = new ServerSocket(Configer.getCPUConnectPort());  
        Socket client = null;
        boolean alive = true;
        while(alive){
            client = server.accept();
            serverThreads.add(new ServerThread(client));
        }
        server.close();
	}
	
	public void post() {
		copyFromPort();
		for(ServerThread st : serverThreads) {
			st.send();
		}
	}
	
	public static final void copyFromPort() {
		for(int i = 0;i < ServerThread.BUFFER.length;i += 2) {
			ServerThread.BUFFER[i] = PortMgr.PORT[i].getState();
			ServerThread.BUFFER[i + 1] = PortMgr.PORT[i].getValue();
		}
	}
	
	public static final void copyToPort() {
		PortMgr.setValue(ServerThread.BUFFER);
	}
	
}
  
class ServerThread extends Thread {  
	protected Socket socket = null;
	protected InputStream in = null;
	protected OutputStream out = null;

	protected boolean alive = true;
	
	/**
	 * 2N-STATE
	 * 2N+1-VALUE
	 */
	protected static final byte[] BUFFER = new byte[Configer.getCPUPortCount() * 2];
	
	public ServerThread(Socket socket) throws Exception {
		this.socket = socket;
		init();
	}

	private void init() throws Exception {
		socket = new Socket(Configer.getCPUConnectHost(), Configer.getCPUConnectPort());
		out = socket.getOutputStream();
		in = socket.getInputStream();
	}

	@Override
	public final void run() {
		while (alive) {
			receive();
			doJob();
		}
	}
	
	public static final void doJob() {
		
	}
	
	protected void receive() {
		try {
			in.read(BUFFER);
			Server.copyToPort();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void send() {
		try {
			out.write(BUFFER, 0, BUFFER.length);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	protected final void destory() {
		alive = false;
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void arrayConcat(byte[] des,byte[] state,byte[] value) throws CopyArrayException {
		for(int i = 0;i < value.length;i++) {
			des[i * 2] = state[i];
			des[i * 2 + 1] = value[i];
		}
	}
	
	public static void arraySplit(byte[] src,byte[] state,byte[] value) throws CopyArrayException {
		for(int i = 0;i < value.length;i++) {
			state[i] = src[i * 2];
			value[i] = src[i * 2 + 1];
		}
	}
	
}