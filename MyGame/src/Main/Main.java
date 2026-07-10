package Main;

import javax.swing.JFrame;

public class Main { // Class name should be capitalized by convention

    public static void main(String[] args) {
        // Create the main game window
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // Lock the window size for consistent tile display
        window.setTitle("Game of the dead");

        // Add game panel to the window
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack(); // Sizes the frame so that all its contents are at or above their preferred sizes

        window.setLocationRelativeTo(null); // Center the window
        window.setVisible(true); // Make the window visible
        
        gamePanel.setupGame();

        // Start the game loop
        gamePanel.startGameThread();
    }
}
