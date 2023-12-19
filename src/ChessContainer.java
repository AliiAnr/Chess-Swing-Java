import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Flow;

public class ChessContainer extends JFrame {
   Font poppinsFont = null;
   Font poppinsFontBold = null;

   

   ChessContainer() {
      

      try {
         poppinsFont = Font.createFont(Font.TRUETYPE_FONT,
               new File("Fontz\\Poppins-Regular.ttf"));
         poppinsFontBold = Font.createFont(Font.TRUETYPE_FONT,
               new File("Fontz\\Poppins-Bold.ttf"));
      } catch (FontFormatException | IOException e) {
         e.printStackTrace();
      }
      JPanel panel = new JPanel();
      JPanel userPanel = new JPanel();
      JPanel rightPanel = new JPanel();
      JPanel leftPanel = new JPanel();
      ChessBoard chessBoard = new ChessBoard();

      JPanel topPanel = new JPanel();
      String imagePathTop = "image/black_king.png";
      ImageIcon iconTop = new ImageIcon(
            new ImageIcon(imagePathTop).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH));
      JLabel imageLabelTop = new JLabel(iconTop);
      JPanel imagePanel = new JPanel();
      imagePanel.add(imageLabelTop);
      imagePanel.setPreferredSize(new Dimension(68, 68));
      imagePanel.setBackground(Color.decode("#262522"));
      imagePanel.setLayout(new GridBagLayout());
      topPanel.setPreferredSize(new Dimension(808, 70));
      topPanel.setBackground(Color.decode("#302e2b"));
      topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

      JPanel namePanel = new JPanel();
      namePanel.setPreferredSize(new Dimension(620, 68));
      namePanel.setBackground(Color.decode("#302e2b"));
      namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

      JLabel nameLabel = new JLabel(UserAPI.getOpponentName() + "  (" + UserAPI.getOpponentScore() + ")");
      nameLabel.setFont(poppinsFontBold.deriveFont(20f));
      nameLabel.setForeground(Color.decode("#f8f8f8"));
      nameLabel.setPreferredSize(new Dimension(600, 25));

      nameLabel.setBackground(Color.decode("#302e2b"));
      nameLabel.setOpaque(true);
      namePanel.add(nameLabel);

      JPanel timePanel = new JPanel();
      timePanel.setPreferredSize(new Dimension(119, 68));
      timePanel.setBackground(Color.decode("#262522"));
      timePanel.setLayout(new GridBagLayout());
      timePanel.add(chessBoard.blackTimer);

      topPanel.add(imagePanel);
      topPanel.add(namePanel);
      topPanel.add(timePanel);

