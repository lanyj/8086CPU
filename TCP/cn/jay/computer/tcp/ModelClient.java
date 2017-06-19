package cn.jay.computer.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;

import cfg.Configer;
import cn.jay.modelprovider.ModelMgr;
import modelinterface.ModelInterface;

public abstract class ModelClient extends Thread implements ModelInterface {
	protected Socket socket = null;
	protected InputStream in = null;
	protected OutputStream out = null;

	protected boolean alive = true;
	protected final byte[] BUFFER = new byte[Configer.getCPUPortCount() * 2];
	protected String modelName = new Date().toString();
	
	public ModelClient(String modelName) {
		this.modelName = modelName;
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
	
	/**
	 * do what you want to do
	 */
	public abstract void doJob();
	
	protected void receive() {
		try {
			in.read(BUFFER);
		} catch (IOException e) {
			if(alive)
				e.printStackTrace();
		}
	}
	
	protected final void send() {
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
	
	public void deploy() {
		this.start();
		ModelMgr.addModel(this);
	}
	
	public void undeploy() {
		this.close();
	}
	
	@Override
	public boolean isRunning() {
		return alive;
	}
	
	public void setModelName(String name) {
		this.modelName = name;
	}
	
	public String getModelName() {
		return modelName;
	}
	
}