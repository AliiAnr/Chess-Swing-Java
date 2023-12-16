import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class ChessContainer extends JFrame {

   ChessContainer() {
      JPanel panel = new JPanel();
      JPanel rightPanel = new JPanel();
      JPanel leftPanel = new JPanel();
      ChessBoard chessBoard = new ChessBoard();
      JPanel topPanel = new JPanel();
      topPanel.setPreferredSize(new Dimension(840, 60));
      topPanel.setBackground(Color.BLUE);
      topPanel.add(chessBoard.blackTimer);
      
      JPanel bottomPanel = new JPanel();
      bottomPanel.setPreferredSize(new Dimension(840, 60));
      bottomPanel.setBackground(Color.RED);
      bottomPanel.add(chessBoard.whiteTimer);
      
      rightPanel.setPreferredSize(new Dimension(200, 970));
      rightPanel.setBackground(Color.GREEN);
      
      leftPanel.setPreferredSize(new Dimension(860, 970));
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
