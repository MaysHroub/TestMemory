package workspace;

import java.io.IOException;
import java.util.ArrayList;

import data.Martyr;
import io.MartyrInputStream;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class MainWindow extends Window {
	
	private static final String MARTYRS_FILE_NAME = "Martyrs.dat";

	private static final String CORRECT_IMAGE_URL = "file:///C:/Users/ismae/eclipse-workspace/Game/src/pictures/pngwing.com.png"; //"file:///C:/Users/ismae/eclipse-workspace/Game/src/pictures/happybunny2.png";
	private static final String WRONG_IMAGE_URL = "file:///C:/Users/ismae/eclipse-workspace/Game/src/pictures/pngegg%20(6).png";//"file:///C:/Users/ismae/eclipse-workspace/Game/src/pictures/thinkingbunny.png";
	private static final String INSTRUCTION_IMAGE_URL = "file:///C:/Users/ismae/eclipse-workspace/Game/src/pictures/pngegg%20(1).png";//"file:///C:/Users/ismae/eclipse-workspace/Game/src/pictures/bunny.png";
	
	private Label mainTitleLabel;
	private Label desc1Label;
	private Label desc2Label;
	private GridPane martyrsGP;
	private Label martyredBeforeLabel;
	private TextField martyr1TF;
	private TextField martyr2TF;
	private Label statusLabel;
	private Button submitBtn;
	private Button clearBtn;
	private Button returnButton;
	private ComboBox<String> themesBox;
	private ImageView instructionImageView;
	private ImageView statusImageView;

	private HBox descHB;
	private BorderPane layout;
	
	private ArrayList<Martyr> martyrs;
	
	public MainWindow(WindowManager manager, int width, int height, String title) {
		super(manager, width, height, WindowID.MAIN, title);
	}

	@Override
	public void initScene() {
		mainTitleLabel = new Label("TEST YOUR MEMORY");
		themesBox = new ComboBox<>();
		desc1Label = new Label(
				"\"The elderly dies and the young forget\".\n  This is what Israel aims for. So, Let's see if your mental strength\n  can defeat their myth and remember who was martyred before.");
		desc2Label = new Label(
				"Pick two martyr names from the following list. Enter them in the boxes in correct order,\nand then press the Submit button!");
		instructionImageView = new ImageView(INSTRUCTION_IMAGE_URL);
		descHB = new HBox(30);
		martyredBeforeLabel = new Label("Martyred before");
		martyr1TF = new TextField();
		martyr2TF = new TextField();
		martyrsGP = new GridPane();
		submitBtn = new Button("  Submit  ");
		clearBtn = new Button("  Clear  ");
		returnButton = new Button(" Return ");
		statusLabel = new Label();
		statusImageView = new ImageView();
		layout = new BorderPane();
		
		themesBox.getItems().addAll("Light", "Dark");
		themesBox.getSelectionModel().select((getManager().getCurrentTheme() == Theme.LIGHT) ? 0 : 1);
		themesBox.setOnAction(e -> handleSwitchingThemes());
		
		BorderPane mainBP = new BorderPane();
		mainBP.setCenter(mainTitleLabel);
		mainBP.setRight(themesBox);
		mainBP.setLeft(returnButton);
		mainBP.setPadding(new Insets(0, 0, 15, 0));
		BorderPane.setMargin(themesBox, new Insets(20));

		desc1Label.setTextAlignment(TextAlignment.LEFT);
		desc2Label.setTextAlignment(TextAlignment.LEFT);

		VBox descVB = new VBox(20, desc1Label, desc2Label);
		descVB.setAlignment(Pos.CENTER);

		instructionImageView.setFitHeight(150);
		instructionImageView.setPreserveRatio(true);
		
		descHB.getChildren().addAll(instructionImageView, descVB);
		descHB.setAlignment(Pos.CENTER);

		VBox headerVB = new VBox(10, mainBP, descHB);
		
		martyrsGP.setHgap(3);
		martyrsGP.setVgap(5);
		martyrsGP.setPadding(new Insets(5));
		martyrsGP.setAlignment(Pos.CENTER);

		HBox inputHB = new HBox(20, martyr1TF, martyredBeforeLabel, martyr2TF);
		inputHB.setAlignment(Pos.CENTER);
		
		submitBtn.setOnAction(e -> handleSubmitBtn());
		clearBtn.setOnAction(e -> handleClearBtn());
		returnButton.setOnAction(e -> handleReturnButton());

		statusImageView.setFitHeight(130);
		statusImageView.setPreserveRatio(true);
		
		HBox btnsHB = new HBox(30, submitBtn, clearBtn);
		btnsHB.setAlignment(Pos.CENTER);
		btnsHB.setPadding(new Insets(0, 0, 0, 30));

		BorderPane btnStatusBP = new BorderPane();
		btnStatusBP.setLeft(btnsHB);
		btnStatusBP.setCenter(statusLabel);
		btnStatusBP.setRight(statusImageView);
		btnStatusBP.setPadding(new Insets(0, 15, 0, 0));

		VBox lowerVB = new VBox(20, inputHB, btnStatusBP);

		layout.setTop(headerVB);
		layout.setCenter(martyrsGP);
		layout.setBottom(lowerVB);
		layout.setPadding(new Insets(10, 10, 10, 10));

		setScene(new Scene(layout, getWidth(), getHeight()));
	}

	@Override
	public void applyTheme(Theme theme) {
		switch (theme) {
			case LIGHT:
				mainTitleLabel.setStyle("-fx-font-size: 35; -fx-font-weight: bold; -fx-font-family: 'Cambria';");
				themesBox.setStyle(
						"-fx-font-size: 14; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: BURLYWOOD;");
				desc1Label.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-font-family: 'Cambria';");
				desc2Label.setStyle("-fx-font-size: 16; -fx-font-family: 'Cambria';");
				martyredBeforeLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria';");
				martyr1TF.setStyle("-fx-font-size: 15; -fx-font-family: 'Cambria';");
				martyr2TF.setStyle("-fx-font-size: 15; -fx-font-family: 'Cambria';");
				statusLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Cambria';");
				submitBtn.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				clearBtn.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				returnButton.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				descHB.setStyle("-fx-background-color: ANTIQUEWHITE");
				layout.setStyle("-fx-background-color: #ebdecc");
				break;
				
			case DARK:
				mainTitleLabel.setStyle("-fx-font-size: 35; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-text-fill: white;");
				themesBox.setStyle(
						"-fx-font-size: 14; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: BURLYWOOD;");
				desc1Label.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-text-fill: white;");
				desc2Label.setStyle("-fx-font-size: 16; -fx-font-family: 'Cambria'; -fx-text-fill: white;");
				martyredBeforeLabel.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-text-fill: white;");
				martyr1TF.setStyle("-fx-font-size: 15; -fx-font-family: 'Cambria'; -fx-background-color: grey; -fx-text-fill: white;");
				martyr2TF.setStyle("-fx-font-size: 15; -fx-font-family: 'Cambria'; -fx-background-color: grey; -fx-text-fill: white;");
				statusLabel.setStyle("-fx-font-size: 20; -fx-font-family: 'Cambria'; -fx-text-fill: white;");
				submitBtn.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				clearBtn.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				returnButton.setStyle("-fx-font-size: 15; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				descHB.setStyle("-fx-background-color: #2e2c28");
				layout.setStyle("-fx-background-color: #262523");
				break;
		}
		
	}
	
	@Override
	protected void updateContent() {
		uploadData();
	}
	
	/**
	 * Returns to the intro window.
	 */
	private void handleReturnButton() {
		getManager().display(WindowID.INTRO);
	}

	/**
	 * Switch the theme of this window and the rest of them.
	 */
	private void handleSwitchingThemes() {
		getManager().applyThemeToWindows((themesBox.getValue().equalsIgnoreCase("dark")) ? Theme.DARK : Theme.LIGHT);
	}

	/**
	 * Handles the action event of the submit button.
	 */
	private void handleSubmitBtn() {
		String t1 = martyr1TF.getText(), t2 = martyr2TF.getText();
		String imageName = WRONG_IMAGE_URL;
		if (t1.equals("") || t2.equals(""))
			statusLabel.setText("Enter names in both boxes. Then press Submit.");
		else if (!inList(t1) && !inList(t2))
			statusLabel.setText("Neither entry is in the list.");
		else if (!inList(t1))
			statusLabel.setText("First entry is not in the list. Check spelling.");
		else if (!inList(t2))
			statusLabel.setText("Second entry is not in the list. Check spelling.");
		else if (t1.equalsIgnoreCase(t2))
			statusLabel.setText("You entered the same name. Try again.");
		else 
			if (isOrderCorrect(t1, t2)) {
				statusLabel.setText("You are correct!!!");
				imageName = CORRECT_IMAGE_URL;
			}
			else 
				statusLabel.setText("Wrong :/ Try again.");
		
		statusImageView.setImage(new Image(imageName));
	}

	/**
	 * Checks if the first martyr has mortared before the second one.
	 * @param mar1
	 * @param mar2
	 * @return {@code true} if mar1 has mortared before mar2, {@code false} otherwise.
	 */
	private boolean isOrderCorrect(String mar1, String mar2) {
		String[] date1 = getMartyr(mar1).getDateOfMartyrdom().split("-");
		String[] date2 = getMartyr(mar2).getDateOfMartyrdom().split("-");
		int d1 = Integer.parseInt(date1[0]), m1 = Integer.parseInt(date1[1]), y1 = Integer.parseInt(date1[2]);
		int d2 = Integer.parseInt(date2[0]), m2 = Integer.parseInt(date2[1]), y2 = Integer.parseInt(date2[2]);
		
		return (y1 < y2) ? true : (y1 == y2 && m1 < m2) ? true : (m1 == m2 && d1 < d2) ? true : false;
	}

	/**
	 * Handles the action event of the submit button.
	 */
	private void handleClearBtn() {
		martyr1TF.setText("");
		martyr2TF.setText("");
		statusLabel.setText("");
		statusImageView.setImage(null);
	}

	/**
	 * Uploads martyrs from the file.
	 */
	private void uploadData() {
		try (MartyrInputStream in = new MartyrInputStream(MARTYRS_FILE_NAME)) {
			martyrs = in.readMartyrs();
		} catch (IOException e) {
			System.out.println(e);
		}
		removeRedundancy();
		populateGridPane();
	}

	/**
	 * Fills the gridpane with the martyrs' name.
	 */
	private void populateGridPane() {
		if (martyrs == null) return;
		int row = 0, col = 0;
		for (Martyr martyr : martyrs) {
			Label l = new Label(String.format("  %s  ", martyr.getName()));
			l.setStyle("-fx-font-size: 17; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #b88b39; -fx-background-radius: 10; -fx-text-fill: white; ");
			l.setTextAlignment(TextAlignment.CENTER);
			martyrsGP.add(l, row++, col);
			if (row % 10 == 0) {
				row = 0; col++;
			}
		}
	}
	
	/**
	 * Checks if the given martyr's name is in the list.
	 * @param martyrName
	 * @return {@code true} if the name is in the list, {@code false} otherwise.
	 */
	private boolean inList(String martyrName) {
		return getMartyr(martyrName) != null;
	}
	
	/**
	 * Returns the martyr object by its name.
	 * @param name
	 * @return The martyr object if it's found, and null otherwise.
	 */
	private Martyr getMartyr(String name) {
		for (Martyr martyr : martyrs) 
			if (martyr.getName().equalsIgnoreCase(name))
				return martyr;
		return null;
	}
	
	
	private void removeRedundancy() {
		for (int i = 0; i < martyrs.size(); i++) 
			for (int j = i + 1; j < martyrs.size(); j++) 
				if ( martyrs.get(i).equals(martyrs.get(j)) ) {
					martyrs.remove(j);
					j--;
				}
	}

}

