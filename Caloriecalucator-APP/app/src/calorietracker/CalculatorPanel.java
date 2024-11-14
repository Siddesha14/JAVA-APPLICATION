package calorietracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorPanel extends JPanel {

    public CalculatorPanel(CardLayout cardLayout, JFrame frame) {
        setLayout(new BorderLayout());

        JPanel calculatorFormPanel = new JPanel(new GridLayout(8, 2));

        // Input fields
        JTextField heightField = new JTextField();
        JTextField weightField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<String> activityLevelCombo = new JComboBox<>(new String[]{"Sedentary", "Moderate", "Active"});

        // Buttons
        JButton calculateButton = new JButton("Calculate Calories");
        JButton refreshButtonCalculator = new JButton("Refresh");
        JButton switchToTrackerButton = new JButton("Go to Calorie Tracker");
        JButton homeButtonCalculator = new JButton("Home");

        // Result label
        JLabel resultLabel = new JLabel("");

        // Action Listeners
        calculateButton.addActionListener(e -> {
            double height = Double.parseDouble(heightField.getText());
            double weight = Double.parseDouble(weightField.getText());
            int age = Integer.parseInt(ageField.getText());
            String activityLevel = (String) activityLevelCombo.getSelectedItem();

            double bmr = 10 * weight + 6.25 * height - 5 * age + 5;
            switch (activityLevel) {
                case "Sedentary": bmr *= 1.2; break;
                case "Moderate": bmr *= 1.55; break;
                case "Active": bmr *= 1.725; break;
            }

            double calorieGoal = Math.round(bmr);
            resultLabel.setText("Daily Calorie Goal: " + calorieGoal + " calories");

            DataUtils.saveCalorieGoal(calorieGoal);
        });

        refreshButtonCalculator.setPreferredSize(new Dimension(70, 30)); // Smaller button size
        refreshButtonCalculator.addActionListener(e -> {
            DataUtils.refreshData();
            resultLabel.setText("Daily Calorie Goal: 0 calories");
        });

        switchToTrackerButton.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Tracker"));
        homeButtonCalculator.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Home"));

        // Add components to the panel
        calculatorFormPanel.add(new JLabel("Height (cm):"));
        calculatorFormPanel.add(heightField);
        calculatorFormPanel.add(new JLabel("Weight (kg):"));
        calculatorFormPanel.add(weightField);
        calculatorFormPanel.add(new JLabel("Age:"));
        calculatorFormPanel.add(ageField);
        calculatorFormPanel.add(new JLabel("Activity Level:"));
        calculatorFormPanel.add(activityLevelCombo);
        calculatorFormPanel.add(calculateButton);
        calculatorFormPanel.add(resultLabel);
        calculatorFormPanel.add(switchToTrackerButton);
        calculatorFormPanel.add(homeButtonCalculator);
        calculatorFormPanel.add(refreshButtonCalculator);

        add(calculatorFormPanel, BorderLayout.CENTER);
    }
}

