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
import java.util.concurrent.Flow;

public class ChessContainer extends JFrame {

   ChessContainer() {
      Font poppinsFont = null;
      Font poppinsFontBold = null;
      try {
         poppinsFont = Font.createFont(Font.TRUETYPE_FONT,
               new File("Fontz\\Poppins-Regular.ttf"));
         poppinsFontBold = Font.createFont(Font.TRUETYPE_FONT,
               new File("Fontz\\Poppins-Bold.ttf"));
      } catch (FontFormatException | IOException e) {
         e.printStackTrace();
      }
      JPanel panel = new JPanel();
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

      JLabel nameLabel = new JLabel("Ali Gantengaaaa (402)");
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

      JLabel nameLabel2 = new JLabel("Gita Mailand (200)");
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

      RoundedButton drawButton = createButton("Draw", 15, 2);
      drawButton.setFont(poppinsFontBold.deriveFont(20f));
      drawButton.setPreferredSize(new Dimension(420, 55));
      drawButton.setBackground(Color.decode("#7ec139"));
      drawButton.setForeground(Color.WHITE);
      drawPanel.add(drawButton);
      
      JPanel innerPanel = new JPanel();
      innerPanel.setPreferredSize(new Dimension(450, 170));
      innerPanel.setBackground(Color.decode("#262522"));
      innerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
      
      innerPanel.add(resignPanel);
      innerPanel.add(drawPanel);
      
      JPanel userPanel = new JPanel();
      userPanel.setPreferredSize(new Dimension(420, 760));
      userPanel.setBackground(Color.decode("#302e2b"));
      userPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
      
            String imagePath1 = "image/black_king.png";
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

      JLabel nameLabelUser1 = new JLabel("Ali Gantengaaaa (402)");
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
      listUser1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#eaeaea")));
      
      listUser1.add(imagePanelUser1);
      listUser1.add(namePanelUser1);
      
            String imagePath2 = "image/white_king.png";
      ImageIcon icon2 = new ImageIcon(
            new ImageIcon(imagePath2).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
      JLabel imageLabelUser2 = new JLabel(icon2);
      JPanel imagePanelUser2 = new JPanel();
      imagePanelUser2.add(imageLabelUser2);
      imagePanelUser2.setPreferredSize(new Dimension(40, 40));
      imagePanelUser2.setBackground(Color.decode("#262522"));
      imagePanelUser2.setLayout(new GridBagLayout());

      JPanel namePanelUser2 = new JPanel();
      namePanelUser2.setPreferredSize(new Dimension(300, 30));
      namePanelUser2.setBackground(Color.decode("#302e2b"));
      namePanelUser2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

      JLabel nameLabelUser2 = new JLabel("Gita Mailand (200)");
      nameLabelUser2.setFont(poppinsFontBold.deriveFont(15f));
      nameLabelUser2.setForeground(Color.decode("#f8f8f8"));
      nameLabelUser2.setPreferredSize(new Dimension(410, 25));

      nameLabelUser2.setBackground(Color.decode("#302e2b"));
      nameLabelUser2.setOpaque(true);
      namePanelUser2.add(nameLabelUser2);
      
      JPanel listUser2 = new JPanel();
      listUser2.setPreferredSize(new Dimension(420, 62));
      listUser2.setBackground(Color.decode("#302e2b"));
      listUser2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
      listUser2.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#eaeaea")));
      
      listUser2.add(imagePanelUser2);
      listUser2.add(namePanelUser2);
      
      
      
      userPanel.add(listUser1);
      userPanel.add(listUser2);
      
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
               
            }else if (number == 2) {
               
               button.setBackground(Color.decode("#90c05e")); // Ubah warna teks saat hover
            }
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
         }

         @Override
         public void mouseExited(MouseEvent e) {
            if (number == 1) {
               button.setBackground(Color.decode("#42413f"));
               
            }else if (number == 2) {
               
               button.setBackground(Color.decode("#7ec139"));
            }
            button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         }
      });

      return button;
   }

}
