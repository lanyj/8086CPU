package cn.jay.computer.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RegisterPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2830884845095962029L;
	private DataReg DR = new DataReg();
	private AddressReg AR = new AddressReg();
	private SegmentReg SR = new SegmentReg();
	private ControlReg CR = new ControlReg();

	public RegisterPanel() {
		init();
	}

	private void init() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(4, 1));
		add(DR);
		add(AR);
		add(SR);
		add(CR);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		DR.paintComponents(g);
		AR.paintComponents(g);
		SR.paintComponents(g);
		CR.paintComponents(g);
	}

}

class DataReg extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5991090702768402997L;
	JLabel AX = new JLabel("", JLabel.CENTER);
	JLabel BX = new JLabel("", JLabel.CENTER);
	JLabel CX = new JLabel("", JLabel.CENTER);
	JLabel DX = new JLabel("", JLabel.CENTER);

	public DataReg() {
		init();
	}

	private void init() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(5, 1));
		setBorder(new LineBorder(Color.BLACK));
		add(new JLabel("Data Register", JLabel.CENTER));
		add(AX);
		add(BX);
		add(CX);
		add(DX);
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponent(g);
		AX.setText("AX:" + DataProvider.getRegister("AX").toString());
		BX.setText("BX:" + DataProvider.getRegister("BX").toString());
		CX.setText("CX:" + DataProvider.getRegister("CX").toString());
		DX.setText("DX:" + DataProvider.getRegister("DX").toString());
	}
}

class AddressReg extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3473591911844026633L;
	JLabel SP = new JLabel("", JLabel.CENTER);
	JLabel BP = new JLabel("", JLabel.CENTER);
	JLabel SI = new JLabel("", JLabel.CENTER);
	JLabel DI = new JLabel("", JLabel.CENTER);

	public AddressReg() {
		init();
	}

	private void init() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(5, 1));
		setBorder(new LineBorder(Color.BLACK));
		add(new JLabel("Address Register", JLabel.CENTER));
		add(SP);
		add(BP);
		add(SI);
		add(DI);
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponent(g);
		SP.setText("SP:" + DataProvider.getRegister("SP").toString());
		BP.setText("BP:" + DataProvider.getRegister("BP").toString());
		SI.setText("SI:" + DataProvider.getRegister("SI").toString());
		DI.setText("DI:" + DataProvider.getRegister("DI").toString());
	}
}

class SegmentReg extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 978637143170079791L;
	JLabel CS = new JLabel("", JLabel.CENTER);
	JLabel DS = new JLabel("", JLabel.CENTER);
	JLabel SS = new JLabel("", JLabel.CENTER);
	JLabel ES = new JLabel("", JLabel.CENTER);

	public SegmentReg() {
		init();
	}

	private void init() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(5, 1));
		setBorder(new LineBorder(Color.BLACK));
		add(new JLabel("Segment Register", JLabel.CENTER));
		add(CS);
		add(DS);
		add(SS);
		add(ES);
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponent(g);
		CS.setText("CS:" + DataProvider.getRegister("CS").toString());
		DS.setText("DS:" + DataProvider.getRegister("DS").toString());
		SS.setText("SS:" + DataProvider.getRegister("SS").toString());
		ES.setText("ES:" + DataProvider.getRegister("ES").toString());
	}
}

class ControlReg extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8023766492865239751L;
	JLabel IP = new JLabel("", JLabel.CENTER);
	JLabel FLAGS = new JLabel("", JLabel.CENTER);

	public ControlReg() {
		init();
	}

	private void init() {
		setBackground(Color.WHITE);
		setLayout(new GridLayout(5, 1));
		setBorder(new LineBorder(Color.BLACK));
		add(new JLabel("Control Register", JLabel.CENTER));
		add(IP);
		add(FLAGS);
		add(new JLabel(""));
		add(new JLabel(""));
	}

	@Override
	public void paintComponents(Graphics g) {
		super.paintComponent(g);
		IP.setText("IP = " + DataProvider.getIP());
		FLAGS.setText("FLAGS:" + Arrays.toString(DataProvider.getFlags()));
	}
}
