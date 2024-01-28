package workspace;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class IntroWindow extends Window {

	private Button openInputWindowBtn;
	private Button openMainWindowBtn;
	private ImageView helloImageView;
	private VBox layout;
	
	public IntroWindow(WindowManager manager, int width, int height, String title) {
		super(manager, width, height, WindowID.INTRO, title);
	}

	@Override
	public void initScene() {
		openInputWindowBtn = new Button("Open  'CREATE MARTYR LIST'  window");
		openMainWindowBtn = new Button("Open  'MEMORY TEST'  window");
		helloImageView = new ImageView("file:///C:/Users/ismae/eclipse-workspace/Game/src/pictures/pngegg%20(2).png");
		layout = new VBox(15, helloImageView, openInputWindowBtn, openMainWindowBtn);
		
		openInputWindowBtn.setOnAction(e -> openInputWindow());
		openMainWindowBtn.setOnAction(e -> openMainWindow());
		
		helloImageView.fitHeightProperty().bind(layout.heightProperty().divide(2));
		helloImageView.setPreserveRatio(true);
		
		layout.setPadding(new Insets(10));
		layout.setAlignment(Pos.CENTER);
		
		setScene(new Scene(layout, getWidth(), getHeight()));
	}
	
	@Override
	public void applyTheme(Theme theme) {
		switch (theme) {
			case LIGHT:
				openInputWindowBtn.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				openMainWindowBtn.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				layout.setStyle("-fx-background-color: #ebdecc");  // #e3e1de   old: #ebdecc
				break;
				
			case DARK:
				openInputWindowBtn.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				openMainWindowBtn.setStyle("-fx-font-size: 20; -fx-font-weight: bold; -fx-font-family: 'Cambria'; -fx-background-color: #fca503; -fx-text-fill: white;");
				layout.setStyle("-fx-background-color: #262523");
				break;
		}
	}

	@Override
	protected void updateContent() {
		// do nothing
	}

	/**
	 * Opens main window.
	 */
	private void openMainWindow() {
		getManager().display(WindowID.MAIN);
	}

	/**
	 * Opens Input window.
	 */
	private void openInputWindow() {
		getManager().display(WindowID.INPUT);
	}
	
}
