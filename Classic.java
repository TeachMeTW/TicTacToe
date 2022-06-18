package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Classic extends JFrame implements ActionListener, TicTacToe {

    // Window properties
    private JFrame frame = new JFrame();
    private JPanel title = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JLabel txt = new JLabel();
    private JButton[] buttons = new JButton[9];
    
    // Players
    private boolean pTurn;
    private int count = 0;

    Classic() {
        initBoard();
    }

    public void initBoard() {
        // Sets window properties
        setTitle("Tic Tac Toe V1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        getContentPane().setBackground(new Color(50,50,50));
        setLayout(new BorderLayout());
        setVisible(true);

        // sets the text at the top (where the players would be displayed)
        txt.setBackground(new Color(25,25,25));
        txt.setForeground(new Color(255,255,255));
        txt.setFont(new Font("Comic Sans MS", Font.BOLD,75));
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setText("Tic Tac Toe");
        txt.setOpaque(true);

        title.setLayout(new BorderLayout());
        title.setBounds(0,0,800,100);

        // create the play space 3 x 3
        buttonPanel.setLayout(new GridLayout(3,3));


        // Cycle through the play space
        for(int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title.add(txt);
        add(title,BorderLayout.NORTH);
        add(buttonPanel);
        setLocationRelativeTo(null);

    }

    public void gameLogic() {
       // Checks for P1 win conditions [manually coded since just 3 places]


        // Horizontal section

        if((buttons[0].getText()==iconSelector.playerIcons.get(0)) && (buttons[1].getText()==iconSelector.playerIcons.get(0)) && (buttons[2].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(0,1,2);
        }
        if((buttons[3].getText()==iconSelector.playerIcons.get(0)) && (buttons[4].getText()==iconSelector.playerIcons.get(0)) && (buttons[5].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(3,4,5);
        }
        if((buttons[6].getText()==iconSelector.playerIcons.get(0)) && (buttons[7].getText()==iconSelector.playerIcons.get(0)) && (buttons[8].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(6,7,8);
        }

        // Vertical Section

        if((buttons[1].getText()==iconSelector.playerIcons.get(0)) && (buttons[4].getText()==iconSelector.playerIcons.get(0)) && (buttons[7].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(1,4,7);
        }
        if((buttons[0].getText()==iconSelector.playerIcons.get(0)) && (buttons[3].getText()==iconSelector.playerIcons.get(0)) && (buttons[6].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(0,3,6);
        }
        if((buttons[2].getText()==iconSelector.playerIcons.get(0)) && (buttons[5].getText()==iconSelector.playerIcons.get(0)) && (buttons[8].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(2,5,8);
        }

        // Diagonal section

        if((buttons[0].getText()==iconSelector.playerIcons.get(0)) && (buttons[4].getText()==iconSelector.playerIcons.get(0)) && (buttons[8].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(0,4,8);
        }
        if((buttons[2].getText()==iconSelector.playerIcons.get(0)) && (buttons[4].getText()==iconSelector.playerIcons.get(0)) && (buttons[6].getText()==iconSelector.playerIcons.get(0))) {
            P1Won(2,4,6);
        }



        // Checks for P2 win conditions [manually coded since just 3 places]

        // Horizontal Section

        if((buttons[0].getText()==iconSelector.playerIcons.get(1)) && (buttons[1].getText()==iconSelector.playerIcons.get(1)) && (buttons[2].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(0,1,2);
        }
        if((buttons[3].getText()==iconSelector.playerIcons.get(1)) && (buttons[4].getText()==iconSelector.playerIcons.get(1)) && (buttons[5].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(3,4,5);
        }
        if((buttons[6].getText()==iconSelector.playerIcons.get(1)) && (buttons[7].getText()==iconSelector.playerIcons.get(1)) && (buttons[8].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(6,7,8);
        }

        // Vertical Section

        if((buttons[1].getText()==iconSelector.playerIcons.get(1)) && (buttons[4].getText()==iconSelector.playerIcons.get(1)) && (buttons[7].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(1,4,7);
        }
        if((buttons[0].getText()==iconSelector.playerIcons.get(1)) && (buttons[3].getText()==iconSelector.playerIcons.get(1)) && (buttons[6].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(0,3,6);
        }
        if((buttons[2].getText()==iconSelector.playerIcons.get(1)) && (buttons[5].getText()==iconSelector.playerIcons.get(1)) && (buttons[8].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(2,5,8);
        }

        // Diagonal Section

        if((buttons[0].getText()==iconSelector.playerIcons.get(1)) && (buttons[4].getText()==iconSelector.playerIcons.get(1)) && (buttons[8].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(0,4,8);
        }
        if((buttons[2].getText()==iconSelector.playerIcons.get(1)) && (buttons[4].getText()==iconSelector.playerIcons.get(1)) && (buttons[6].getText()==iconSelector.playerIcons.get(1))) {
            P2Won(2,4,6);
        }


        // Checks if its a draw by checking if everything is filled without a winner
        for (int i = 0; i < 9; i++) {
            if(buttons[i].getText() != "") {
                count++;
            }
            if(count == 9) {
                noWindraw();
            }
        }
        count = 0;

    }

    public void noWindraw() {
        for (int j = 0; j < 9; j++) {
            buttons[j].setBackground(Color.GRAY);
            buttons[j].setEnabled(false);
        }
        txt.setText("DRAW");
        new Prompt();
        
        
    }

    // Displays where X won and locks the spaces
    // promps user for replay or not
    public void P1Won(int n1, int n2, int n3) {
        buttons[n1].setBackground(Color.GREEN);
        buttons[n2].setBackground(Color.GREEN);
        buttons[n3].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        txt.setText(iconSelector.playerIcons.get(0) + " Player wins");
        iconSelector.playerIcons.clear();
        new Prompt();
    }

    // Displays where y won and locks the spaces
    // promps user for replay or not
    public void P2Won(int n1, int n2, int n3) {
        buttons[n1].setBackground(Color.GREEN);
        buttons[n2].setBackground(Color.GREEN);
        buttons[n3].setBackground(Color.GREEN);

        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
        txt.setText(iconSelector.playerIcons.get(1) + " Player wins");
        iconSelector.playerIcons.clear();
        new Prompt();
    }

    // What happens when button is pressed
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<9;i++) {
            // checks whos turn and displays it at the top, takes the user input sends it to logic class
            if(e.getSource()==buttons[i]) {
                if(pTurn) {
                    if(buttons[i].getText()=="") {
                        // Why are you not changing colors?
                        buttons[i].setForeground(Color.GREEN);
                        buttons[i].setText(iconSelector.playerIcons.get(0));
                        pTurn=false;
                        txt.setForeground(Color.BLUE);
                        txt.setText(iconSelector.playerIcons.get(1) + " turn");
                        gameLogic();
                    }
                }
                else {
                    if(buttons[i].getText()=="") {
                        // Why are you not changing colors
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText(iconSelector.playerIcons.get(1));
                        pTurn=true;
                        txt.setForeground(Color.GREEN);
                        txt.setText(iconSelector.playerIcons.get(0) + " turn");
                        gameLogic();
                    }
                }
            }			
        }
    }
}
