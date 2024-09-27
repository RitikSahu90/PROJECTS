package src.game_logics;

import java.awt.*;
import javax.swing.*;

public class Gameover {
    JFrame frame;
    JLabel message;
    JButton restartButton;
    JButton exitButton;
    public void createbox(){
        frame = new JFrame("Game Over");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        message = new JLabel("Game Over! Press Restart to play again.");
        message.setFont(new Font("Arial", Font.BOLD, 16));
        contentPanel.add(message);

        //Restart Button
        restartButton =new JButton("Restart");
        restartButton.addActionListener(e->{
            new Flappegle();
        });
        contentPanel.add(restartButton);

        //Exit Button
        exitButton =new JButton("Exit");
        exitButton.addActionListener(e->System.exit(0));
        contentPanel.add(exitButton);


        frame.add(contentPanel,BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
