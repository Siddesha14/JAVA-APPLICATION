package calorietracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends JPanel {

    public HomePanel(CardLayout cardLayout, JFrame frame) {
        setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Welcome to the Calorie Tracker", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        // Buttons
        JButton calculateButton = new JButton("Go to Calorie Calculator");
        JButton trackButton = new JButton("Go to Calorie Tracker");
        JButton refreshButtonHome = new JButton("Refresh");
        refreshButtonHome.setPreferredSize(new Dimension(100, 30)); // Smaller button size

        // Action Listeners
        calculateButton.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Calculator"));
        trackButton.addActionListener(e -> cardLayout.show(frame.getContentPane(), "Tracker"));
        refreshButtonHome.addActionListener(e -> DataUtils.refreshData());

        // Layout for buttons
        JPanel homeButtonsPanel = new JPanel();
        homeButtonsPanel.setLayout(new BorderLayout());
        homeButtonsPanel.add(refreshButtonHome, BorderLayout.WEST);

        add(homeButtonsPanel, BorderLayout.WEST);
        add(calculateButton, BorderLayout.NORTH);
        add(trackButton, BorderLayout.SOUTH);
    }
}
