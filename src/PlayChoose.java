import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class PlayChoose extends JFrame {

   Font poppinsFont = null;
   Font poppinsFontBold = null;
   
   

   PlayChoose() {
      try {
         poppinsFont = Font.createFont(Font.TRUETYPE_FONT,
               new File("Fontz\\Poppins-Regular.ttf"));
         poppinsFontBold = Font.createFont(Font.TRUETYPE_FONT,
               new File("Fontz\\Poppins-Bold.ttf"));
      } catch (FontFormatException | IOException e) {
         e.printStackTrace();
      }
      
      final String DB_URL_ = "jdbc:mysql://localhost:3306/chessapp";
      final String USERNAME_ = "root";
      final String PASSWORD_ = "";
      try {
          Connection conn = DriverManager.getConnection(DB_URL_, USERNAME_, PASSWORD_);
  
          String sql = "SELECT score FROM user WHERE username = ?";
          PreparedStatement pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, UserAPI.getUserName());
  
          ResultSet resultSet = pstmt.executeQuery();
  
          if (resultSet.next()) {
              UserAPI.setUserScore(resultSet.getInt("score"));  
          }
  
          pstmt.close();
          conn.close();
  
      } catch (Exception e) {
          System.err.println("Database connection error");
      }
      
      JPanel panel = new JPanel();
      JPanel rightPanel = new JPanel();

      rightPanel.setPreferredSize(new Dimension(500, 870));
      rightPanel.setBackground(Color.decode("#262522"));
      rightPanel.setLayout(new FlowLayout());

      // Fetch users from database
      final String DB_URL = "jdbc:mysql://localhost:3306/chessapp";
      final String USERNAME = "root";
      final String PASSWORD = "";
      try {
         Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

         String sql = "SELECT * FROM user WHERE username != ? ORDER BY score DESC";
         PreparedStatement pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, UserAPI.getUserName());

         ResultSet resultSet = pstmt.executeQuery();

         while (resultSet.next()) {
            String username = resultSet.getString("username");
            int score = resultSet.getInt("score");

            // Create user list for each user
            JPanel userList = createUserList(username, score);
            rightPanel.add(userList);
         }

         pstmt.close();
         conn.close();

      } catch (Exception e) {
         System.err.println("Database connection error");
      }

      panel.setPreferredSize(new Dimension(1325, 900));
      panel.setBackground(Color.decode("#262522"));

      this.setTitle("Chess");
      panel.setLayout(new GridBagLayout());
      panel.add(rightPanel);
      this.add(panel);
      this.setLayout(new GridBagLayout());
      this.getContentPane().setBackground(Color.decode("#262522"));
      this.setSize(new Dimension(1450, 1030));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }

   private JPanel createUserList(String username, int score) {
      String imagePath1;
      if (Math.random() < 0.5) {
          imagePath1 = "image/black_king.png";
      } else {
          imagePath1 = "image/white_king.png";
      }
      ImageIcon icon1 = new ImageIcon(
            new ImageIcon(imagePath1).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      JLabel imageLabelUser1 = new JLabel(icon1);
      JPanel imagePanelUser1 = new JPanel();
      imagePanelUser1.add(imageLabelUser1);
      imagePanelUser1.setPreferredSize(new Dimension(40, 40));
      imagePanelUser1.setBackground(Color.decode("#262522"));
      imagePanelUser1.setLayout(new GridBagLayout());

      JPanel namePanelUser1 = new JPanel();
      namePanelUser1.setPreferredSize(new Dimension(300, 30));
      namePanelUser1.setBackground(Color.decode("#302e2b"));
      namePanelUser1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

      JLabel nameLabelUser1 = new JLabel(username + "  (" + score + ")");
      nameLabelUser1.setFont(poppinsFontBold.deriveFont(15f));
      nameLabelUser1.setForeground(Color.decode("#f8f8f8"));
      nameLabelUser1.setPreferredSize(new Dimension(410, 25));

      nameLabelUser1.setBackground(Color.decode("#302e2b"));
      nameLabelUser1.setOpaque(true);
      namePanelUser1.add(nameLabelUser1);

      JPanel listUser1 = new JPanel();
      listUser1.setPreferredSize(new Dimension(500, 62));
      listUser1.setBackground(Color.decode("#302e2b"));
      listUser1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
      listUser1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#f8f8f8")));

      listUser1.add(imagePanelUser1);
      listUser1.add(namePanelUser1);

      listUser1.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            listUser1.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e) {
            listUser1.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }

         @Override
         public void mouseClicked(MouseEvent e) {
            System.out.println(username + " clicked");
            UserAPI.setOpponentName(username);
            UserAPI.setOpponentScore(score);
            new ChessContainer();
            dispose();
         }
      });

      return listUser1;
   }
}