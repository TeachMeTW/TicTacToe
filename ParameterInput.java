package TicTacToe;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ParameterInput extends JFrame implements ActionListener {
    
    
    private JLabel playerCountLabel;
    private JLabel winConditionLabel;
    private JTextField playerCountInput;
    private JTextField winConditionInput;
    private JButton enterInfo;

    // make sure to make a player character chooser

    static int playerCount;
    static int winCondition;

    ParameterInput() {
        
        setLayout(new FlowLayout());

        // To get user input of players
        playerCountLabel = new JLabel("Enter number of players (2-10): ");
        add(playerCountLabel);

        playerCountInput = new JTextField(10);
        add(playerCountInput);

        // to get user input for win condition
        winConditionLabel = new JLabel("Enter number in a row to win (at least 3 but no more than players + 1): ");
        add(winConditionLabel);

        winConditionInput = new JTextField(10);
        add(winConditionInput);

        enterInfo = new JButton("Enter");
        add(enterInfo);

        enterInfo.addActionListener(this);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);

    }


    public void actionPerformed(ActionEvent e) {
        // input validation: checks if player count is at least 2 but less than 10
        // checks that win condition must be at least 3 up to 1 more than players
        try {
            String players = playerCountInput.getText();
            String win = winConditionInput.getText();

            winCondition = Integer.parseInt(win);
            playerCount = Integer.parseInt(players);

            if (playerCount < 2 || playerCount > 10 || winCondition < 3 || winCondition > playerCount+1) {
                throw new NumberFormatException();
            }


            else {
                new iconSelector();
                dispose();
                
            }
            // System.out.println(playercount);
            
        }

        // catch the error of bad win con or bad player count
        catch (NumberFormatException ex) {
            JFrame n = new JFrame();
            ImageIcon icon = new ImageIcon("TicTacToe/jpeg.png");
            JOptionPane.showMessageDialog(n, "Enter a valid player count or win condition!", "Invalid Player Count", JOptionPane.INFORMATION_MESSAGE, icon);
            
        }
    }

}
