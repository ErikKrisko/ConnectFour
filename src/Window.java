import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    public Window(Game game){
        setSize(700, 700);
        setTitle("Connect4");

        add(game);

        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Stop program when window is closed
    }


}
