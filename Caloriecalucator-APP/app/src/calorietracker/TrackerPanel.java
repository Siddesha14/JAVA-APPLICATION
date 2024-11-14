package calorietracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TrackerPanel extends JPanel {

    // Declare the totalCaloriesConsumed variable at the class level
    private double totalCaloriesConsumed;

    public TrackerPanel(CardLayout cardLayout, JFrame frame) {
        setLayout(new BorderLayout());

        // Load total calories consumed from file
        totalCaloriesConsumed = DataUtils.loadTotalCalories();

        JPanel trackerFormPanel = new JPanel(new GridLayout(7, 2));

        // Input fields
        JTextField calorieInputField = new JTextField();

        // Buttons
        JButton addButton = new JButton("Add Calories");
        JButton refreshButtonTracker = new JButton("Refresh");
        JButton switchToCalculatorButton = new JButton("Go to Calorie Calculator");
        JButton homeButtonTracker = new JButton("Home");

        // Labels
        JLabel totalLabel = new JLabel("Total Consumed: " + totalCaloriesConsumed + " calories");
        JLabel remainingLabel = new JLabel("Remaining: " + (DataUtils.loadCalorieGoal() - totalCaloriesConsumed) + " calories");

        // Action Listeners
        addButton.addActionListener(e -> {
            try {
                double caloriesToAdd = Double.parseDouble(calorieInputField.getText());
                totalCaloriesConsumed += caloriesToAdd; // Add calories

                // Update total and remaining labels
                totalLabel.setText("Total Consumed: " + totalCaloriesConsumed + " calories");
                remainingLabel.setText("Remaining: " + (DataUtils.loadCalorieGoal() - totalCaloriesConsumed) + " calories");

                // Save updated total calories
                DataUtils.saveTotalCalories(totalCaloriesConsumed);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            }
        });

        refreshButtonTracker.setPreferredSize(new Dimension(70, 30)); // Smaller button size
        refreshButtonTracker.addActionListener(e -> {
            DataUtils.refreshData();
            totalCaloriesConsumed = 0;  // Reset total calories
            totalLabel.setText("Total Consumed: 0 calories");
            remainingLabel.setText("Remaining: " + DataUtils.loadCalorieGoal() + " calories");
        });

        switchToCalculatorButton.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Calculator"));
        homeButtonTracker.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Home"));

        // Add components to the panel
        trackerFormPanel.add(new JLabel("Calories Consumed:"));
        trackerFormPanel.add(calorieInputField);
        trackerFormPanel.add(addButton);
        trackerFormPanel.add(totalLabel);
        trackerFormPanel.add(remainingLabel);
        trackerFormPanel.add(switchToCalculatorButton);
        trackerFormPanel.add(homeButtonTracker);
        trackerFormPanel.add(refreshButtonTracker);

        add(trackerFormPanel, BorderLayout.CENTER);
    }
}