      JPanel bottomPanel = new JPanel();
      String imagePathBottom = "image/white_king.png";
      ImageIcon iconBottom = new ImageIcon(
            new ImageIcon(imagePathBottom).getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH));
      JLabel imageLabelBottom = new JLabel(iconBottom);
      JPanel imagePanel2 = new JPanel();
      imagePanel2.add(imageLabelBottom);
      imagePanel2.setPreferredSize(new Dimension(68, 68));
      imagePanel2.setBackground(Color.decode("#262522"));
      imagePanel2.setLayout(new GridBagLayout());
      bottomPanel.setPreferredSize(new Dimension(808, 70));
      bottomPanel.setBackground(Color.decode("#302e2b"));
      bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

      JPanel namePanel2 = new JPanel();
      namePanel2.setPreferredSize(new Dimension(620, 68));
      namePanel2.setBackground(Color.decode("#302e2b"));
      namePanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

      JLabel nameLabel2 = new JLabel(UserAPI.getUserName() + "  (" + UserAPI.getUserScore() + ")");
      nameLabel2.setFont(poppinsFontBold.deriveFont(20f));
      nameLabel2.setForeground(Color.decode("#f8f8f8"));
      nameLabel2.setPreferredSize(new Dimension(600, 25));

      nameLabel2.setBackground(Color.decode("#302e2b"));
      nameLabel2.setOpaque(true);
      namePanel2.add(nameLabel2);

      JPanel timePanel2 = new JPanel();
      timePanel2.setPreferredSize(new Dimension(119, 68));
      timePanel2.setBackground(Color.decode("#262522"));
      timePanel2.setLayout(new GridBagLayout());
      timePanel2.add(chessBoard.whiteTimer);

      bottomPanel.add(imagePanel2);
      bottomPanel.add(namePanel2);
      bottomPanel.add(timePanel2);

      rightPanel.setPreferredSize(new Dimension(500, 970));
      rightPanel.setBackground(Color.decode("#262522"));
      JPanel resignPanel = new JPanel();
      resignPanel.setPreferredSize(new Dimension(450, 57));
      resignPanel.setBackground(Color.decode("#262522"));
      resignPanel.setLayout(new GridBagLayout());
      JPanel drawPanel = new JPanel();
      drawPanel.setPreferredSize(new Dimension(450, 57));
      drawPanel.setBackground(Color.decode("#262522"));
      drawPanel.setLayout(new GridBagLayout());

      RoundedButton resignButton = createButton("Resign", 15, 1);
      resignButton.setFont(poppinsFontBold.deriveFont(20f));
      resignButton.setPreferredSize(new Dimension(420, 55));
      resignButton.setBackground(Color.decode("#42413f"));
      resignButton.setForeground(Color.WHITE);
      resignPanel.add(resignButton);
      resignButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            chessBoard.askResign(chessBoard.isWhitesTurn);

         }
      });

      RoundedButton drawButton = createButton("Draw", 15, 2);
      drawButton.setFont(poppinsFontBold.deriveFont(20f));
      drawButton.setPreferredSize(new Dimension(420, 55));
      drawButton.setBackground(Color.decode("#7ec139"));
      drawButton.setForeground(Color.WHITE);
      drawPanel.add(drawButton);
      drawButton.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            chessBoard.askDraw(chessBoard.isWhitesTurn);

         }
      });

      JPanel innerPanel = new JPanel();
      innerPanel.setPreferredSize(new Dimension(450, 170));
      innerPanel.setBackground(Color.decode("#262522"));
      innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));

      innerPanel.add(resignPanel);
      innerPanel.add(drawPanel);

      final String DB_URL = "jdbc:mysql://localhost:3306/chessapp";
      final String USERNAME = "root";
      final String PASSWORD = "";
      try {
         Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

         String sql = "SELECT * FROM user ORDER BY score DESC";
         PreparedStatement pstmt = conn.prepareStatement(sql);

         ResultSet resultSet = pstmt.executeQuery();

         while (resultSet.next()) {
            String userna = resultSet.getString("username");
            int scoreDb = resultSet.getInt("score");

            // Create user list for each user
            JPanel userList = createUserList(userna, scoreDb);
            userPanel.add(userList);
         }

         pstmt.close();
         conn.close();

      } catch (Exception e) {
         System.err.println("Database connection error");
      }

      userPanel.setPreferredSize(new Dimension(420, 760));
      userPanel.setBackground(Color.decode("#302e2b"));
      userPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

      rightPanel.add(innerPanel);
      rightPanel.add(userPanel);

      leftPanel.setPreferredSize(new Dimension(820, 970));
      leftPanel.setBackground(Color.decode("#302e2b"));

      panel.setPreferredSize(new Dimension(1400, 970));
      panel.setBackground(Color.decode("#302e2b"));

      this.setTitle("Chess");
      this.setLayout(new GridBagLayout());
      panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
      leftPanel.add(topPanel);
      leftPanel.add(chessBoard);
      leftPanel.add(bottomPanel);
      panel.add(leftPanel);
      panel.add(rightPanel);
      this.add(panel);
      this.getContentPane().setBackground(Color.decode("#302e2b"));
      this.setSize(new Dimension(1450, 1030));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }

   private JPanel createUserList(String username2, int score2) {
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

      JLabel nameLabelUser1 = new JLabel(username2 + "  (" + score2 + ")");
      nameLabelUser1.setFont(poppinsFontBold.deriveFont(15f));
      nameLabelUser1.setForeground(Color.decode("#f8f8f8"));
      nameLabelUser1.setPreferredSize(new Dimension(410, 25));

      nameLabelUser1.setBackground(Color.decode("#302e2b"));
      nameLabelUser1.setOpaque(true);
      namePanelUser1.add(nameLabelUser1);

      JPanel listUser1 = new JPanel();
      listUser1.setPreferredSize(new Dimension(420, 62));
      listUser1.setBackground(Color.decode("#302e2b"));
      listUser1.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
      listUser1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#f8f8f8")));

      listUser1.add(imagePanelUser1);
      listUser1.add(namePanelUser1);
      return listUser1;
   }

   private static RoundedButton createButton(String name, int cornerRadius, int number) {
      RoundedButton button = new RoundedButton(name, cornerRadius);
      button.setOpaque(false);
      button.setFocusable(false);
      button.setBorder(null);

      button.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            if (number == 1) {
               button.setBackground(Color.decode("#494846")); // Ubah warna teks saat hover

            } else if (number == 2) {

               button.setBackground(Color.decode("#90c05e")); // Ubah warna teks saat hover
            }
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e) {
            if (number == 1) {
               button.setBackground(Color.decode("#42413f"));

            } else if (number == 2) {

               button.setBackground(Color.decode("#7ec139"));
            }
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
      });

      return button;
   }

}
