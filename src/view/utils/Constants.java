package view.utils;

import java.awt.Color;

import view.JFrameMain;

public class Constants {
	
	//TEXT
	public static final String VERSION = "Version 1.0";
	public static final String HELP = "Recuerda subir un archivo donde estén registrados en una columna los números Ri (sin cabecera, solo los número)... :)";
	
	//COMMANDS
	public static final String COMMAND_LOAD_FILE = "COMMAND_LOAD_FILE";
	public static final String COMMAND_START = "COMMAND_START";
	public static final String COMMAND_TEST1 = "COMMAND_TEST1";
	public static final String COMMAND_TEST2 = "COMMAND_TEST2";
	public static final String COMMAND_TEST3 = "COMMAND_TEST3";
	public static final String COMMAND_TEST4 = "COMMAND_TEST4";
	public static final String COMMAND_TEST5 = "COMMAND_TEST5";
	
	//ERRORS
	public static final String ERROR = "Error";

	// FONT
	public static final String FONT_APP = "Cambria Math";
	public static final int FONT_SIZE_APP_BUTTONS = 27 * JFrameMain.WIDTH_FRAME / 1920;
	public static final int FONT_SIZE_APP_TITLES = 45 * JFrameMain.WIDTH_FRAME / 1920;
	public static final int FONT_SIZE_APP_LABELS = 34 * JFrameMain.WIDTH_FRAME / 1920;
	public static final int FONT_SIZE_APP_PLACEHOLDER = 25 * JFrameMain.WIDTH_FRAME / 1920;

	// COLORS
	public static final Color COLOR_BACKGROUND_GRADIENT_ONE = new Color(57, 55, 55);
	public static final Color COLOR_BACKGROUND_GRADIENT_TWO = new Color(118, 113, 113);
	public static final Color COLOR_BACKGROUND_CONTENT = new Color(59, 56, 56);
	public static final Color COLOR_BUTTONS_METHODS = new Color(88, 84, 84);
	public static final Color COLOR_BUTTONS_METHODS_HOVER = new Color(38, 38, 38);
	public static final Color COLOR_BORDER = new Color(200, 200, 200);

	//PATHS
	public static final String PATH_IMG_LOGO = "/res/sim.png";
	
}
