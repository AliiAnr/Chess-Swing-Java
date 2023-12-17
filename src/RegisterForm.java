import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class RegisterForm extends JFrame {

    public void initComponent() {
        // Creating a black background panel
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setBackground(Color.decode("#2C3333"));
        backgroundPanel.setLayout(null);

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("ChessAja\\image\\logo.png").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(40, 20, 400, 200);  // Adjust the position and size as needed
        backgroundPanel.add(imageLabel);

        int yOffset = 200;  // Set the desired vertical margin

        JLabel labelLogin = new JLabel("Register");
        labelLogin.setBounds(440, 100 + yOffset, 300, 50);
        labelLogin.setFont(new Font("Arial", Font.BOLD, 36));
        labelLogin.setForeground(Color.decode("#A5C9CA"));
        labelLogin.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(labelLogin);

        JLabel labelInfo = new JLabel("Please, enter your details");
        labelInfo.setForeground(Color.decode("#A5C9CA"));
        labelInfo.setFont(new Font("Arial", Font.BOLD, 18));
        labelInfo.setBounds(480, 150 + yOffset, 300, 30);
        labelInfo.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(labelInfo);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.decode("#A5C9CA"));
        usernameLabel.setBounds(415, 200 + yOffset, 300, 30);
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(usernameLabel);


        JTextField usernameTxt = new JTextField();
        usernameTxt.setBorder(null);
        usernameTxt.setBounds(520, 240 + yOffset, 420, 55);
        usernameTxt.setFont(new Font("Arial", Font.PLAIN, 26));
        backgroundPanel.add(usernameTxt);

       


        JPasswordField passwordTxt = new JPasswordField();
        passwordTxt.setBorder(null);
        passwordTxt.setBounds(520, 350 + yOffset, 420, 55);
        passwordTxt.setFont(new Font("Arial", Font.PLAIN, 26));
        backgroundPanel.add(passwordTxt);

        JLabel passwordLabelC = new JLabel("Password");
        passwordLabelC.setForeground(Color.decode("#A5C9CA"));
        passwordLabelC.setBounds(420, 310 + yOffset, 300, 30);
        passwordLabelC.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabelC.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundPanel.add(passwordLabelC);

        JLabel passwordLabelconfirm = new JLabel("Confirm Password");
        passwordLabelconfirm.setBounds(520, 420 + yOffset, 300, 30);
        passwordLabelconfirm.setFont(new Font("Arial", Font.BOLD, 18));

        passwordLabelconfirm.setForeground(Color.decode("#A5C9CA"));
        backgroundPanel.add(passwordLabelconfirm);

        JPasswordField passwordTxtconfirm = new JPasswordField();
        passwordTxtconfirm.setBounds(520, 470 + yOffset, 420, 55);
        passwordTxtconfirm.setBorder(null);
        backgroundPanel.add(passwordTxtconfirm);

        JButton createaccount = new JButton("Create Account");
        createaccount.setBounds(520, 570 + yOffset, 420, 50);
        createaccount.setFont(new Font("Arial", Font.BOLD, 24));
        createaccount.setBorder(null);
        createaccount.setForeground(Color.WHITE);
        createaccount.setBackground(Color.decode("#7ec139"));
        backgroundPanel.add(createaccount);

        JLabel labelInfo2 = new JLabel("Already have an Account?");
        labelInfo2.setFont(new Font("Arial", Font.PLAIN, 16));
        labelInfo2.setBounds(520, 670 + yOffset, 300, 30);
        labelInfo2.setForeground(Color.decode("#A5C9CA"));
        backgroundPanel.add(labelInfo2);

        JButton loginbutton = new JButton("Login here");
        loginbutton.setForeground(Color.WHITE);
        loginbutton.setBackground(Color.decode("#7ec139"));
        loginbutton.setBorder(null);
        loginbutton.setBounds(740, 670 + yOffset, 200, 30);
        backgroundPanel.add(loginbutton);

        loginbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm loginForm = new LoginForm();
                loginForm.initComponent();
                dispose();
            }
        });

        createaccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameTxt.getText();
                String password = String.valueOf(passwordTxt.getPassword());
                String confirmPassword = String.valueOf(passwordTxtconfirm.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill all fields");
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Confirm password doesn't match");
                    return;
                } else {
                    addUserToDatabase(username, password);
                }
            }

            private boolean isUsernameExists(String username) {
                final String DB_URL = "jdbc:mysql://localhost:3308/chessapp";
                final String USERNAME = "root";
                final String PASSWORD = "";

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    String query = "SELECT * FROM user WHERE username = ?";
                    PreparedStatement preparedStatement = conn.prepareStatement(query);
                    preparedStatement.setString(1, username);

                    ResultSet resultSet = preparedStatement.executeQuery();

                    boolean result = resultSet.next();

                    resultSet.close();
                    preparedStatement.close();
                    conn.close();

                    return result;

                } catch (SQLException e) {
                    e.printStackTrace();
                    return true; // Assume username exists in case of an error
                }
            }

            private void addUserToDatabase(String username, String password) {
                if (isUsernameExists(username)) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Registration failed.");
                } else {
                    final String DB_URL = "jdbc:mysql://localhost:3308/chessapp";
                    final String USERNAME = "root";
                    final String PASSWORD = "";

                    try {
                        Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                        String sql = "INSERT INTO user (username, password) VALUES (?, ?)";
                        PreparedStatement preparedStatement = conn.prepareStatement(sql);
                        preparedStatement.setString(1, username);
                        preparedStatement.setString(2, password);

                        int addedRows = preparedStatement.executeUpdate();

                        if (addedRows > 0) {
                            JOptionPane.showMessageDialog(null, "Registration successful");
                        }

                        preparedStatement.close();
                        conn.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Registration failed due to an error.");
                    }
                }
            }
        });

        // Add the backgroundPanel to the frame
        add(backgroundPanel);

        setTitle("Register Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1450, 1030);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        RegisterForm rf = new RegisterForm();
        rf.initComponent();
    }
}
