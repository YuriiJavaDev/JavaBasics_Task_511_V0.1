# Component Event Interaction: ActionListener Basics (JavaBasics_Task_511_V0.1)

## 📖 Description
In event-driven graphical desktop designs, interactive components rely on localized listener abstractions to process behavioral user feedback. This project introduces the core **`ActionListener`** mechanics using a single **`JButton`** element. By mounting an event listener to the operational button component, we register an observer that waits for a user push dispatch. Upon activation, the underlying event pipeline captures the trigger, instantly updates the text state of the button component to reflect the interaction, and routes a confirmation entry to the standard console system output log.

## 📋 Requirements Compliance
- **Action Event Registration**: Bound a single functional `ActionListener` mapping to a `JButton`.
- **Dynamic Content Modification**: Updated the button string token state to "Clicked" instantly upon user click.
- **Console Feedback Pipeline**: Provided standard system telemetry verification reporting "Button was clicked".

## 🚀 Architectural Stack
- Java 17+ (Java AWT Event Delegation Model, Java Swing)

## 🏗️ Implementation Details
- **ActionListenerBasics**: The standalone application workspace initializing layout trees and handling individual button dispatches.

## 📋 Expected result
*(Clicking the window button triggers simultaneous UI and stream mutations)*
- **UI State**: The button text morphs from "Click me" into "Clicked".
- **Console Stream**:
```text
Button was clicked
```

## 💻 Code Example

Project Structure:

    JavaBasics_Task_511/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 └── app/
    │                     └── ActionListenerBasics.java
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.Font;

public class ActionListenerBasics extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ActionListenerBasics app = new ActionListenerBasics();
            app.setVisible(true);
        });
    }

    public ActionListenerBasics() {
        super("ActionListener Basics");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JButton clickButton = new JButton("Click me");
        clickButton.setFont(new Font("Arial", Font.BOLD, 16));

        clickButton.addActionListener(e->{
            clickButton.setText("Clicked");
            System.out.println("Button was clicked");
        });

        add(clickButton, BorderLayout.CENTER);
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
