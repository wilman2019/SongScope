package Java.Screens;

import Java.TextFields.PasswordField;
import Java.TextFields.TextField;
import Java.Database.DatabaseManager;

import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Image;
import javax.swing.ImageIcon;

import javax.swing.WindowConstants;
import javax.swing.GroupLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.validator.routines.EmailValidator;
import org.mindrot.jbcrypt.BCrypt;



public class SignUpScreen extends JFrame {

    private JButton SignUpBtn;
    private JLabel appNameLabel;
    private PasswordField confirmPasswordText;
    private TextField emailText;
    private JPanel imagePanel;
    private JButton loginBtn;
    private JPanel mainPanel;
    private PasswordField passwordText;
    private JPanel signUpPanel;
    private JLabel singUpLabel;
    private ImageIcon icon;
    private JLabel iconLabel;
    private JLabel incorrectPasswordLabel;
    private TextField nameText;
    private Image image;
    private Image newImg;
    private Connection connection;




    public SignUpScreen() {
        // Set Look and Feel for Buttons
        try {
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
        } catch (Exception e) {
                    e.printStackTrace();
        }

        connection = DatabaseManager.getConnection();


        // Initialize Components
        initComponents();
    }

  
    private void initComponents() {
        // Main Frame Settings
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sign Up");
        setResizable(false);


        // Main Panel Settings
        mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setMinimumSize(new Dimension(1000, 600));
        mainPanel.setPreferredSize(new Dimension(1000, 600));

        
        // Image Panel Settings
        imagePanel = new JPanel();
        imagePanel.setBackground(new Color(21, 170, 180));
        imagePanel.setMinimumSize(new Dimension(500, 600));
        imagePanel.setPreferredSize(new Dimension(500, 600));



        // Icon Settings
        icon = new ImageIcon("src/img/Icon.png");
        image = icon.getImage();
        newImg = image.getScaledInstance(200, 200,  Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        iconLabel = new JLabel(icon);
        
        
        // App Name Label Settings
        appNameLabel = new JLabel();
        appNameLabel.setFont(new Font("Showcard Gothic", 0, 24)); // NOI18N
        appNameLabel.setForeground(new Color(255, 255, 255));
        appNameLabel.setText("SongScope");
        
        
        // Sign Up Panel Settings
        signUpPanel = new JPanel();
        signUpPanel.setBackground(new Color(255, 255, 255));
        signUpPanel.setMinimumSize(new Dimension(500, 600));
        signUpPanel.setPreferredSize(new Dimension(500, 600));
        
        
        // Sign Up Label Settings
        singUpLabel = new JLabel("Sign Up");
        singUpLabel.setBackground(new Color(0, 102, 102));
        singUpLabel.setFont(new Font("Segoe UI", 1, 24)); 
        
        
        // Name Settings
        nameText = new TextField();
        nameText.setForeground(new Color(102, 102, 102));
        nameText.setLabelText("Name");



        // Email Settings
        emailText = new TextField();
        emailText.setForeground(new Color(102, 102, 102));
        emailText.setLabelText("Email");
        
        
        // Password Settings
        passwordText = new PasswordField();
        passwordText.setForeground(new Color(102, 102, 102));
        passwordText.setLabelText("Password");
        passwordText.setShowAndHide(true);


        // Confirm Password Settings
        confirmPasswordText = new PasswordField();
        confirmPasswordText.setForeground(new Color(102, 102, 102));
        confirmPasswordText.setLabelText("Confirm Password");
        confirmPasswordText.setShowAndHide(true);


        // Incorrect Password Label Settings
        incorrectPasswordLabel = new JLabel(" ");
        incorrectPasswordLabel.setFont(new Font("Segoe UI", 0, 14));
        incorrectPasswordLabel.setForeground(new Color(255, 0, 0));
        incorrectPasswordLabel.setHorizontalAlignment(JLabel.CENTER);

        
        // Sign Up Button Settings
        SignUpBtn = new JButton("Sign Up");
        SignUpBtn.setBackground(new Color(21, 170, 180));
        SignUpBtn.setBorder(null);
        SignUpBtn.setRolloverEnabled(false);
        SignUpBtn.setFocusPainted(false);
        SignUpBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                SignUpBtnActionPerformed(evt);
            }
        });
        
        
        // Login Button Settings
        loginBtn = new JButton("Already have an account? Login");
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
        
        


        // Add Main Panel to the Frame
        getContentPane().add(mainPanel);



        // Main Panel Layout
        GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(signUpPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(imagePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
            .addComponent(signUpPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
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


       

            // Sign Up Panel Layout
            GroupLayout signUpPanelLayout = new GroupLayout(signUpPanel);
            signUpPanel.setLayout(signUpPanelLayout);
            signUpPanelLayout.setHorizontalGroup(
                signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(signUpPanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE) // Add a gap before the components
                    .addComponent(singUpLabel)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)) // Add a gap after the components
                .addGroup(signUpPanelLayout.createSequentialGroup()
                    .addGap(75)
                    .addGroup(signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                            .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addComponent(emailText, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addComponent(confirmPasswordText, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addComponent(incorrectPasswordLabel, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)
                            .addComponent(SignUpBtn, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 350, GroupLayout.PREFERRED_SIZE)))
                    .addGap(75))
            );
            signUpPanelLayout.setVerticalGroup(
                signUpPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(signUpPanelLayout.createSequentialGroup()
                    .addGap(55)
                    .addComponent(singUpLabel)
                    .addGap(25)
                    .addComponent(nameText, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(emailText, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(passwordText, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(30)
                    .addComponent(confirmPasswordText, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(incorrectPasswordLabel, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
                    .addGap(10)
                    .addComponent(SignUpBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
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

    private void SignUpBtnActionPerformed(ActionEvent evt) {

        // get all the text from the fields
        // hash password ad confirm password with BCrypt
        String name = nameText.getText();
        String email = emailText.getText();
        email = email.toLowerCase();
        String password = new String(passwordText.getPassword());
        String confirmPassword = new String(confirmPasswordText.getPassword());


        // check if email is valid and if password and confirm password match
        if (areInputsValid(name, email, password, confirmPassword) && !isEmailInDatabase(email)) {
            // fields are valid and not in database, insert new user
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            String sql = "INSERT INTO user(email, username, password) VALUES(?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                stmt.setString(1, email);
                stmt.setString(2, name);
                stmt.setString(3, hashedPassword);
                DatabaseManager.update(stmt);

            } catch (SQLException e) {
                e.printStackTrace();
            }


            // open the login screen
            MainScreen mainFrame = new MainScreen(email);
            mainFrame.setVisible(true);
            mainFrame.pack();
            mainFrame.setLocationRelativeTo(null);
            this.dispose();

        } else if (isEmailInDatabase(email)) {
            // email is in database
            incorrectPasswordLabel.setText("Email already exists");
        } else if (!password.equals(confirmPassword)) {
            // passwords do not match
            incorrectPasswordLabel.setText("Passwords do not match");
        } 
    }

    private boolean isEmailInDatabase(String email) {
        // create a query to check if the email is in the database
        String sql = "SELECT * FROM user WHERE email = ?";
        ResultSet result = null;
        
        // execute the query
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            result = stmt.executeQuery();
            
            // if we get a result, the email is in the database
            if (result.next()) {
                incorrectPasswordLabel.setText("Email already exists");
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close the result set
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    
        return false;
    }

    private boolean areInputsValid(String name, String email, String password, String confirmPassword) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            incorrectPasswordLabel.setText("Please fill in all fields");
            return false;
        }
    
        if (!EmailValidator.getInstance().isValid(email)) {
            incorrectPasswordLabel.setText("Invalid email");
            return false;
        }
    
        if (!arePasswordsValid(password, confirmPassword)) {
            return false;
        }
    
        return true;
    }

    private boolean arePasswordsValid(String password, String confirmPassword) {
        if (!password.equals(confirmPassword)) {
            incorrectPasswordLabel.setText("Passwords do not match");
            return false;
        }

        // regex for password having at least 1 uppercase, 1 digit, 8 characters
        String regex = "^(?=.*[0-9])(?=.*[A-Z]).{8,}$";
        if (!password.matches(regex)) {
            incorrectPasswordLabel.setText("Password needs 1 uppercase, 1 digit, 8 characters");
            return false;
        }

    
        return true;
    }


}