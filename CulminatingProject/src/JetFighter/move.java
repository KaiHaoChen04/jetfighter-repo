package JetFighter;
/*
Kenny Chen
May 12, 2023
Movement of the jets
 */
import javax.swing.*;
import java.util.*;
import java.util.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
public class move extends JPanel implements Runnable, KeyListener {
	// Declare and initialize variables 
	private int x, y; 
	private Timer timer;
	int score = 3;
	int score1 = 3;
	public boolean fire = true;
	private ArrayList<Rectangle> bullets = new ArrayList<>(); //declare arraylist with rectangle property element
	private Rectangle bullet;
	private double rotationAngle = 0.0;
	private double rotationAngle1 = 0.0;
	private double rotationIncrement = 5.4;
	private boolean right = false;
	private boolean left = false;
	private boolean left2 = false;
	private boolean right2 = false;
    Image jet1;
	Image jet2, Sprite;
    Thread run;
    Rectangle hitbox, hitbox1; 
    int positionX=100, positionY=100;
    int position1X=200, position1Y=200;
    int bulletX = 100, bulletY = 100;
    double bulletVelocity = 13.5;
    double VELOCITY = 16;
    int angle = 3;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // returns value of screensize
    //constructor
    public move(){
    	timer = new Timer(); // new timer object
        timer.schedule(new bullet(), 0, 1500); // time duration between bullet releases
    	setFocusable(true); // make gui focusable 
        addKeyListener(this); // key events
        Toolkit kit = Toolkit.getDefaultToolkit();
        jet1 = kit.getImage("blue-jet.png"); // blue jet image
        jet2 = kit.getImage("red-jet.png");//red jet image
        run = new Thread(this); // set run = new thread
        run.start(); //start thread
    }
    /**
	 * Draws the necessary components needed for game
	 * Pre: Graphics comp
 	 * Post: n/a
	 */
    public void paintComponent(Graphics comp) {
    	super.paintComponent(comp); // calls superclass
        Graphics2D comp2D = (Graphics2D) comp; //new graphics2d object
        //Draw Bullets
        for (Rectangle bullet : bullets) {
            comp.setColor(Color.BLACK);
            comp.fillRect(bullet.x, bullet.y, bullet.width, bullet.height);
        }
        //Draw health bar for blue based on scores
        comp2D.setColor(Color.BLUE);
        comp2D.setFont(new Font("Arial", Font.BOLD, 20));
        //blue jet health
        if(score==3) {
            comp2D.drawString("Health: ♥ ♥ ♥" , 10, 30);
        }
        if(score==2) {
            comp2D.drawString("Health: ♥ ♥" , 10, 30);

        }
        if(score==1) {
            comp2D.drawString("Health: ♥" , 10, 30);
        }
        //Draw health bar for red jet 
        comp2D.setColor(Color.RED);
        comp2D.setFont(new Font("Arial", Font.BOLD, 20));
        //red jet health
        if(score1==3) {
            comp2D.drawString("Health: ♥ ♥ ♥" , screenSize.width - 150, 30);
        }
        if(score1==2) {
            comp2D.drawString("Health: ♥ ♥" , screenSize.width - 150, 30);

        }
        if(score1==1) {
            comp2D.drawString("Health: ♥" , screenSize.width - 150, 30);
        }        
        comp2D.setFont(new Font("Arial", Font.BOLD, 50));
        //Win Condition
    	if(score==0) {
            comp2D.setColor(Color.RED);
            comp2D.drawString("Player 2 wins" , screenSize.width/2 - 150, screenSize.height/2 +50);
    	}
    	if(score1==0) {
            comp2D.setColor(Color.BLUE);
            comp2D.drawString("Player 1 wins" , screenSize.width/2 - 150, screenSize.height/2 +50);
    	}

        // Draw jet1
        AffineTransform rotation = AffineTransform.getTranslateInstance(positionX, positionY); //new affinetransform object
        rotation.rotate(Math.toRadians(rotationAngle), jet1.getWidth(this) / 2, jet1.getHeight(this) / 2); //rotate image based on the angle
        comp2D.setTransform(rotation);// new image 
        comp2D.drawImage(jet1, 0, 0, this); // make it visible on gui

        // Draw jet2
        AffineTransform rotation1 = AffineTransform.getTranslateInstance(position1X, position1Y);
        rotation1.rotate(Math.toRadians(rotationAngle1), jet2.getWidth(this) / 2, jet2.getHeight(this) / 2);
        comp2D.setTransform(rotation1);
        comp2D.drawImage(jet2, 0, 0, this);
    }
    /**
	 * Main execution
	 * Pre: n/a
 	 * Post: n/a
	 */
    public void run() {
        Thread thisThread = Thread.currentThread();  // new thread
        //initialize jet 1 and 2 positioning
        positionX = 2600;
        positionY = 1300; 
        position1X = 200;
        position1Y = 1300; 
        while (run == thisThread) { // while running
        	//Checks Win Condition
        	if(score==0) {
        		break;
        	}
        	if(score1==0) {
        		break;
        	}
        	// Wrap jet1 around the screen if it goes out of bounds
            if (positionY < 0) positionY = 1700;
            if (positionY > 1700) positionY = 2;
            if(positionX < 0) positionX = 2800;
            if (positionX > 2800) positionX = 2;
            // Wrap jet2 around the screen if it goes out of bounds
            if (position1Y < 0) position1Y = 1700;
            if (position1Y > 1700) position1Y = 2;
            if(position1X < 0) position1X = 2800;
            if (position1X > 2800) position1X = 2;
            
            // Update rotation angles based on key inputs
            if(right) {
            	rotationAngle += rotationIncrement;
            }
            if(left) {
            	rotationAngle-=rotationIncrement;
            }
            if(right2) {
            	rotationAngle1 += rotationIncrement;
            }
            if(left2) {
            	rotationAngle1 -= rotationIncrement;
            }
            if(right&&right2) {
            	rotationAngle+=rotationIncrement;
            	rotationAngle1+=rotationIncrement;
            }
            if(left&&left2) {
            	rotationAngle-=rotationIncrement;
            	rotationAngle1-=rotationIncrement;
            }
            if(left&&right2) {
            	rotationAngle-=rotationIncrement;
            	rotationAngle1+=rotationIncrement;
            }
            if(right&&left2){
				rotationAngle+=rotationIncrement;
            	rotationAngle1-=rotationIncrement;
			}
            //updates speed and direction for each bullet in the bullets arraylist
            for (Rectangle bullet : bullets) {
            		bullet.x+=bulletVelocity;
            		bullet.y-=bulletVelocity;
            }
            // Update jet1 position based on velocity and rotation angle
            positionX += VELOCITY * Math.sin(Math.toRadians(rotationAngle));
            positionY -= VELOCITY * Math.cos(Math.toRadians(rotationAngle)); 
            // Update jet2 position based on velocity and rotation angle
            position1X += VELOCITY * Math.sin(Math.toRadians(rotationAngle1));
            position1Y -= VELOCITY * Math.cos(Math.toRadians(rotationAngle1)); 
            
            // Create hitboxes for jet1 and jet2
            hitbox = new Rectangle((positionX+6)/2, (positionY+6)/2, jet2.getWidth(this)/2, jet2.getHeight(this)/2);
            hitbox1 = new Rectangle((position1X)/2, (position1Y)/2, jet2.getWidth(this)/2, jet2.getHeight(this)/2);
            
            // Create an ArrayList to store bullets that need to be removed
            ArrayList<Rectangle> bulletsToRemove = new ArrayList<>();

            try {
            // Iterate over the bullets and update their positions
            Iterator<Rectangle> iterator = bullets.iterator();
            while (iterator.hasNext()) {
                Rectangle bullet = iterator.next();
                // Update bullet position based on direction
                bullet.x += bulletVelocity;
                bullet.y -= bulletVelocity; 
                // Check collision with hitboxes
                if (bullet.intersects(hitbox)) {
                    score--;
                    bulletsToRemove.add(bullet); // Add the bullet to the removal list
                    break; // Exit the loop since a collision has been detected
                }
                if (bullet.intersects(hitbox1)) {
                    score1--;
                    bulletsToRemove.add(bullet); 
                    break; 
                }
            }
            bullets.removeAll(bulletsToRemove);// Remove the bullets that collided from the bullets list
            repaint(); //request that a component or the entire graphical window be repainted. 
            requestFocusInWindow(); // gains the keyboard focus within its window. Receives keyboard events such as key presses and key releases.
            }
            catch(ConcurrentModificationException e) {
            	continue;
            }
            try {
                Thread.sleep(45); // pauses the execution of the current thread for 45 milliseconds
            } 
            catch (InterruptedException e) {
                e.printStackTrace(); 
            }
        }
        repaint(); //request that a component or the entire graphical window be repainted. 
    }
    /**
	 * Detects which keys are pressed
	 * Pre: KeyEvent event
 	 * Post: n/a
	 */
    public void keyPressed (KeyEvent event){
    	if (event.getKeyCode() == KeyEvent.VK_LEFT) left = true; // if left arrow is pressed
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) right = true; // if right arrow is pressed
        if (event.getKeyCode() == KeyEvent.VK_A) left2 = true; // if A key is pressed
        if (event.getKeyCode() == KeyEvent.VK_D) right2 = true; // if D key is pressed
    }
    /**
	 * Determine which keys are typed
	 * Pre: KeyEvent event
 	 * Post: n/a
	 */
	public void keyTyped(KeyEvent event) {
	
	}
	/**
	 * Detects which keys are released
	 * Pre: KeyEvent event
 	 * Post: n/a
	 */
	public void keyReleased(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_LEFT) left = false; // if left arrow is released
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) right = false; // if right arrow is released
        if (event.getKeyCode() == KeyEvent.VK_A) left2 = false; // if A key is released
        if (event.getKeyCode() == KeyEvent.VK_D) right2 = false; // if D key is released
	}
	//Nested class
	private class bullet extends TimerTask {
		/**
		 * Main execution
		 * Pre: n/a
	 	 * Post: n/a
		 */
        public void run() { 
            if (fire) { //if boolean variable fire is true
            	int side = (int)(Math.random() * 3 + 0); // random int 
                switch (side) { // switch cases
                    case 0: // Top
                        x = (int)(Math.random() * screenSize.width + 0);
                        y = 0;
                        break;
                    case 1: // Left
                        x = 0;
                        y = (int)(Math.random() * screenSize.height + 0);
                        break;
                    case 2: // Bottom
                        x = (int)(Math.random() * screenSize.width + 0);
                        y = screenSize.height;
                        break;
                    case 3: // Right
                        x = screenSize.width;
                        y = (int)(Math.random() * screenSize.height + 0);
                        break;
                    default:
                        x = 0;
                        y = 0;
                        break;
                }
                // 4 different bullets
                bullet = new Rectangle(x, y, 12, 12);
                bullets.add(bullet);
                bullet = new Rectangle(x+100, y+100, 12, 12);
                bullets.add(bullet);
                bullet = new Rectangle(x+200, y, 12, 12);
                bullets.add(bullet);
                bullet = new Rectangle(x+300, y+100, 12, 12);
                bullets.add(bullet);
                fire = true;
            }
        }
    }
}