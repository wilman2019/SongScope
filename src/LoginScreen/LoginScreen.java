package LoginScreen;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FirstScreen.FirstScreen;
import SignupScreen.HandleSignup;
import SignupScreen.HidePassword;
import SignupScreen.SignupScreen;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class LoginScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtEnterUsername;
	private JPasswordField txtEnterPassword;
	private HashMap<String, HandleSignup> signupMap;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    public LoginScreen(HashMap<String, HandleSignup> signupMap) {
        this.signupMap = signupMap;
    }

	/**
	 * Create the frame.
	 */
	public LoginScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1354, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("SongScope2.jpg");
        Image image = imageIcon.getImage().getScaledInstance(1230, 692, Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(image));
        lblNewLabel.setBounds(10, 10, 1230, 692);
        contentPane.add(lblNewLabel);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255, 200));
        panel.setBounds(464, 89, 423, 515);
        panel.setOpaque(true);
        contentPane.add(panel);
        
        contentPane.setComponentZOrder(panel, 0);
        
        JTextArea txtrPassword = new JTextArea();
        txtrPassword.setBackground(new Color(0, 0, 0, 1));
        txtrPassword.setBounds(500, 350, 100, 25);
        txtrPassword.setText("Password");
        panel.add(txtrPassword);
        contentPane.setComponentZOrder(txtrPassword, 0);
        txtrPassword.setEditable(false);
        
        txtEnterPassword = new JPasswordField();
        txtEnterPassword.setBounds(500, 370, 100, 25);
        panel.add(txtEnterPassword);
        txtEnterPassword.setColumns(10);
        contentPane.setComponentZOrder(txtEnterPassword, 0);
        
        txtEnterUsername = new JTextField();
        txtEnterUsername.setBounds(500, 320, 100, 25);
        panel.add(txtEnterUsername);
        txtEnterUsername.setColumns(10);
        contentPane.setComponentZOrder(txtEnterUsername, 0);
        
        JTextArea txtrUsername = new JTextArea();
        panel.setBackground(new Color(255, 255, 255, 200));
        txtrUsername.setBackground(new Color(0, 0, 0, 1));
        txtrUsername.setBounds(500, 300, 100, 25);
        txtrUsername.setText("Username");
        panel.add(txtrUsername);
        contentPane.setComponentZOrder(txtrUsername, 0);
        txtrUsername.setEditable(false);
        
        JButton btnNewButton = new JButton("Login");
        btnNewButton.setBounds(625, 550, 100, 25);
        panel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = txtEnterUsername.getText();
                String password = new String(txtEnterPassword.getPassword());
                
                if (HandleSignup.getSignUpMap().containsKey(username)) {
                    HandleSignup handleSignup = HandleSignup.getSignUpMap().get(username);
                    
                    if (handleSignup != null && handleSignup.getUsername().equals(username)) {
                        try {
                            String hashedEnteredPassword = HidePassword.cipherPassword(password);
                            if (hashedEnteredPassword.equals(handleSignup.getPassword())) {
                                FirstScreen firstScreen = new FirstScreen();
                                firstScreen.setVisible(true);
                                dispose(); 
                            } else {
                                JOptionPane.showMessageDialog(null, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NoSuchAlgorithmException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        contentPane.setComponentZOrder(btnNewButton, 0);
        
        JTextArea txtrLogin = new JTextArea();
        txtrLogin.setBackground(new Color(0, 0, 0, 1));
        txtrLogin.setBounds(0, 0, 100, 100);
        txtrLogin.setFont(new Font("Monospaced", Font.PLAIN, 30));
        txtrLogin.setText("Login");
        txtrLogin.setEditable(false);
        panel.add(txtrLogin);
	}
}
