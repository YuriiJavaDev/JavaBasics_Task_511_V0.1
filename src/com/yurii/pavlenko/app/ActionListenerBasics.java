package com.yurii.pavlenko.app;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;

/**
 * Custom frame architecture isolating primary button action listener interactions.
 */
public class ActionListenerBasics extends JFrame {

    /**
     * Entry point.
     */
    public static void main(String[] args) {
        // Enforce thread-safe Swing execution context mapping on the EDT
        SwingUtilities.invokeLater(() -> {
            ActionListenerBasics app = new ActionListenerBasics();
            app.setVisible(true);
        });
    }

    /**
     * Constructs the window layout context and wires action feedback channels.
     */
    public ActionListenerBasics() {
        super("ActionListener Basics");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Instantiate the operational target button
        JButton clickButton = new JButton("Click me");
        clickButton.setFont(new Font("Arial", Font.BOLD, 16));

        clickButton.addActionListener(e->{
            clickButton.setText("Clicked");
            System.out.println("Button was clicked");
        });

        // Add the button to the frame content structure using standard centering layout
        add(clickButton, BorderLayout.CENTER);
    }
}
