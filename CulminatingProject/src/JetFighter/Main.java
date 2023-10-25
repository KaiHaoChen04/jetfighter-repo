package JetFighter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/*
Kenny Chen & Mithulan Muraleetharan
April 28th, 2023
Culminating Final Project - JET FIGHTER
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {

	//Initialize JLabels, JButtons,
    JLabel welcome = new JLabel("Welcome to Jet Fighter!!!");
    JLabel instruction = new JLabel("<html><center><h2>Instructions</h2></center>"
            + "<p>Both players try to dodge the bullets.</p>"
            + "<p>If a jet gets hit by a bullet, they lose one health bar.</p>"
            + "<p>Whichever jet loses all three health bars, loses the game.</p>");
    JLabel aKey = new JLabel("A: Left ");
    JLabel dKey = new JLabel("D: Right ");
    JLabel leftKey = new JLabel("←: Left ");
    JLabel rightKey = new JLabel("→: Right ");
    JLabel createdBy = new JLabel("<html><center><h2>Created by:" + "<p> Kenny  &   Mithulan");

    JButton b1 = new JButton("PLAY");
    JButton b2 = new JButton("CONTROLS");
    JButton b3 = new JButton("BACK");

    //Import necessary images
    ImageIcon wasd = new ImageIcon("WASD.jpeg");
    Image img1 = wasd.getImage();
    Image newimg1 = img1.getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH);
    ImageIcon wasdpic = new ImageIcon(newimg1);
    JLabel wasdlbl = new JLabel(wasdpic);

    ImageIcon arrow = new ImageIcon("arrow.jpeg");
    Image img2 = arrow.getImage();
    Image newimg2 = img2.getScaledInstance(150, 120, java.awt.Image.SCALE_SMOOTH);
    ImageIcon arrowpic = new ImageIcon(newimg2);
    JLabel arrowlbl = new JLabel(arrowpic);

    background space = new background();

    move boing = new move();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    
    public static void main(String[] args) {
        Main gui = new Main(); // Create gui
    }
    //constructor
    public Main() {
    	//Label each components onto the gui
        super("Jet Fighter");
        setSize((int) screenSize.getWidth(), (int) screenSize.getHeight());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        space.setLayout(null); //Using coordinates layout
        space.setBounds(0, 0, getWidth(), getHeight());
        //Checks for clicks
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        //Design JLabel and Jbutton
        welcome.setForeground(Color.RED);
        welcome.setFont(new Font("Snell Roundhand", Font.PLAIN, 32));
        welcome.setBounds(560, -50, 340, 340);
        b1.setBounds(620, 250, 150, 50);
        Font font = new Font("Arial", Font.PLAIN, 24);
        b1.setFont(font);
        b1.setForeground(Color.WHITE);
        b1.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2)); // Set border
        b2.setBounds(620, 450, 150, 50);
        b2.setFont(font);
        b2.setForeground(Color.WHITE);
        b2.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2)); // Set border
        b3.setBounds(0, 0, 50, 50);
        wasdlbl.setBounds(100, 100, 120, 110);
        arrowlbl.setBounds(100, 350, 120, 110);
        aKey.setBounds(350, 100, 120, 110);
        aKey.setForeground(Color.WHITE);
        dKey.setBounds(500, 100, 120, 110);
        instruction.setForeground(Color.RED);
        instruction.setFont(new Font("MV Boli", Font.BOLD, 22));
        instruction.setBounds(700, 100, 500, 500);
        dKey.setForeground(Color.WHITE);
        leftKey.setBounds(350, 400, 120, 110);
        leftKey.setForeground(Color.WHITE);
        rightKey.setBounds(500, 400, 120, 110);
        rightKey.setForeground(Color.WHITE);
        boing.setBounds(0, 0, 2800, 1600);
        createdBy.setForeground(Color.GREEN);
        createdBy.setFont(new Font("Arial", Font.BOLD, 24));
        createdBy.setBounds(590, 480, 400, 150); 
        //Add to the background
        add(space);
        space.add(welcome);
        space.add(instruction);
        space.add(b1);
        space.add(b2);
        space.add(wasdlbl);
        space.add(arrowlbl);
        space.add(b3);
        space.add(aKey);
        space.add(dKey);
        space.add(boing);
        space.add(leftKey);
        space.add(rightKey);
        space.add(createdBy); 
        boing.setVisible(false);
        instruction.setVisible(false);
        b3.setVisible(false);
        wasdlbl.setVisible(false);
        arrowlbl.setVisible(false);
        aKey.setVisible(false);
        dKey.setVisible(false);
        leftKey.setVisible(false);
        rightKey.setVisible(false);
        setVisible(true);
        //Call playMusic method to play background music
        String filePath = "BGM.wav";
        playMusic(filePath);
    }
    /**
	 * checks for clicks
	 * Pre: ActionEvent event
 	 * Post: n/a
	 */
    public void actionPerformed(ActionEvent event) {
        String eventName = event.getActionCommand();
        if (eventName.equals("PLAY")) {
            b1.setVisible(false);
            b2.setVisible(false);
            welcome.setVisible(false);
            boing.setVisible(true);
        }
        if (eventName.equals("CONTROLS")) {
        	//Control screen
            b1.setVisible(false);
            b2.setVisible(false);
            welcome.setVisible(false);
            wasdlbl.setVisible(true);
            arrowlbl.setVisible(true);
            b3.setVisible(true);
            aKey.setVisible(true);
            dKey.setVisible(true);
            leftKey.setVisible(true);
            rightKey.setVisible(true);
            instruction.setVisible(true);
            createdBy.setVisible(false);
        }
        if (eventName.equals("BACK")) {
        	//Main menu screen
            b1.setVisible(true);
            b2.setVisible(true);
            welcome.setVisible(true);
            wasdlbl.setVisible(false);
            b3.setVisible(false);
            arrowlbl.setVisible(false);
            aKey.setVisible(false);
            dKey.setVisible(false);
            leftKey.setVisible(false);
            rightKey.setVisible(false);
            instruction.setVisible(false);
            createdBy.setVisible(true);
        }
    }

    /**
	 * Plays background music
	 * Pre: String filePath
 	 * Post: n/a
	 */
    public void playMusic(String filePath) {
        try {
            File musicPath = new File(filePath);
            AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

