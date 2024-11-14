package calorietracker;

import javax.swing.*;
import java.awt.*;

public class CalorieTrackerApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CalorieTrackerApp::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Calorie Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        CardLayout cardLayout = new CardLayout();
        frame.setLayout(cardLayout);

        // Add Panels
        frame.add(new HomePanel(cardLayout, frame), "Home");
        frame.add(new CalculatorPanel(cardLayout, frame), "Calculator");
        frame.add(new TrackerPanel(cardLayout, frame), "Tracker");

        // Show Home page initially
        cardLayout.show(frame.getContentPane(), "Home");

        frame.setVisible(true);
    }
}
