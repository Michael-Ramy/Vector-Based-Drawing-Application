package eg.edu.alexu.csd.oop.draw.cs35;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class Controller implements DrawingEngine {
	List<Class<? extends Shape>> p = new ArrayList<>();
	LinkedList<Shape> shapes = new LinkedList<>();
	ShapesFactory factory = new ShapesFactory();
	Stack<Operation> undo = new Stack<>();
	Stack<Operation> redo = new Stack<>();
	boolean u = false;

	@SuppressWarnings("unchecked")
	public Controller() {
		// TODO Auto-generated constructor stub
		try {
			p.add((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs35.Line", true,
					this.getClass().getClassLoader()));
			p.add((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs35.Circle", true,
					this.getClass().getClassLoader()));
			p.add((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs35.Rectangle", true,
					this.getClass().getClassLoader()));
			p.add((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs35.Triangle", true,
					this.getClass().getClassLoader()));
			p.add((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs35.Ellipse", true,
					this.getClass().getClassLoader()));
			p.add((Class<? extends Shape>) Class.forName("eg.edu.alexu.csd.oop.draw.cs35.Square", true,
					this.getClass().getClassLoader()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		canvas.clearRect(0, 0, 632, 372);
		for (int i = 0; i < shapes.size(); i++) {
			Shapes sh = (Shapes) shapes.get(i);
			sh.draw(canvas);
		}

	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		shapes.add(shape);
		if (undo.size() == 20 && !u) {
			undo.remove(0);
		}
		if (!u) {
			undo.push(new Operation("add", shape));
			redo.clear();
		}
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		shapes.remove(shape);
		if (undo.size() == 20 && !u) {
			undo.remove(0);
		}
		if (!u) {
			undo.push(new Operation("remove", shape));
			redo.clear();
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		int i = shapes.indexOf(oldShape);
		shapes.remove(i);
		shapes.add(i, newShape);
		if (undo.size() == 20 && !u) {
			undo.remove(0);
		}
		if (!u) {
			undo.push(new Operation("update", oldShape, newShape));
			redo.clear();
		}
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		Shape[] sh = new Shape[shapes.size()];
		for (int i = 0; i < shapes.size(); i++) {
			sh[i] = shapes.get(i);
		}
		return sh;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return this.p;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		if (!undo.isEmpty()) {
			System.out.println("undo");
			u = true;
			Operation op;
			op = undo.pop();
			if (op.getName() == "add") {
				this.removeShape(op.getS1());
				redo.push(new Operation("remove", op.getS1()));
			} else if (op.getName() == "remove") {
				this.addShape(op.getS1());
				redo.push(new Operation("add", op.getS1()));
			} else if (op.getName() == "update") {
				this.updateShape(op.getS2(), op.getS1());
				redo.push(new Operation("update", op.getS2(), op.getS1()));
			}
			u = false;
		}

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		if (!redo.isEmpty()) {
			u = true;
			Operation op;
			op = redo.pop();
			if (op.getName() == "add") {
				this.removeShape(op.getS1());
				undo.push(new Operation("remove", op.getS1()));
			} else if (op.getName() == "remove") {
				this.addShape(op.getS1());
				undo.push(new Operation("add", op.getS1()));
			} else if (op.getName() == "update") {
				this.updateShape(op.getS2(), op.getS1());
				undo.push(new Operation("update", op.getS2(), op.getS1()));
			}
			u = false;
		}

	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		// Json json = new Json();
		// json.save(null);
		if (path.contains("xml")) {
			try {
				FileWriter file = new FileWriter(path);
				file.write("<paint>\n");
				for (int i = 0; i < this.getShapes().length; i++) {
					Shape sh = this.getShapes()[i];
					file.write("<shape id=\"" + sh.getClass().getName() + "\">\n");
					file.write("<x>" + sh.getPosition().x + "</x>\n");
					file.write("<y>" + sh.getPosition().y + "</y>\n");
					file.write("<map>\n");
					Set<?> set = sh.getProperties().keySet();
					Object[] key = set.toArray();
					for (int j = 0; j < sh.getProperties().size(); j++) {
						file.write("<" + (String) key[j] + ">" + sh.getProperties().get((String) key[j]) + "</"
								+ (String) key[j] + ">\n");
					}
					file.write("</map>\n");
					file.write("<color>" + sh.getColor() + "</color>\n");
					file.write("<fillcolor>" + sh.getFillColor() + "</fillcolor>\n");
					file.write("</shape>\n");
				}
				file.write("</paint>");
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		path = path.toLowerCase();
		if (path.contains("xml")) {
			try {
				Shape sh;
				Point point = new Point();
				FileReader xmlFile = new FileReader(path);
				BufferedReader br = new BufferedReader(xmlFile);
				String line;
				int end = 0;
				br.readLine();
				line = br.readLine();
				while (!line.equals("</paint>")) {
					end = line.indexOf(">");
					sh = factory.makeShape(line.substring(42, end - 1));
					line = br.readLine();
					end = line.indexOf("/") - 1;
					point.x =(Integer) Integer.parseInt(line.substring(3, end));
					line = br.readLine();
					end = line.indexOf("/") - 1;
					point.y = Integer.parseInt(line.substring(3, end));
					sh.setPosition(new Point(point.x, point.y));
					br.readLine();
					line = br.readLine();
					end = line.indexOf("/") - 1;
					Map<String, Double> map = new HashMap<String, Double>();
					
					map.put("x", Double.parseDouble(line.substring(3, end)));
					line = br.readLine();
					end = line.indexOf("/") - 1;
					map.put("y", Double.parseDouble(line.substring(3, end)));
					sh.setProperties(new HashMap<>(map));
					br.readLine();
					line = br.readLine();
					String r = "", g = "", b = "";
					for (int m = 21; m < line.length(); m++) {
						int j = m;
						if (line.charAt(j) == '=' && (line.charAt(j - 1)) == 'r') {
							j++;
							while (line.charAt(j) != ',') {
								r += line.charAt(j);
								j++;
							}
							j--;
						}
						if (line.charAt(j) == '=' && (line.charAt(j - 1)) == 'g') {
							j++;
							while (line.charAt(j) != ',') {
								g += line.charAt(j);
								j++;
							}
							j--;
						}
						if (line.charAt(j) == '=' && (line.charAt(j - 1)) == 'b') {
							j++;
							while (line.charAt(j) != ',' && line.charAt(j) != ']') {
								b += line.charAt(j);
								j++;
							}
							j--;
							break;
						}
					}
					sh.setColor(new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
					line = br.readLine();
					r = "";
					g = "";
					b = "";
					for (int m = 21; m < line.length(); m++) {
						int j = m;
						if (line.charAt(j) == '=' && (line.charAt(j - 1)) == 'r') {
							j++;
							while (line.charAt(j) != ',') {
								r += line.charAt(j);
								j++;
							}
							j--;
						}
						if (line.charAt(j) == '=' && (line.charAt(j - 1)) == 'g') {
							j++;
							while (line.charAt(j) != ',') {
								g += line.charAt(j);
								j++;
							}
							j--;
						}
						if (line.charAt(j) == '=' && (line.charAt(j - 1)) == 'b') {
							j++;
							while (line.charAt(j) != ',' && line.charAt(j) != ']') {
								b += line.charAt(j);
								j++;
							}
							j--;
							break;
						}
					}
					sh.setFillColor(new Color(Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b)));
					shapes.add(sh);
					line = br.readLine();
					line = br.readLine();
					undo.clear();
					redo.clear();
				}
				br.close();
			} catch (Exception e) {

			}
		}
	}

	@Override
	public void installPluginShape(String jarPath) {
		// TODO Auto-generated method stub
		/*
		 * File jarFile = new File(jarPath); // String className = "helloworld.Main";
		 * try { URL fileURL = jarFile.toURI().toURL(); String jarURL = "jar:" + fileURL
		 * + "!/"; URL urls[] = { new URL(jarURL) }; URLClassLoader ucl = new
		 * URLClassLoader(urls); //Shapes m = (Shapes)
		 * Class.forName(ucl.getClass().getName(), true, ucl).newInstance();
		 * p.add((Class<? extends Shape>) Class.forName(ucl.getClass().getName(), true,
		 * ucl.getClass().getClassLoader())); } catch (MalformedURLException |
		 * ClassNotFoundException ex) { ex.printStackTrace(); }
		 */
		/*
		 * File myJar = new File(jarPath); try { URL url = myJar.toURI().toURL();
		 * 
		 * Class[] parameters = new Class[] { URL.class };
		 * 
		 * URLClassLoader sysLoader = (URLClassLoader)
		 * ClassLoader.getSystemClassLoader(); Class sysClass = URLClassLoader.class;
		 * try { System.out.println(sysClass.getName()); Method method =
		 * sysClass.getDeclaredMethod("addURL", parameters); method.setAccessible(true);
		 * method.invoke(sysLoader, new Object[] { url });
		 * 
		 * } catch (Exception ex) { System.err.println(ex.getMessage()); } } catch
		 * (MalformedURLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

	}

}
