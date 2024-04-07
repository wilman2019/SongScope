package Java.Screens;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.geom.AffineTransform;

public class SignupScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel picPanel;
	private final JPanel inputPanel;
    private JLabel lblEmail;
	private JTextField inputEmail;
    private JLabel lblUsername;
	private JTextField inputUsername;
    private JLabel lblPassword;
	private JPasswordField inputPassword;
    private JButton signupButton;
    private JButton backButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignupScreen frame = new SignupScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SignupScreen() {
		
        // Create Frame
		picPanel = new JPanel(); 
        picPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setBounds(100, 100, 1354, 768); 
        picPanel.setLayout(null);
		
        // Add background image
        ImageIcon imageIcon = new ImageIcon("src/img/SongScope2.jpg");
        Image image = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picPanel.add(picLabel);
        setContentPane(picPanel); 



        // Create a custom inputPanel with a rounded border
        inputPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                int radius = 20;
                Graphics2D inputPanelGraphics = (Graphics2D) g.create();
                inputPanelGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                inputPanelGraphics.setColor(new Color(213, 137, 148, 180)); 
                inputPanelGraphics.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
                inputPanelGraphics.dispose();
            }
            
        };

        // Set Dimensions
        int inputPanelWidth = 423;
        int inputPanelHeight = 515;
        int x = (picPanel.getWidth() - inputPanelWidth) / 2;
        int y = (picPanel.getHeight() - inputPanelHeight) / 2;
        inputPanel.setBounds(x, y, inputPanelWidth, inputPanelHeight);
        
        // Additional settings
        inputPanel.setOpaque(true);
        inputPanel.setLayout(null);
        picPanel.add(inputPanel);
        picPanel.setComponentZOrder(inputPanel, 0); 



        // Create a JLabel for the title
        JLabel lblTitle = new JLabel("Sign Up");
        lblTitle.setFont(new Font("Monospaced", Font.BOLD, 36));
        lblTitle.setBounds((inputPanel.getWidth() - lblTitle.getPreferredSize().width) / 2, 10, lblTitle.getPreferredSize().width, lblTitle.getPreferredSize().height);
        inputPanel.add(lblTitle);
        


        // Calculate the y-coordinate for the middle of the inputPanel
        int middleY = inputPanel.getHeight() / 2;



        // Create a JLabel for the email
        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, middleY-40, 100, 25);
        inputPanel.add(lblEmail);
        
        // Create a JTextField for the email input
        inputEmail = new JTextField();
        inputEmail.setBounds(120, middleY-40, 200, 25);
        inputPanel.add(inputEmail);
        


        // Create a JLabel for the username
        lblUsername = new JLabel("Username:");
        lblUsername.setBounds(10, middleY - 80, 100, 25);
        inputPanel.add(lblUsername);
        
        // Create a JTextField for the username input
        inputUsername = new JTextField();
        inputUsername.setBounds(120, middleY - 80, 200, 25);
        inputPanel.add(inputUsername);
        
        

        // Create a JLabel for the password
        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, middleY , 100, 25);
        inputPanel.add(lblPassword);
        
        // Create a JPasswordField for the password input
        inputPassword = new JPasswordField();
        inputPassword.setBounds(120, middleY, 200, 25);
        inputPassword.setEchoChar('*');
        inputPanel.add(inputPassword);
        


        // Create a signup button
        signupButton = new JButton("Signup");
        signupButton.setBounds ((inputPanel.getWidth() - signupButton.getPreferredSize().width) / 2 - 12, middleY + 175, signupButton.getPreferredSize().width + 25, signupButton.getPreferredSize().height);
        inputPanel.add(signupButton);
        inputPanel.setComponentZOrder(signupButton, 0);

        // Actions for Signup button
        signupButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String username = inputUsername.getText();
	            String password = new String(inputPassword.getPassword());
	            String email = inputEmail.getText();
	            HandleSignup signup = null;
	            try {
	                signup = new HandleSignup(email, username, password);
	                signup.addToHash(signup); 
	            } catch (NoSuchAlgorithmException | NullPointerException ex) {
	                ex.printStackTrace();
	            }
	            FirstScreen firstScreen = new FirstScreen();
	            firstScreen.setVisible(true);
	            dispose();
	        }
	    });
        


        // Create a back button
        backButton = new JButton("Back");
        backButton.setBounds((inputPanel.getWidth() - signupButton.getPreferredSize().width) / 2 - 12, middleY + 215, signupButton.getPreferredSize().width + 25, signupButton.getPreferredSize().height);
        inputPanel.add(backButton);

        // Actions for Back button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                FirstScreen firstScreen = new FirstScreen();
                firstScreen.setVisible(true);
                dispose(); 
            }
        });



        // Scales different components when the window is resized
        this.addComponentListener(new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {

            // For Image
            Image newImage = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            picLabel.setIcon(new ImageIcon(newImage));
            picLabel.setBounds(0, 0, getWidth(), getHeight());

            // For inputPanel
            int inputPanelX = (picPanel.getWidth() - inputPanelWidth) / 2;
            int inputPanelY = (picPanel.getHeight() - inputPanelHeight) / 2;
            inputPanel.setBounds(inputPanelX, inputPanelY, inputPanelWidth, inputPanelHeight);
        }

    });
        
	}
}
