package prj.codinners.positiontone.client;

import prj.codinners.positiontone.PropertiesManager;

import java.awt.*;

public class Colors {
    private static PropertiesManager propertiesManager = new PropertiesManager("./config/client/colors.ini");

    public static Color BACKGROUND = new Color(Integer.parseInt(propertiesManager.getProperties("background").substring(1), 16));
    public static Color LINE = new Color(Integer.parseInt(propertiesManager.getProperties("line").substring(1), 16));

    public static Color BLACK = new Color(Integer.parseInt(propertiesManager.getProperties("black").substring(1), 16));
    public static Color WHITE = new Color(Integer.parseInt(propertiesManager.getProperties("white").substring(1), 16));
}
