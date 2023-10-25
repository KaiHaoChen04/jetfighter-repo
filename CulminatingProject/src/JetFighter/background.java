package JetFighter;

import java.awt.*;
import javax.swing.*;
public class background extends JPanel  {
    Image shop;
    //constructor
    public background() {
        super();
        Toolkit kit = Toolkit.getDefaultToolkit();
        shop = kit.getImage("space.png");
    }
    /**
	 * Draws the background
	 * Pre: Graphics comp
 	 * Post: n/a
	 */
    public void paintComponent(Graphics comp) {
        Graphics2D comp2D = (Graphics2D) comp;
        comp2D.drawImage(shop, 0, 0, getWidth(), getHeight(),this);
    }
}