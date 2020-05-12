package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JList;
import java.awt.SystemColor;

public class PaintGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintGui window = new PaintGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PaintGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 794, 524);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel paintPanel = new JPanel();
		frame.getContentPane().add(paintPanel, "name_644254749319124");
		paintPanel.setLayout(null);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(62, 52, 607, 389);
		paintPanel.add(canvas);
		
		JButton lbtn = new JButton("L");
		lbtn.setBounds(10, 91, 42, 23);
		paintPanel.add(lbtn);
		
		JButton rbtn = new JButton("R");
		rbtn.setBounds(10, 135, 42, 23);
		paintPanel.add(rbtn);
		
		JButton sbtn = new JButton("S");
		sbtn.setBounds(10, 182, 42, 23);
		paintPanel.add(sbtn);
		
		JButton cbtn = new JButton("C");
		cbtn.setBounds(10, 233, 42, 23);
		paintPanel.add(cbtn);
		
		JButton ebtn = new JButton("E");
		ebtn.setBounds(10, 284, 42, 23);
		paintPanel.add(ebtn);
		
		JButton tbtn = new JButton("T");
		tbtn.setBounds(10, 338, 42, 23);
		paintPanel.add(tbtn);
		
		JButton button = new JButton("");
		button.setBounds(10, 388, 42, 23);
		paintPanel.add(button);
		
		JButton btnColor = new JButton("Color");
		btnColor.setBounds(62, 447, 69, 23);
		paintPanel.add(btnColor);
		
		JButton btnFillColor = new JButton("Fill Color");
		btnFillColor.setBounds(141, 447, 82, 23);
		paintPanel.add(btnFillColor);
		
		JList list = new JList();
		list.setBackground(SystemColor.activeCaption);
		list.setBounds(769, 447, -90, -392);
		paintPanel.add(list);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.setBounds(62, 11, 69, 23);
		paintPanel.add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.setBounds(141, 11, 69, 23);
		paintPanel.add(btnRedo);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(602, 11, 69, 23);
		paintPanel.add(btnLoad);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(523, 11, 69, 23);
		paintPanel.add(btnSave);
		
		JPanel colorPanel = new JPanel();
		frame.getContentPane().add(colorPanel, "name_645090247469085");
		colorPanel.setLayout(null);
		
		JColorChooser colorChooser = new JColorChooser();
		colorChooser.setBounds(0, 0, 778, 444);
		colorPanel.add(colorChooser);
		
		JButton colorSelect = new JButton("Select");
		colorSelect.setBounds(679, 452, 89, 23);
		colorPanel.add(colorSelect);
		
		JPanel fillPanel = new JPanel();
		frame.getContentPane().add(fillPanel, "name_645195543553939");
		fillPanel.setLayout(null);
		
		JColorChooser colorChooser_1 = new JColorChooser();
		colorChooser_1.setBounds(0, 0, 778, 441);
		fillPanel.add(colorChooser_1);
		
		JButton fillSelect = new JButton("Select");
		fillSelect.setBounds(679, 452, 89, 23);
		fillPanel.add(fillSelect);
	}
}
