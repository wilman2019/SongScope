package Java.Screens;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea txtWelcomeBackTo;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public FirstScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1339, 756);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 103, 125));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("SongScopeLogo.png"));
		lblNewLabel.setBounds(0, 0, 725, 800);
		contentPane.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon("SongScopeLogo.png");
		Image image = icon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH);
		lblNewLabel.setIcon(new ImageIcon(image));
		
		txtWelcomeBackTo = new JTextArea();
		txtWelcomeBackTo.setBackground(new Color(176, 103, 125));
		txtWelcomeBackTo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtWelcomeBackTo.setText("Welcome back to SongScope!");
		txtWelcomeBackTo.setBounds(850, 250, 347, 60);
		contentPane.add(txtWelcomeBackTo);
		txtWelcomeBackTo.setColumns(10);
		txtWelcomeBackTo.setEditable(false);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBackground(new Color(255, 255, 153));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					LoginScreen loginScreen = new LoginScreen();
					loginScreen.setVisible(true);
					dispose(); 
			}
		});
		btnNewButton.setBounds(920, 320, 200, 50);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Signup");
		btnNewButton_1.setForeground(new Color(0, 0, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBackground(new Color(255, 255, 153));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignupScreen signupScreen = new SignupScreen();
					signupScreen.setVisible(true);
					dispose(); 
			}
		});
		btnNewButton_1.setBounds(920, 393, 200, 50);
		contentPane.add(btnNewButton_1);
	}
}
