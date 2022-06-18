package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class iconSelector extends JFrame implements ActionListener  {

    static ArrayList<String> playerIcons = new ArrayList<String>();

    private JLabel[] iconSelect = new JLabel[ParameterInput.playerCount];
    private JTextField[] iconEnter = new JTextField[ParameterInput.playerCount];
    private JButton enterInfo;

    iconSelector() {
        setLayout(new FlowLayout());

        // Creates user inputs for their character
        for (int i = 0; i < ParameterInput.playerCount; i++) {
            iconSelect[i] = new JLabel("Player " + i + " : ");
            add(iconSelect[i]);
            iconEnter[i] = new JTextField(5);
            add(iconEnter[i]);
        }

        enterInfo = new JButton("Enter");
        enterInfo.addActionListener(this);

        add(enterInfo);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        // input validation: checks if player count is at least 2 but less than 10
        // checks that win condition must be at least 3 up to 1 more than players
        try {
            for (int i = 0; i < ParameterInput.playerCount; i++) {
                // if empty
                if (iconEnter[i].getText() == "") {
                    throw new NumberFormatException();
                }
                else {
                    playerIcons.add(iconEnter[i].getText());
                }
                
            }
            // System.out.println(playerIcons);
            if(ParameterInput.playerCount == 2) {
                new Classic();
                dispose();
            }
            else {
                new Remastered();
                dispose();
            }
            

            // System.out.println(playercount);
            
        }

        // catch the error of bad win con or bad player count
        catch (NumberFormatException ex) {
            JFrame n = new JFrame();
            ImageIcon icon = new ImageIcon("TicTacToe/jpeg.png");
            JOptionPane.showMessageDialog(n, "Enter valid icons!", "Invalid Player", JOptionPane.INFORMATION_MESSAGE, icon);
            
        }
    }
}
