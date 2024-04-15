package Java.Screens;
import Java.TextFields.PasswordField;
import Java.TextFields.TextField;
import javafx.scene.text.Text;

import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JButton;

import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import javax.swing.WindowConstants;

public class ForgotPass extends JFrame{
    private JButton resetBtn;
    private JLabel appNameLabel;
    private PasswordField confirmPasswordText;
    private  JTextField emailText;
    private  JPanel imagePanel;
    public JButton loginBtn;
    private JPanel mainPanel;
    private PasswordField passwordText;
    private JPanel ForgotPasswordPanel;
    private  JLabel ForgotPasswordLabel;
    private ImageIcon icon;
    private JLabel iconLabel;
    private JLabel incorrectPasswordLabel;
    private TextField nameText;
    private JLabel emailLabel;
    private Image image;
    private Image newImg;

    public ForgotPass() {
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
                    e.printStackTrace();
        } 
        initComponents();  
    } 
    private void initComponents(){
    //Main Frame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Forgot Password");
        setResizable(false);

    //Main Panel Settings
        mainPanel = new JPanel();
            mainPanel.setBackground(new Color(255, 255, 255));
            mainPanel.setMinimumSize(new Dimension(1000, 600));
            mainPanel.setPreferredSize(new Dimension(1000, 600));

    //Image Panel Settings
        imagePanel = new JPanel();
            imagePanel.setBackground(new Color(21, 170, 180));
            imagePanel.setMinimumSize(new Dimension(500, 600));
            imagePanel.setPreferredSize(new Dimension(500, 600));
    
    //Icon settings
            icon = new ImageIcon("src/img/Icon.png");
            image = icon.getImage();
            newImg = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
            iconLabel = new JLabel(icon);
    //App Name Label Settings
            appNameLabel = new JLabel();
            appNameLabel.setFont(new Font("Showcard Gothic", 0, 24)); // NOI18N
            appNameLabel.setForeground(new Color(255, 255, 255));
            appNameLabel.setText("SongScope");

        //Forgot Password Panel Settings
            ForgotPasswordPanel= new JPanel();
            ForgotPasswordPanel.setBackground(new Color(255, 255,255));
            ForgotPasswordPanel.setMinimumSize(new Dimension(500,600));
            ForgotPasswordPanel.setPreferredSize(new Dimension(500,600));
        //Forgot Password Label Settings
            ForgotPasswordLabel= new JLabel("Forgot Password");
            ForgotPasswordLabel.setBackground(new Color(0,102,102));
            ForgotPasswordLabel.setFont(new Font("Segoe UI",1,24));
    
        //Email Text Settings
        emailText= new TextField();
            emailText.setForeground(new Color(102,102,102));
            //emailText.setLabelText("Email");
        
        //Email Label Settings
        emailLabel= new JLabel("registered email address");
            emailLabel.setBackground(new Color(0,102,102));
            emailLabel.setFont(new Font("Segoe UI",1, 24));
        //Reset Btn Settings
            resetBtn= new JButton("Reset Password");
            resetBtn.setBackground(new Color(21, 170, 180));
            resetBtn.setBorder(null);
            resetBtn.setRolloverEnabled(false);
            resetBtn.setFocusPainted(false);
            resetBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    forgotPasswordBtnActionPerformed(evt);
                }
            });
        //Return to Login Settings
            loginBtn = new JButton("Remember Password? Login");
            loginBtn.setBackground(new Color(21, 170, 180, 64));
            loginBtn.setRolloverEnabled(false);
            loginBtn.setFocusPainted(false);
            loginBtn.setBorder(null);
            loginBtn.setPreferredSize(new Dimension(250, 50));
            loginBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    loginBtnActionPerformed(evt);
                }
            });
            
    
            getContentPane().add(mainPanel);
    
        //Main Panel Layout
        GroupLayout mainPanelLayout= new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(ForgotPasswordPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(ForgotPasswordPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        GroupLayout imagePanelLayout = new GroupLayout(imagePanel);
            imagePanel.setLayout(imagePanelLayout);
            imagePanelLayout.setHorizontalGroup(
                imagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(imagePanelLayout.createSequentialGroup()
                    .addGap(152, 152, 152)
                    .addGroup(imagePanelLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(iconLabel) 
                        .addComponent(appNameLabel))
                    .addContainerGap(164, Short.MAX_VALUE))
            );
            imagePanelLayout.setVerticalGroup(
                imagePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, imagePanelLayout.createSequentialGroup()
                    .addContainerGap(1, Short.MAX_VALUE)
                    .addComponent(iconLabel) 
                    .addGap(20)
                    .addComponent(appNameLabel)
                    .addContainerGap(100, Short.MAX_VALUE))
            );
        //Forgot Password Panel Layout
            GroupLayout ForgotPassPanelLayout = new GroupLayout(ForgotPasswordPanel);
            ForgotPasswordPanel.setLayout(ForgotPassPanelLayout);
            ForgotPassPanelLayout.setHorizontalGroup(
                ForgotPassPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ForgotPassPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Add a gap before the components
                    .addComponent(ForgotPasswordLabel)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) // Add a gap after the components
                .addGroup(ForgotPassPanelLayout.createSequentialGroup()
                    .addGap(75)
                    .addGroup(ForgotPassPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(ForgotPassPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(emailText, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addComponent(resetBtn, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
                    .addGap(75))
            );
            ForgotPassPanelLayout.setVerticalGroup(
                ForgotPassPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(ForgotPassPanelLayout.createSequentialGroup()
                    .addGap(55)
                    .addComponent(ForgotPasswordLabel)
                    .addGap(25)
                    .addComponent(emailText, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(3)
                    .addComponent(resetBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                    .addGap(60))
            );

        

        pack();
        
}

private void loginBtnActionPerformed(ActionEvent evt) {
    LoginScreen LoginFrame = new LoginScreen();
    LoginFrame.setVisible(true);
    LoginFrame.pack();
    LoginFrame.setLocationRelativeTo(null);
    this.dispose();
}
private void forgotPasswordBtnActionPerformed(ActionEvent evt){
    System.out.println("reset password btn clicked");

    MainScreen mainScreen = new MainScreen();
        mainScreen.pack();
        mainScreen.setVisible(true);
        mainScreen.setLocationRelativeTo(null);
        this.dispose();

        String email = emailText.getText();
        String password = new String(passwordText.getPassword());
        String confirmPassword = new String(confirmPasswordText.getPassword());

        // check if email in contained in database
        if (email.contains("@") && email.contains(".")) {
            //If Valid send an email to reset password
        } else {
            // if email is not valid, set text to "Invalid email"
        }

        // check if user used email to reset password
        if (password.equals(confirmPassword)) {
            // if they did update the data base with the new password with that email
            incorrectPasswordLabel.setText(" ");
        } else {
            // if they don't match
            incorrectPasswordLabel.setText("Passwords do not match!");
        }

}

}
