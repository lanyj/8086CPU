package cn.jay.computer.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1978354669464704963L;
	RegisterPanel RP = new RegisterPanel();
	
	public MainFrame() {
		super("8086 CPU");
		
		init();
	}
	
	private void init() {
		setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(2, 3));
		add(RP);
	}
	
	@Override
	public void paintComponents(Graphics g) {
		super.paintComponents(g);
		
		RP.paintComponents(g);
	}
	
	public static void main(String[] args) {
		MainFrame mf = new MainFrame();
		mf.setBounds(0, 0, 500, 500);
		mf.setVisible(true);
	}
	
}
