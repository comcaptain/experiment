package cube;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CubeGUI extends JFrame{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 4429996090242195421L;
//	
//	private int cellWidth = 50;
//	
//	private Cube cube;
//	
//	private JPanel cubePanel;
//	
//	private List<CubeCellTextField> cells;
//	
//	public CubeGUI() throws Exception {
//		this.cube = new Cube();
//		initGUI();
//		this.startNewGame();
//		this.repaint();
//	}
//
//	private void startNewGame() throws Exception {
//		cube.generate();
//		updateCubeData();
//	}
//	
//	private void updateCubeData() {
//		if (cubePanel != null) {
//			cubePanel.removeAll();
//		}
//		cubePanel = new JPanel();
//		this.add(cubePanel);
//		cubePanel.setBounds(20, 20, cellWidth * cube.getWidth(), cellWidth * cube.getWidth());
//		cubePanel.setLayout(null);
//		
//		this.cells = new LinkedList<CubeCellTextField>();
//		int[][] data = this.cube.getData();
//		for (int y = 0; y < data.length; y++) {
//			for (int x = 0; x < data[y].length; x++) {
//				int value = data[y][x];
//				CubeCellTextField cell = new CubeCellTextField();
//				if (value > 0) {
//					cell.setText(value + "");
//					cell.setBackground(new Color(0xF3D774));
//					cell.setEditable(false);
//				}
//				else {
//					cell.setFlipped(true);
//					cell.setAnswer(-value);
//					cell.setBackground(new Color(0xbbada0));
//					cell.setForeground(Color.white);
//				}
//				cell.setFont(new Font("Consolas", Font.PLAIN, 20));
//				cell.setX(x);
//				cell.setY(y);
//				this.cells.add(cell);
//				addCell(cell, x, y);
//			}
//		}
//	}
//	
//	private void winCheck() {
//		if (this.cube.check()) {
//			JOptionPane.showMessageDialog(null, "You win");
//			try {
//				this.startNewGame();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	}
//	
//	private void addCell(JTextField cell, int x, int y) {
//		cell.setBounds(this.cellWidth * x, this.cellWidth * y, this.cellWidth, this.cellWidth);
//		cell.addKeyListener(new KeyListener() {
//			@Override
//			public void keyPressed(KeyEvent arg0) {}
//			@Override
//			public void keyReleased(KeyEvent event) {
//				CubeCellTextField field = (CubeCellTextField) event.getSource();
//				int[][] data = CubeGUI.this.cube.getData();
//				data[field.getY()][field.getX()] = Integer.parseInt(field.getText());
//				CubeGUI.this.winCheck();
//			}
//			@Override
//			public void keyTyped(KeyEvent arg0) {}
//			
//		});
//		cell.setHorizontalAlignment(JTextField.CENTER);
//		this.cubePanel.add(cell);
//	}
//	
//	private void initGUI() {
//		this.setSize(cellWidth * cube.getWidth() + 40, cellWidth * cube.getWidth() + 110);
//		this.setVisible(true);
//		this.setLocationRelativeTo(null);
//		this.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent event) {
//				System.exit(0);
//			}
//		});
//		this.setLayout(null);
//		this.setTitle("CUBE");
//		this.setResizable(false);
//		
//		JPanel controlPanel = new JPanel();
//		controlPanel.setBounds(0, cellWidth * cube.getWidth() + 30, cellWidth * cube.getWidth() + 40, 100);
//		this.add(controlPanel);
//		
//		JButton button = new JButton("New Game");
//		controlPanel.add(button);
//		button.setBounds(0, 0, cellWidth * 3, 30);
//		
//		button.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent event) {
//				try {
//					CubeGUI.this.startNewGame();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//	
//	public static void main(String[] args) throws Exception {
//		new CubeGUI();
//	}
	
}
