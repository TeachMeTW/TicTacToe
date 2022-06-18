package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame implements ActionListener  {

    private JLabel backgroundLabel;
    private JPanel background;
    private JButton playButton;
    private JButton instructionButton;

    Menu() {
        initBackground();
        initButtons();
    }

    public void initBackground() {

        // set background for window

        ImageIcon icon = new ImageIcon("TicTacToe/toe.png");
        backgroundLabel = new JLabel();
        backgroundLabel.setIcon(icon);
        background = new JPanel();
        background.setOpaque(false);
        background.setBackground(Color.green);
        background.setBounds(-10,0,1280,800);
        background.add(backgroundLabel);
        add(background);

        // set window properties
        setTitle("TicTacToe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1280,750);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
        
    }

    public void initButtons() {

        // create instruction and play button
        playButton = new JButton("Play");
        instructionButton = new JButton("Info");

        // set play button position
        playButton.setBounds(590,500,100,50);

        // set what happens when button is pressed
        playButton.addActionListener(this);

        // set instruction position
        instructionButton.setBounds(590,560,100,50);
        
        // set what happens when button is pressed
        instructionButton.addActionListener(this);
        
        add(playButton);
        add(instructionButton);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == instructionButton) {
            // open the info panel
            new Info();
        }
        if (e.getSource() == playButton) {
            // close current and open the player information window
            dispose();
            new ParameterInput();
        }

    }

}
