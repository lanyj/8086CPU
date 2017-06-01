package cn.jay.computer.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import cfg.Configer;
import cn.jay.modelprovider.ModelMgr;

public abstract class Client extends Thread {
	protected Socket socket = null;
	protected InputStream in = null;
	protected OutputStream out = null;

	protected boolean alive = true;
	protected final byte[] BUFFER = new byte[Configer.getCPUPortCount() * 2];
	protected String modelName = new Date().toString();
	
	public Client(String modelName) {
		this.modelName = modelName;
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean deploy() {
		this.start();
		
		return ModelMgr.addIO_Model(this);
	}
	
	public boolean undeploy() {
		this.close();
		
		return ModelMgr.removeIO_Model(this.getModelName()) == this;
	}
	
	private void init() throws Exception {
		socket = new Socket(Configer.getCPUConnectHost(), Configer.getCPUConnectPort());
		out = socket.getOutputStream();
		in = socket.getInputStream();
	}

	public String getModelName() {
		return modelName;
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
			if(alive)
				e.printStackTrace();
		}
	}
	
	protected void send() {
		try {
			out.write(BUFFER, 0, BUFFER.length);
			out.flush();
		} catch (IOException e) {
			if(alive)
				e.printStackTrace();
		}
	}
	
	private final void close() {
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