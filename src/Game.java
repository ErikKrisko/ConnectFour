import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class Game extends JPanel {
    //Global Variables
    private Window window;
    private MouseInput mouseInput;
    private BufferedImage grid;
    private boolean playerOne = true;
    private char [][] values = new char[7][6];
    private int w, h;
    private int score1 = 0;
    private int score2 = 0;

    //Score labels
    private JLabel score1L = new JLabel();
    private JLabel score2L = new JLabel();




    public Game(){
        window = new Window(this);  //Initializes Window with game
        mouseInput = new MouseInput(this);  //Initializes Mouseinput with game

        //Add Mouse Listeners
        window.addMouseListener(mouseInput);
        window.addMouseMotionListener(mouseInput);

        //Sets up Score Labels
        this.add(score1L);
        this.add(score2L);
        score1L.setForeground(Color.red);
        score2L.setForeground(Color.blue);
        score1L.setFont(new Font("Verdana", Font.PLAIN, 50));
        score2L.setFont(new Font("Verdana", Font.PLAIN, 50));

        //Sets image grid as png image.
        try{
            grid = ImageIO.read(new File(".\\Connect4grid2.png"));
        }catch(Exception e){
            e.printStackTrace();
        }

        resetValues();  //Resets arrayList values all back to 0 (empty)
        displayScores();    //Changes the Score Label texts

        paintComponent(this.getGraphics()); //Paints the panel
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        super.repaint();

        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i < values.length; i++){
            for(int j = 0; j < values[i].length; j++){
                if(values[i][j] == '1'){
                    g2d.setColor(Color.red);
                    g2d.fill(new Ellipse2D.Double((double)i/7*getWidth(), (double)(j+1)/7*getHeight(), (double)getWidth()/7, (double)getHeight()/7));
                }
                else if(values[i][j] == '2'){
                    g2d.setColor(Color.blue);
                    g2d.fill(new Ellipse2D.Double((double)i/7*getWidth(), (double)(j+1)/7*getHeight(), (double)getWidth()/7, (double)getHeight()/7));
                }
            }
        }

        g2d.drawImage(grid, 0, 0, this.getWidth(), this.getHeight(), null);
    }

    //Receives X coordinate from MouseClicked.
    //Method that draws and check for droppable points
    public void draw(int pos){
        for(int y = 6 - 1; y >= 0; y--){    //Loops through the Y coordinates of the grid
            if(values[pos][y] == '0'){  //If the position is 0 or EMPTY. If it isnt empty, for loops goes position up and repeats the check
                if(playerOne){  //If playerone is true, it means player ones turn therefore it will set the values arraylists position as 1.
                    values[pos][y] = '1';   //Sets arraylists position as 1, aka player one has placed coin in.
                    paintComponent(this.getGraphics()); //Paintcomponent checks the arraylists value in certain position, if 1, sets it as red, if 2 sets it as blue.
                    check(pos,y);   //Check for win
                    playerOne = false;  //Changes turn
                }
                else{
                    values[pos][y] = '2';
                    paintComponent(this.getGraphics());
                    check(pos,y);
                    playerOne = true;
                }
                break;
            }
        }
    }

    //Checks for win, takes in position values.
    public void check(int x, int y){
        int turn = checkWin(x,y);   //Checks if the position the coi nwas placed is a winning one. if it is, it returns an int of 0 or 1 or 2. 0 if not. 1 if player one wins, 2 if player two wins

        if(turn == 1){
            score1++;
            System.out.println("Player One has won!");
            System.out.println("Player 1: " + score1 + " Player 2: " + score2);
            showMessageDialog(null, "Player One has won\nPlayer 1: " + score1 + "\nPlayer 2: " + score2 + "\nPlayer 2 starts next game");
            displayScores();
            resetValues();
            this.paintComponent(this.getGraphics());
        }
        else if(turn == 2){
            score2++;
            System.out.println("Player Two has won!");
            System.out.println("Player 1: " + score1 + " Player 2: " + score2);
            showMessageDialog(null, "Player Two has won\nPlayer 1: " + score1 + "\nPlayer 2: " + score2 + "\nPlayer 1 starts next game");
            displayScores();
            resetValues();
            this.paintComponent(this.getGraphics());
        }
    }

    public void resetValues(){
        for (char[] value : values) {
            Arrays.fill(value, '0');
        }
    }

    public void displayScores(){
        score1L.setText(String.valueOf(score1));
        score2L.setText(String.valueOf(score2));

    }

    //All win conditions, premis in check.
    public int checkWin(int x, int y){
        char c = values[x][y];
        int points = 0;

        for(int i = -3; i <= 3; i++){   //ACROSS VALUE CHECK
            if((x+i >= 0) && (x+i < 7)){
                if(values[x+i][y] == c){
                    points++;
                }
            }
            else{
                points = 0;
            }

            if(points >= 4){
                if(playerOne){
                    return 1;
                }
                else{
                    return 2;
                }
            }
        }
        points = 0;

        for(int i = -3; i <= 3; i++){
            if((y+i >= 0) && (y+i < 6)){   //DOWN UP VALUE CHECK
                if(values[x][y+i] == c){
                    points++;
                }
            }
            else{
                points = 0;
            }

            if(points >= 4){
                if(playerOne){
                    return 1;
                }
                else{
                    return 2;
                }
            }
        }
        points = 0;

        for(int i = -3; i <= 3; i++){
            if((x+i >= 0) && (x+i < 7) && (y+i >= 0) && (y+i < 6)){   //DIAGONAL DOWN RIGHT VALUE CHECK
                if(values[x+i][y+i] == c){
                    points++;
                }
            }
            else{
                points = 0;
            }

            if(points >= 4){
                if(playerOne){
                    return 1;
                }
                else{
                    return 2;
                }
            }
        }
        points = 0;

        for(int i = -3; i <= 3; i++){
            if((x+i >= 0) && (x+i < 7) && (y-i >= 0) && (y-i < 6)){   //DIAGONAL UP RIGHT VALUE CHECK
                if(values[x+i][y-i] == c){
                    points++;
                }
            }
            else{
                points = 0;
            }

            if(points >= 4){
                if(playerOne){
                    return 1;
                }
                else{
                    return 2;
                }
            }
        }
        points = 0;

        return 0;
    }

}
