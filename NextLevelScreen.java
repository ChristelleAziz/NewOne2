package org.folifac;

import javax.swing.*;
import java.awt.*;

public class NextLevelScreen extends JFrame {

    public NextLevelScreen() {
        setTitle("Next Level");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Congratulations! Next Level", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBackground(Color.BLACK);
        label.setOpaque(true);

        add(label, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NextLevelScreen().setVisible(true));
    }
}
