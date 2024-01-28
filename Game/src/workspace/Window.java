package workspace;

import javafx.scene.Scene;

public abstract class Window {

	private WindowManager manager;
	private int width;
	private int height;
	private WindowID id;
	private String title;
	private Scene scene;
	
	private boolean initialized;

	/**
	 * Constructs a window object with the specified information.
	 * @param manager
	 * @param width
	 * @param height
	 * @param id
	 * @param title
	 */
	protected Window (WindowManager manager, int width, int height, WindowID id, String title) {
		setWidth(width);
		setHeight(height);
		this.id = id;
		this.title = title;
		this.manager = manager;
		setInitialized(false);
	}

	
	// Setters and getters for each property.
	public void setManager(WindowManager manager) {
		this.manager = manager;
	}

	public String getTitle() {
		return title;
	}

	public WindowManager getManager() {
		return manager;
	}

	public WindowID getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Scene getScene() {
		return scene;
	}
	
	protected void setScene(Scene scene) {
		this.scene = scene;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}
	
	
	/**
	 * Prepares the components and initialises the scene of this window.
	 */
	protected abstract void initScene();
	
	/**
	 * Updates the content of the scene's components.
	 */
	protected abstract void updateContent();
	
	/**
	 * Apply the given theme to the window.
	 * @param theme
	 */
	public abstract void applyTheme(Theme theme);
	
	
}
