package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.event.MouseMotionAdapter;
import java.io.File;

import javax.swing.JColorChooser;
import java.awt.CardLayout;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JFileChooser;

public class PaintS {

	private JFrame frame;
	private Point pos1, pos2, position;
	private String chosen;
	private Canvas canvas;
	private Graphics2D g;
	private Graphics gd;

	boolean drawing = false;
	private DrawingEngine engine = new Controller();
	JColorChooser colorChooser;
	private JPanel choose;
	private JButton btnSelect;
	Color dcolor = Color.BLACK;
	private JButton btnSquare;
	Color fcolor = Color.WHITE;

	private ShapesFactory factory = new ShapesFactory();
	private Shapes shape, Cshape;

	private JButton btnNewButton;
	private JPanel panel_1;
	private JColorChooser colorChooser_1;
	private JButton button;
	private JButton btnFcolor;
	private JButton btnL;
	private JButton btnU;
	private JButton btnClomme;
	private JButton btnR;
	private JList list;
	private int i = 0;
	private JButton btnEllipse;
	private JButton btnCircle;
	private JButton btnTriangle;
	private JButton btnNewButton_1;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaintS window = new PaintS();
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
	public PaintS() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 776, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "name_499431610693407");

		JButton rect = new JButton("Rectangle");
		rect.setBounds(10, 11, 89, 23);
		rect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				shape = factory.makeShape("Rectangle");
				drawing = true;
			}
		});
		panel.setLayout(null);
		panel.add(rect);

		canvas = new Canvas();
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				if (drawing) {
					pos2 = arg0.getPoint();
					// canvas.getGraphics();
					// if (drawing) {

					// }
					// shape.properties.put("x", Math.max(pos1.getX(), pos2.getX()));
					// shape.properties.put("y", Math.max(pos1.getY(), pos2.getY()));
					shape.properties.put("x", pos2.getX());
					shape.properties.put("y", pos2.getY());
					shape.setPosition(pos1);
					position = new Point(Math.min(pos1.x, pos2.x), Math.min(pos1.y, pos2.y));
					// shape.position = position;
					gd = canvas.getGraphics();
					g = (Graphics2D) gd;
					shape.setColor(dcolor);
					engine.refresh(g);
					g.setStroke(new BasicStroke(2));
					shape.setColor(dcolor);
					shape.setFillColor(fcolor);
					shape.draw(g);

					// rectangle.draw(g);
					// chosen = "";
				}
			}

		});

		canvas.setBackground(Color.WHITE);
		canvas.setBounds(48, 59, 574, 271);
		panel.add(canvas);

		JButton btnColor = new JButton("Color");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel.setVisible(false);
				choose.setVisible(true);
			}
		});
		btnColor.setBounds(10, 338, 89, 23);
		panel.add(btnColor);

		btnSquare = new JButton("Square");
		btnSquare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape = factory.makeShape("Square");
				drawing = true;
			}
		});
		btnSquare.setBounds(109, 11, 89, 23);
		panel.add(btnSquare);

		btnNewButton = new JButton("S");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Filechoose = new JFileChooser();
				int retval = Filechoose.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					// ... The user selected a file, get it, use it.
					File file = Filechoose.getSelectedFile();
					engine.save(file.getPath() + ".xml");
				}
			}
		});
		btnNewButton.setBounds(429, 11, 89, 23);
		panel.add(btnNewButton);

		btnFcolor = new JButton("fcolor");
		btnFcolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel.setVisible(false);
				panel_1.setVisible(true);
			}
		});
		btnFcolor.setBounds(109, 336, 89, 23);
		panel.add(btnFcolor);

		btnL = new JButton("L");
		btnL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Filechoose = new JFileChooser();
				int retval = Filechoose.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					// ... The user selected a file, get it, use it.
					File file = Filechoose.getSelectedFile();
					engine.load(file.getPath());
				}
				gd = canvas.getGraphics();
				engine.refresh(gd);
			}
		});
		btnL.setBounds(533, 11, 89, 23);
		panel.add(btnL);

		btnU = new JButton("U");
		btnU.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				gd = canvas.getGraphics();
				engine.refresh(gd);
				engine.undo();
				engine.refresh(g);
			}
		});
		btnU.setBounds(429, 338, 89, 23);
		panel.add(btnU);

		btnClomme = new JButton("Clone");
		btnClomme.setEnabled(false);
		btnClomme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Cshape = (Shapes) shape.clone();
					engine.addShape(Cshape);
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnClomme.setBounds(315, 11, 89, 23);
		panel.add(btnClomme);

		btnR = new JButton("R");
		btnR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				engine.redo();
				engine.refresh(g);
			}
		});
		btnR.setBounds(528, 338, 89, 23);
		panel.add(btnR);

		list = new JList(engine.getShapes());
		
		list.setBounds(645, 59, 105, 271);
		panel.add(list);

		JButton btnLine = new JButton("Line");
		btnLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shape = factory.makeShape("Line");
				drawing = true;
			}
		});
		btnLine.setBounds(208, 11, 89, 23);
		panel.add(btnLine);

		btnEllipse = new JButton("Ellipse");
		btnEllipse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				shape = factory.makeShape("Ellipse");
				drawing = true;
			}
		});
		btnEllipse.setBounds(10, 34, 89, 23);
		panel.add(btnEllipse);

		btnCircle = new JButton("Circle");
		btnCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape = factory.makeShape("Circle");
				drawing = true;
			}
		});
		btnCircle.setBounds(109, 34, 89, 23);
		panel.add(btnCircle);

		btnTriangle = new JButton("Triangle");
		btnTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				shape = factory.makeShape("Triangle");
				drawing = true;
			}
		});
		btnTriangle.setBounds(208, 34, 89, 23);
		panel.add(btnTriangle);

		btnNewButton_1 = new JButton("plugin");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Filechoose = new JFileChooser();
				int retval = Filechoose.showOpenDialog(null);
				if (retval == JFileChooser.APPROVE_OPTION) {
					// ... The user selected a file, get it, use it.
					File file = Filechoose.getSelectedFile();
					engine.installPluginShape(file.getPath());
				}
			}
		});
		btnNewButton_1.setBounds(10, 164, 26, 23);
		panel.add(btnNewButton_1);

		choose = new JPanel();
		frame.getContentPane().add(choose, "name_499431645076952");
		choose.setLayout(null);
		colorChooser = new JColorChooser();
		colorChooser.setBounds(19, 5, 603, 317);
		choose.add(colorChooser);

		btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dcolor = colorChooser.getColor();
				choose.setVisible(false);
				panel.setVisible(true);
				// g = canvas.getGraphics();
				engine.refresh(g);
			}
		});
		btnSelect.setBounds(533, 329, 89, 23);
		choose.add(btnSelect);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		frame.getContentPane().add(panel_1, "name_575453660745198");

		colorChooser_1 = new JColorChooser();
		colorChooser_1.setBounds(19, 5, 603, 317);
		panel_1.add(colorChooser_1);

		button = new JButton("Select");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fcolor = colorChooser_1.getColor();
				panel_1.setVisible(false);
				panel.setVisible(true);
				gd = canvas.getGraphics();
				g = (Graphics2D) gd;
				engine.refresh(gd);
			}
		});
		button.setBounds(533, 329, 89, 23);
		panel_1.add(button);

		panel_2 = new JPanel();
		frame.getContentPane().add(panel_2, "name_751710243646145");
		panel_2.setLayout(null);

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setBounds(0, 0, 760, 383);
		panel_2.add(fileChooser);

		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				pos1 = e.getPoint();
			}

			DefaultListModel dlm = new DefaultListModel();

			@Override
			public void mouseReleased(MouseEvent e) {
				if (drawing) {
					list.setModel(dlm);
					engine.addShape(shape);
					drawing = false;
					dlm.clear();
					for (int i = 0; i < engine.getShapes().length; i++) {
						dlm.addElement(engine.getShapes()[i].getClass().getName().substring(31)+" " + i);
					}
				}
			}
		});

	}
}
