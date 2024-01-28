package workspace;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import data.Martyr;
import io.MartyrOutputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class InputWindow extends Window {

	private static final String MARTYRS_FILE_NAME = "Martyrs.dat";

	private static final String SUCCESS_IMAGE_URL = "file:///C:/Users/ismae/git/pro-repository/Game/src/pictures/success.png";

	private static final String FAIL_IMAGE_URL = "file:///C:/Users/ismae/git/pro-repository/Game/src/pictures/error.png";
	
	private Label addLabel;
	private Label statusLabel;
	private TextField inputTF;
	private Button addBtn;
	private Button returnButton;
	private FlowPane pane;
	private BorderPane layout;
	
	private ImageView successIV;
	private ImageView failureIV;
	
	private MartyrOutputStream out;

	public InputWindow(WindowManager manager, int width, int height, String title) {
		super(manager, width, height, WindowID.INPUT, title);
	}

	@Override
	public void initScene() {
		try {
			out = new MartyrOutputStream(MARTYRS_FILE_NAME, true);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
		successIV = new ImageView(SUCCESS_IMAGE_URL);
		failureIV = new ImageView(FAIL_IMAGE_URL);		
		addLabel = new Label("Add Martyr: (Name  DateOfMartyrdom)");
		statusLabel = new Label();
		inputTF = new TextField("Ibrahim  23-1-2024");
		addBtn = new Button("Add to File");
		returnButton = new Button(" Return ");
		pane = new FlowPane(15, 15, addLabel, inputTF, addBtn);
		layout = new BorderPane();
		
		successIV.setFitHeight(55);
		failureIV.setFitHeight(55);
		successIV.setPreserveRatio(true);
		failureIV.setPreserveRatio(true);
		statusLabel.setGraphicTextGap(10);
		statusLabel.setContentDisplay(ContentDisplay.RIGHT);
		
		addBtn.setOnAction(e -> handleAddBtn());
		inputTF.setOnAction(e -> handleAddBtn());
		returnButton.setOnAction(e -> handleReturnButton());
		pane.setAlignment(Pos.CENTER);
		
		layout.setPadding(new Insets(20));
		layout.setCenter(pane);
		layout.setBottom(statusLabel);
		layout.setLeft(returnButton);
		BorderPane.setAlignment(returnButton, Pos.TOP_LEFT);
		BorderPane.setAlignment(pane, Pos.CENTER);
		BorderPane.setAlignment(statusLabel, Pos.CENTER);
		
		setScene(new Scene(layout, getWidth(), getHeight()));
	}

	@Override
	public void applyTheme(Theme theme) {
		switch (theme) {
			case LIGHT:
				addLabel.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-font-family: 'Cambria';");
				inputTF.setStyle("-fx-font-size: 15; -fx-font-family: 'Cambria';");
				addBtn.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: brown; -fx-text-fill: white;");
				statusLabel.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-font-family: 'Cambria';");
				returnButton.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				layout.setStyle("-fx-background-color: #ebdecc");
				break;
				
			case DARK:
				addLabel.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-text-fill: white;");
				statusLabel.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-text-fill: white;");
				inputTF.setStyle("-fx-font-size: 15; -fx-font-family: 'Cambria'; -fx-background-color: grey; -fx-text-fill: white;");
				addBtn.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: brown; -fx-text-fill: white;");
				returnButton.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				layout.setStyle("-fx-background-color: #262523");
				break;
		}
	}
	
	@Override
	protected void updateContent() {
		// do nothing
	}
	
	/**
	 * Returns to the intro scene.
	 */
	private void handleReturnButton() {
		getManager().display(WindowID.INTRO);
		statusLabel.setText("");
		statusLabel.setGraphic(null);
	}

	/**
	 * Adds the input martyr to the list.
	 */
	private void handleAddBtn() {
		if (inputTF.getText().trim().matches("")) return;
		String[] input = inputTF.getText().split("\s+");
 		try {
 			if (input.length != 2) throw new InputMismatchException("Invalid Input");
			Martyr martyr = new Martyr(input[0], input[1]);
			out.writeMartyr(martyr);
			statusLabel.setText("Added successfully");
			statusLabel.setGraphic(successIV);
			
		} catch (Exception e) {
			statusLabel.setText("Adding failed. An error occurred: " + e.getMessage());
			statusLabel.setGraphic(failureIV);
		}
		inputTF.setText("");
	}
	
}
