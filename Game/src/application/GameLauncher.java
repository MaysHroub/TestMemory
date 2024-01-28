package application;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import workspace.InputWindow;
import workspace.IntroWindow;
import workspace.MainWindow;
import workspace.Window;
import workspace.WindowID;
import workspace.WindowManager;


public class GameLauncher extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		WindowManager manager = new WindowManager(primaryStage);
		
		ArrayList<Window> windows = new ArrayList<>();
		windows.add(new IntroWindow(manager, 700, 500, "The war in Gaza"));
		windows.add(new MainWindow(manager, 930, 600, "Memory Test"));
		windows.add(new InputWindow(manager, 800, 300, "Add a martyr to file"));
		
		manager.addWindows(windows);
		
		manager.display(WindowID.INTRO);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}











