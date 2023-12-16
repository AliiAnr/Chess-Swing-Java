import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
      imagePanel.setBackground(Color.YELLOW);
      imagePanel.setLayout(new GridBagLayout());
      topPanel.setPreferredSize(new Dimension(808, 70));
      topPanel.setBackground(Color.BLUE);
      topPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

      JPanel namePanel = new JPanel();
      namePanel.setPreferredSize(new Dimension(620, 68));
      namePanel.setBackground(Color.PINK);
      namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); 

      JLabel nameLabel = new JLabel("Ali Gantengaaaa");
      nameLabel.setFont(poppinsFontBold.deriveFont(20f));
      nameLabel.setPreferredSize(new Dimension(600, 25));
      
      nameLabel.setBackground(Color.ORANGE);
      nameLabel.setOpaque(true);
      namePanel.add(nameLabel);
      
      JPanel timePanel = new JPanel();
      timePanel.setPreferredSize(new Dimension(119, 68));
      timePanel.setBackground(Color.GREEN);
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
      imagePanel2.setBackground(Color.YELLOW);
      imagePanel2.setLayout(new GridBagLayout());
      bottomPanel.setPreferredSize(new Dimension(808, 70));
      bottomPanel.setBackground(Color.BLUE);
      bottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

      JPanel namePanel2 = new JPanel();
      namePanel2.setPreferredSize(new Dimension(620, 68));
      namePanel2.setBackground(Color.PINK);
      namePanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); 

      JLabel nameLabel2 = new JLabel("Ali Gantengaaaa");
      nameLabel2.setFont(poppinsFontBold.deriveFont(20f));
      nameLabel2.setPreferredSize(new Dimension(600, 25));
      
      nameLabel2.setBackground(Color.ORANGE);
      nameLabel2.setOpaque(true);
      namePanel2.add(nameLabel2);
      
      JPanel timePanel2 = new JPanel();
      timePanel2.setPreferredSize(new Dimension(119, 68));
      timePanel2.setBackground(Color.GREEN);
      timePanel2.setLayout(new GridBagLayout());
      timePanel2.add(chessBoard.whiteTimer);

      bottomPanel.add(imagePanel2);
      bottomPanel.add(namePanel2);
      bottomPanel.add(timePanel2);

      rightPanel.setPreferredSize(new Dimension(190, 970));
      rightPanel.setBackground(Color.GREEN);

      leftPanel.setPreferredSize(new Dimension(820, 970));
      leftPanel.setBackground(Color.ORANGE);

      panel.setPreferredSize(new Dimension(1450, 970));
      panel.setBackground(Color.PINK);

      this.setTitle("Chess");
      this.setLayout(new GridBagLayout());
      panel.setLayout(new FlowLayout(FlowLayout.LEFT, 30, 0));
      leftPanel.add(topPanel);
      leftPanel.add(chessBoard);
      leftPanel.add(bottomPanel);
      panel.add(leftPanel);
      panel.add(rightPanel);
      this.add(panel);
      this.getContentPane().setBackground(Color.YELLOW);
      this.setSize(new Dimension(1500, 1030));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }

}
