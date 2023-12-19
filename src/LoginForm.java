import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginForm extends JFrame {

    public void initComponent() {
        // Creating a black background panel
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.decode("#2C3333"));
        backgroundPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("ChessAja\\image\\logo.png").getImage().getScaledInstance(500, 500, Image.SCALE_DEFAULT));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(475, 100, 500, 500);
        backgroundPanel.add(imageLabel);

        int yOffset = 250;

        JLabel labelLogin = new JLabel("Login");
        labelLogin.setBounds(440, 100 + yOffset, 300, 50);
        labelLogin.setFont(new Font("Arial", Font.BOLD, 36));
        labelLogin.setForeground(Color.decode("#A5C9CA"));
        labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(labelLogin);

        JLabel labelInfo = new JLabel("Please, enter your details");
        labelInfo.setForeground(Color.decode("#A5C9CA"));
        labelInfo.setFont(new Font("Arial", Font.BOLD, 18));
        labelInfo.setBounds(505, 150 + yOffset, 300, 30);
        labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(labelInfo);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.decode("#A5C9CA"));
        usernameLabel.setBounds(440, 200 + yOffset, 300, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(usernameLabel);

        JTextField usernameTxt = new JTextField();
        usernameTxt.setBorder(null);
        usernameTxt.setBounds(550, 240 + yOffset, 420, 55);
        usernameTxt.setFont(new Font("Arial", Font.PLAIN, 26));
        backgroundPanel.add(usernameTxt);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.decode("#A5C9CA"));
        passwordLabel.setBounds(440, 310 + yOffset, 300, 30);
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(passwordLabel);

        JPasswordField passwordTxt = new JPasswordField();
        passwordTxt.setBorder(null);
        passwordTxt.setBounds(550, 350 + yOffset, 420, 55);
        passwordTxt.setFont(new Font("Arial", Font.PLAIN, 26));
        backgroundPanel.add(passwordTxt);

        JButton logiButton = new JButton("Login");
        logiButton.setForeground(Color.white);
        logiButton.setBounds(550, 440 + yOffset, 420, 55);
        logiButton.setBackground(Color.decode("#7ec139"));
        logiButton.setFont(new Font("Arial", Font.BOLD, 18));
        logiButton.setBorder(null);
        backgroundPanel.add(logiButton);

        JLabel labelInfo2 = new JLabel("Don't have an Account?");
        labelInfo2.setFont(new Font("Arial", Font.PLAIN, 14));
        labelInfo2.setForeground(Color.decode("#A5C9CA"));
        labelInfo2.setBounds(480, 520 + yOffset, 300, 30);
        labelInfo2.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(labelInfo2);

        JButton regisbutton = new JButton("Register here");
        regisbutton.setForeground(Color.white);
        regisbutton.setBackground(Color.decode("#7ec139"));
        regisbutton.setBounds(830, 520 + yOffset, 130, 30);
        regisbutton.setBorder(null);
        backgroundPanel.add(regisbutton);

        logiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTxt.getText();
                String password = String.valueOf(passwordTxt.getPassword());

                User user = getAuthenticatedUser(username, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(LoginForm.this, "Login Berhasil!");
                    dispose();
                    UserAPI.setUserName(username);
                    Choose chose = new Choose();
                    
                } else {
                    JOptionPane.showMessageDialog(LoginForm.this, "Username & password salah", password, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        regisbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm rf = new RegisterForm();
                rf.initComponent();
                dispose();
            }
        });

        // Add the backgroundPanel to the frame
        add(backgroundPanel);

        setTitle("Login Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1450, 1030);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private User getAuthenticatedUser(String username, String password) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost:3306/chessapp";
        final String USERNAME = "root";
        final String PASSWORD = "";
        try {
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM user WHERE username=? AND password=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.username = resultSet.getString("username");
                user.password = resultSet.getString("password");
            }

            pstmt.close();
            conn.close();

        } catch (Exception e) {
            System.err.println("Database connection error");
        }
        return user;
    }
    
}
