package cn.jay.computer.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import cfg.Configer;

public abstract class Client extends Thread {
	protected Socket socket = null;
	protected InputStream in = null;
	protected OutputStream out = null;

	protected boolean alive = true;
	protected final byte[] BUFFER = new byte[Configer.getCPUPortCount() * 2];
	
	public Client() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public abstract void doJob();
	
	protected void receive() {
		try {
			in.read(BUFFER);
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
}