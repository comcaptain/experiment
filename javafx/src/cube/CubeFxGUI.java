package cube;

import java.util.LinkedList;
import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

public class CubeFxGUI extends Application{
	
	private int cellWidth = 50;
	
	private Cube cube;
	
	private GridPane grid;
	
	private List<CubeCellTextField> cells;

	private void startNewGame() throws Exception {
		cube.generate();
		updateCubeData();
	}
	
	private void updateCubeData() {
		int[][] data = this.cube.getData();
		for (int y = 0; y < data.length; y++) {
			for (int x = 0; x < data[y].length; x++) {
				CubeCellTextField cell = this.cells.get(y * 9 + x);
				int value = data[y][x];
				if (value < 0) {
					cell.setEditable(true);
					cell.setText("");
					cell.getStyleClass().remove("readonly-cell");
					cell.setFocusTraversable(true);
				}
				else {
					cell.setEditable(false);
					cell.setText(value + "");
					cell.getStyleClass().add("readonly-cell");
					cell.setFocusTraversable(false);
				}
			}
		}
	}
	
	private void winCheck() {
		if (this.cube.check()) {
			JOptionPane.showMessageDialog(null, "You win");
		}
	}
	
	private void addCell(CubeCellTextField cell, int x, int y) {
		cell.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				CubeCellTextField field = (CubeCellTextField) event.getSource();
				int[][] data = CubeFxGUI.this.cube.getData();
				int newValue = -1;
				try {
					newValue = Integer.parseInt(field.getText());
				}
				catch (NumberFormatException e) {
					field.setText("");
					return;
				}
				if (newValue < 0 || newValue > 9) {
					field.setText("");
					return;
				}
				data[field.getY()][field.getX()] = newValue;
				CubeFxGUI.this.winCheck();
			}
			
		});
		cell.setAlignment(Pos.CENTER);
		this.grid.add(cell, x, y);
	}
	
	private void initGUI(Stage stage) {
		
		grid = new GridPane();
		grid.setHgap(2);
		grid.setVgap(2);
		grid.setPadding(new Insets(25, 25, 25, 25));
		
		Scene scene = new Scene(grid, cellWidth * cube.getWidth() + 40, cellWidth * cube.getWidth() + 110);
		stage.setScene(scene);
		stage.setTitle("CUBE");
		scene.getStylesheets().add(this.getClass().getResource("cube.css").toExternalForm());
		
		
		Button button = new Button("New Game");
		button.setPrefSize(120, 30);
		HBox hbBtn = new HBox();
		hbBtn.setPadding(new Insets(10, 0, 0, 0));
		hbBtn.setAlignment(Pos.CENTER);
		hbBtn.getChildren().add(button);
		grid.add(hbBtn, 3, 10, 3, 1);
		
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					CubeFxGUI.this.startNewGame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		button.setId("new-game");
		
		this.cells = new LinkedList<CubeCellTextField>();
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				CubeCellTextField cell = new CubeCellTextField();
				cell.setX(x);
				cell.setY(y);
				cell.setPrefSize(50, 50);
				cell.getStyleClass().add("cube-cell");
				this.cells.add(cell);
				addCell(cell, x, y);
				boolean isOdd = (y / 3 * 3 + x / 3) % 2 == 1;
				if (isOdd) cell.getStyleClass().add("odd-cell");
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.cube = new Cube();
		initGUI(stage);
		this.startNewGame();
		stage.show();
	}
	
}
