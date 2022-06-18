package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class Remastered extends JFrame implements ActionListener, TicTacToe {
    
    // Player and board info (look at the names)
    private int col_row = ParameterInput.playerCount+1;
    private int boardSize = (int)Math.pow((ParameterInput.playerCount+1),2);
    private int count = 0;
    private boolean[] players = new boolean[col_row];
    private Color[] playerColors = {Color.red, Color.blue, Color.yellow, Color.ORANGE, Color.PINK, Color.MAGENTA, Color.cyan, Color.DARK_GRAY, Color.BLACK , new Color(5,12,25)};

    // I had no idea how else to check so I implemented a map that checks if its "in a row", basically ups the counter if it was already seen
    private Map<String, Integer> pairVertical = new HashMap<String, Integer>();
    private Map<String, Integer> pairHorizontal = new HashMap<String, Integer>();
    private Map<String, Integer> pairDiagonalRight = new HashMap<String, Integer>();
    private Map<String, Integer> pairDiagonalLeft = new HashMap<String, Integer>();

    // Window Properties
    private JPanel title = new JPanel();
    private JPanel buttonPanel = new JPanel();
    private JButton[] buttons = new JButton[boardSize];
    private JLabel text = new JLabel();

    Remastered() {
        initBoard();
    }


    public void initBoard() {
        
        setTitle("Tic Tac Toe V2"); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,800);
        getContentPane().setBackground(new Color(210,105,30));
        setLayout(new BorderLayout());
        setVisible(true);


        text.setBackground(new Color(25,25,25));
        text.setForeground(new Color(255,255,255));
        text.setFont(new Font("Comic Sans MS", Font.BOLD,75));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("Tic Tac Toe");
        text.setOpaque(true);

        title.setLayout(new BorderLayout());
        title.setBounds(0,0,800,100);

        // Fill hashmap, each character starts off at 0 since it hasnt been seen yet
        for (int i = 0; i < ParameterInput.playerCount; i++) {
            // System.out.println(iconSelector.playerIcons);
            pairHorizontal.put((String)iconSelector.playerIcons.get(i), 0);
            pairVertical.put((String)iconSelector.playerIcons.get(i), 0);
            pairDiagonalLeft.put((String)iconSelector.playerIcons.get(i), 0);
            pairDiagonalRight.put((String)iconSelector.playerIcons.get(i), 0);
        }

        
        buttonPanel.setLayout(new GridLayout(col_row,col_row));
        // set turns true
        for (int g = 0; g < col_row; g++) {
            players[g] = true;
        }

        // init buttons
        for(int i = 0; i < boardSize; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title.add(text);
        add(title,BorderLayout.NORTH);
        add(buttonPanel);
        setLocationRelativeTo(null);
    }

    public void gameLogic() {
        // Check conditions
        checkHoriz(0);
        checkVert(0);
        checkDiag(0);
        // Resets map to 0 aka resetting counts back to 0
        for (int i = 0; i < ParameterInput.playerCount; i++) {
            pairHorizontal.put((String)iconSelector.playerIcons.get(i), 0);
            pairVertical.put((String)iconSelector.playerIcons.get(i), 0);
            pairDiagonalLeft.put((String)iconSelector.playerIcons.get(i), 0);
            pairDiagonalRight.put((String)iconSelector.playerIcons.get(i), 0);
        }
        // Checks if all spaces are filled with no winner then draw
        for (int i = 0; i < boardSize; i++) {
            if(buttons[i].getText() != "") {
                count++;
            }
            if(count == boardSize) {
                Draw();
            }
        }
        count = 0;

    }

    public void Draw() {
    
        for (int j = 0; j < boardSize; j++) {
            buttons[j].setBackground(Color.GRAY);
            buttons[j].setEnabled(false);
        }
        text.setText("DRAW");
        iconSelector.playerIcons.clear();
        new Prompt();
        
        //dispose();
        
    }
    public void Won(String winner) {
        for (int i = 0; i < boardSize; i++) {
            if (winner == buttons[i].getText()) {
                buttons[i].setBackground(Color.GREEN);
            }
            buttons[i].setEnabled(false);
        }
        text.setText(winner + " Player wins");
        iconSelector.playerIcons.clear();
        new Prompt();
        
        //dispose();
    }



    // 
    // LOGIC FUNC START
    //


    // Need to find bugs
    // I know theres one for diagonals, not sure for the others

    public void checkHoriz(int start) {
        // loop starting from button 1 until the end of the row
        for (int k = 1; k<col_row+1;k++) {
            // loop the next row and the row after that
            for(int i = (k-1)*col_row, j = i+1;  j < k*col_row && i < k*col_row; i++, j++) {
                //System.out.println("Current b1: " + buttons[i].getText() + " Current b2: " + buttons[j].getText() + " char: " + pIcon[z]);

                // if equal and not empty
                if(buttons[i].getText() == buttons[j].getText() && (buttons[i].getText() != "" && buttons[j].getText() != "")) {
                    // up the counter
                    pairHorizontal.put(buttons[j].getText(), pairHorizontal.get(buttons[j].getText())+1);
                    // check if win condition is met
                    for (Map.Entry <String, Integer> entry: pairHorizontal.entrySet()) {
                        if (entry.getValue() == ParameterInput.winCondition-1) {
                            System.out.println("H");
                            Won(entry.getKey());
                            return;
                            }
                        }
                    //System.out.println("\n Matching " + seq);
                }

                }
            // reset count
            for (int i = 0; i < ParameterInput.playerCount; i++) {
                pairHorizontal.put((String)iconSelector.playerIcons.get(i), 0);
            }
        }
    }


    public void checkVert(int start) {
        // System.out.println("V " + pairVertical);

        // loop from starting position to the next vertical position; this is done by taking current + row size
        for (int i = start, j = i+col_row; i<boardSize-1 && j < boardSize; i++, j++) {
            // System.out.println("Current b1: " + TTTV2.buttons[i].getText() + " Current b2: " + TTTV2.buttons[j].getText());

            // if equal and not empty
            if(buttons[i].getText() == buttons[j].getText() && (buttons[i].getText() != "" && buttons[j].getText() != "")) {
                // up the counter
                pairVertical.put(buttons[j].getText(), pairVertical.get(buttons[j].getText())+1);
                // TTTV2.buttons[i].setText(TTTV2.buttons[j].getText()+" ");
                // check if win condition is met
                for (Map.Entry <String, Integer> entry: pairVertical.entrySet()) {
                    if (entry.getValue() == ParameterInput.winCondition-1) {
                        System.out.println("V");
                        Won(entry.getKey());
                        return;
                    }
                }
                // recursively call if win con not met
                checkVert(j);
                // System.out.println("\n Matching " + seq);
            }

        }
        // reset count
        for (int i = 0; i < ParameterInput.playerCount; i++) {
            pairVertical.put((String)iconSelector.playerIcons.get(i), 0);
        }
    }


    // BUGGY BUGGY BUGGY I DONT KNOW WHATS WRONG
    public void checkDiag(int start) {
        // System.out.println("DL " + pairDiagonalLeft);
        // System.out.println("DR " + pairDiagonalRight);
        // Loop from start to the right of the next row

        for (int i = start, j = i+col_row+1; i<boardSize-1 && j < boardSize; i++, j++) {
            int k = i+col_row-1;
            // check if left diag exists
            if (k >= col_row) {
                // check if equal and not empty
                if((buttons[i].getText() == buttons[k].getText() && (buttons[i].getText() != "" && buttons[k].getText() != ""))) {
                    // up counter
                    pairDiagonalLeft.put(buttons[k].getText(), pairDiagonalLeft.get(buttons[k].getText())+1);

                    for (Map.Entry <String, Integer> entry: pairDiagonalLeft.entrySet()) {
                        if (entry.getValue() == ParameterInput.winCondition-1) {
                            System.out.println("DL");
                            Won(entry.getKey());
                            return;
                        }
                    }
                    // recursively call if win con not met
                    checkDiag(k);
                    //System.out.println("\n Matching " + seq);
                }

            }
            // I think the way to fix the bug is to check if right goes out of bounds...
            // chekcs if equal and not empty
            if((buttons[i].getText() == buttons[j].getText() && (buttons[i].getText() != "" && buttons[j].getText() != ""))) {
                // up counter
                pairDiagonalRight.put(buttons[j].getText(), pairDiagonalRight.get(buttons[j].getText())+1);
                // TTTV2.buttons[i].setText(TTTV2.buttons[j].getText()+" ");
                for (Map.Entry <String, Integer> entry: pairDiagonalRight.entrySet()) {
                    if (entry.getValue() == ParameterInput.winCondition-1) {
                        System.out.println("DR");
                        Won(entry.getKey());
                        return;
                    }
                }
                checkDiag(j);
                // System.out.println("\n Matching " + seq);
            }


        }
        // reset counter
        for (int i = 0; i < ParameterInput.playerCount; i++) {
            pairDiagonalRight.put((String)iconSelector.playerIcons.get(i), 0);
            pairDiagonalLeft.put((String)iconSelector.playerIcons.get(i), 0);
        }
    }



    //
    //  LOGIC FUNC END
    //



    // What happens when clicking button
    public void actionPerformed(ActionEvent e) {
        for(int i=0;i<boardSize;i++) {
            if(e.getSource()==buttons[i]) {
                // cycle through buttons
                for (int k = 0; k < (col_row-1); k++ ) { 
                    // checks whos turn it is
                    if (players[k]) {
                        
                        if(buttons[i].getText()=="") {
                            buttons[i].setForeground(playerColors[k]);
                            buttons[i].setText((String)iconSelector.playerIcons.get(k));
                            players[k]=false;
                            int j = (k + 1) % (col_row-1); // index for next active player
                            players[j] = true; // mark next active player
                            text.setForeground(playerColors[j]);
                            text.setText(iconSelector.playerIcons.get(j) + " turn");
                            gameLogic();
                            break; 
                        }
                    }
            
                }

            }			
        }
    }




}
