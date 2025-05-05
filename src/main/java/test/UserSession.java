package test;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

public class UserSession {
    private static String nickname = "";
    private static boolean darkMode = false;
    private static Color fontColor = Color.BLACK;
    private static double fontSize = 14.0;
    private static Color backgroundColor = null; // New

    public static void setNickname(String name) {
        nickname = name;
    }

    public static String getNickname() {
        return nickname;
    }

    public static void setDarkMode(boolean mode) {
        darkMode = mode;
    }

    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void setFontColor(Color color) {
        fontColor = color;
    }

    public static Color getFontColor() {
        return fontColor;
    }

    public static void setFontSize(double size) {
        fontSize = size;
    }

    public static double getFontSize() {
        return fontSize;
    }

    public static void setBackgroundColor(Color color) {
        backgroundColor = color;
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static void applySettings(Scene scene) {
        if (scene == null) return;

        StringBuilder style = new StringBuilder();
        style.append("-fx-font-size: ").append(fontSize).append("px;");

        // Use custom background color if provided
        if (backgroundColor != null) {
            String bgHex = String.format("#%02x%02x%02x",
                    (int) (backgroundColor.getRed() * 255),
                    (int) (backgroundColor.getGreen() * 255),
                    (int) (backgroundColor.getBlue() * 255)
            );
            style.append("-fx-background-color: ").append(bgHex).append(";");
        } else {
            style.append(darkMode ? "-fx-base: #2c2c2c;" : "-fx-base: #FFFFFF;");
        }

        scene.getRoot().setStyle(style.toString());

        applyTextStyles(scene.getRoot());
    }

    private static void applyTextStyles(Node node) {
        if (fontColor == null) return;

        String colorHex = String.format("#%02x%02x%02x",
                (int) (fontColor.getRed() * 255),
                (int) (fontColor.getGreen() * 255),
                (int) (fontColor.getBlue() * 255)
        );

        String textStyle = "-fx-text-fill: " + colorHex + "; -fx-font-size: " + fontSize + "px;";

        if (node instanceof Labeled labeled) {
            labeled.setStyle(textStyle);
        } else if (node instanceof TextInputControl textInput) {
            textInput.setStyle(textStyle);
        }

        if (node instanceof Parent parent) {
            for (Node child : parent.getChildrenUnmodifiable()) {
                applyTextStyles(child);
            }
        }
    }
}