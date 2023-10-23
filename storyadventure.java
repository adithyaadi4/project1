import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class TextAdventureGameGUI {
    private JFrame frame;
    private JPanel panel;
    private JLabel storyLabel;
    private JButton choice1Button;
    private JButton choice2Button;

    private int gameState = 0;

    public TextAdventureGameGUI() {
        frame = new JFrame("Text Adventure Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        storyLabel = new JLabel("WELCOME TO FOREST ADVENTURE GAME");
        choice1Button = new JButton("EXPLORE THE FOREST");
        choice2Button = new JButton("LEAVE");

        choice1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameState == 0) {
                    storyLabel.setText("You encounter a mysterious creature. What do you do?");
                    choice1Button.setText("Try to talk to the creature");
                    choice2Button.setText("Run away");
                    gameState = 1;
                } else if (gameState == 1) {
                    storyLabel.setText("The creature speaks in a language you don't understand.");
                    choice1Button.setText("Continue trying to communicate");
                    choice2Button.setText("Run away");
                    gameState = 2;
                } else if (gameState == 2) {
                    storyLabel.setText("The creature smiles and offers you a magical amulet.");
                    choice1Button.setText("Accept the amulet");
                    choice2Button.setText("Decline and leave");
                    gameState = 3;
                } else if (gameState == 3) {
                    storyLabel.setText("The amulet fills you with power. You are now a magical being. You've won the game!");
                    choice1Button.setText("");
                    choice2Button.setText("");
					gameState=4;
                }
            }
        });

        choice2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameState == 0) {
                    storyLabel.setText("You wait for a while but nothing happens. Eventually, you decide to explore the forest.");
                    choice1Button.setText("Continue exploring");
                    choice2Button.setText("");
                    gameState = 4;
                } else if (gameState == 1 || gameState == 2) {
                    storyLabel.setText("You run away from the creature and get lost in the forest.");
                    choice1Button.setText("");
                    choice2Button.setText("");
                    gameState = 5;
                }
				else if (gameState == 3) {
                    storyLabel.setText("You run away from the creature and get lost in the forest.");
                    choice1Button.setText("");
                    choice2Button.setText("");
                    gameState = 5;
                }
				
            }
        });

        panel.add(storyLabel);
        panel.add(choice1Button);
        panel.add(choice2Button);
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextAdventureGameGUI();
            }
        });
    }
}
