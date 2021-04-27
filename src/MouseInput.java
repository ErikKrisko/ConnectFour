import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {

    private Game game;

    public MouseInput(Game game){
        this.game = game;   //Initializes the game object
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int position = (e.getX()/(game.getWidth()/7));
        game.draw(position);
        //Gets the X coordinate of where the mouse was pressed and pushes it into the draw() method.
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
