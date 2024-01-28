package workspace;

import java.util.ArrayList;

import javafx.stage.Stage;

public class WindowManager {
	
	private Theme theme;
	private ArrayList<Window> windows;
	private Stage stage;
	
	/**
	 * Constructs a WindowManager object.
	 * @param stage
	 */
	public WindowManager(Stage stage) {
		this.windows = new ArrayList<>();
		this.stage = stage;
		theme = Theme.LIGHT;
	}

	/**
	 * Displays a window object by the given Window ID.
	 * @param id
	 */
	public void display(WindowID id) {
		for (Window window : windows) 
			if (window.getId() == id) {
				stage.setScene(window.getScene());
				window.updateContent();
				stage.show();
			}
	}
	
	/**
	 * Apply given theme to all windows.
	 * @param theme
	 */
	public void applyThemeToWindows(Theme theme) {
		this.theme = theme;
		for (Window window : windows) 
			window.applyTheme(theme);
	}
	
	/**
	 * Returns the current theme.
	 * @return
	 */
	public Theme getCurrentTheme() {
		return theme;
	}
	
	/**
	 * Adds windows to the manager.
	 * @param windows
	 */
	public void addWindows(ArrayList<Window> windows) {
		this.windows.addAll(windows);
		initAllWindows();
		applyThemeToWindows(theme);
	}

	/**
	 * Initialises all windows.
	 */
	private void initAllWindows() {
		for (Window window : windows) {
			window.initScene();
			window.setInitialized(true);
		}
			
	}
	
	
}
