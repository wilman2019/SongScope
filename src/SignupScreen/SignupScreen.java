package SignupScreen;

import java.awt.EventQueue;
import java.awt.Image;
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

import FirstScreen.FirstScreen;
import LoginScreen.LoginScreen;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class SignupScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JPasswordField txtEnterPassword;
	private JTextField txtEnterEmail;
	private JPanel picPanel;
	private JPanel inputPanel;

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
		
		picPanel = new JPanel(); // Create new panel for Login section
        picPanel.setBorder(new EmptyBorder(5, 5, 5, 5)); // Set border for panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit program on close
        setBounds(100, 100, 1354, 768); // Set size of frame
        picPanel.setLayout(null);
		
        ImageIcon imageIcon = new ImageIcon("SongScope2.jpg");
        Image image = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(image));
        picPanel.add(picLabel);
        
     // Allows picture to be resized with frame
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                // Scale the image to the new size of the frame
                Image newImage = imageIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                picLabel.setIcon(new ImageIcon(newImage));

                // fixes small bug where white area would show at top
                picLabel.setBounds(0, 0, getWidth(), getHeight());
            }
        });
        
        setContentPane(picPanel); // Set panel as content pane



        inputPanel = new JPanel(); // Create new panel for Login section

        inputPanel.setBackground(new Color(255, 255, 255, 200));
        inputPanel.setBounds(600, 150, 423, 515);
        inputPanel.setOpaque(true);
        inputPanel.setLayout(null);
        picPanel.add(inputPanel);
        
        picPanel.setComponentZOrder(inputPanel, 0);

        // Create a JLabel for the title
        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Monospaced", Font.BOLD, 36));
        lblTitle.setBounds((inputPanel.getWidth() - lblTitle.getPreferredSize().width) / 2, 10, lblTitle.getPreferredSize().width, lblTitle.getPreferredSize().height);
        inputPanel.add(lblTitle);
        
        // Calculate the y-coordinate for the middle of the inputPanel
        int middleY = inputPanel.getHeight() / 2;

        // Create a JLabel for the username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(10, middleY - 40, 100, 25);
        inputPanel.add(lblUsername);
        
        // Create a JTextField for the username input
        JTextField inputUsername = new JTextField();
        inputUsername.setBounds(120, middleY - 40, 100, 25);
        inputPanel.add(inputUsername);
        
     // Create a JLabel for the email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, middleY, 100, 25);
        inputPanel.add(lblEmail);
        
     // Create a JTextField for the email input
        JTextField inputEmail = new JTextField();
        inputEmail.setBounds(120, middleY, 100, 25);
        inputPanel.add(inputEmail);

        // Create a JLabel for the password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, middleY + 40, 100, 25);
        inputPanel.add(lblPassword);
        
        // Create a JPasswordField for the password input
        JPasswordField inputPassword = new JPasswordField();
        inputPassword.setBounds(120, middleY + 40, 100, 25);
        inputPassword.setEchoChar('*');
        inputPassword.setColumns(10);
        inputPanel.add(inputPassword);
        
        // Create a signup button
        JButton signupButton = new JButton("Signup");
        signupButton.setBounds ((inputPanel.getWidth() - signupButton.getPreferredSize().width) / 2 - 12, middleY + 175, signupButton.getPreferredSize().width + 25, signupButton.getPreferredSize().height);
        inputPanel.add(signupButton);
        inputPanel.setComponentZOrder(signupButton, 0);
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
        JButton backButton = new JButton("Back");
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
        
	}
}