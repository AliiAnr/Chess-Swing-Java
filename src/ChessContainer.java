import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;

public class ChessContainer extends JFrame {

   ChessContainer() {
      JPanel panel = new JPanel();
      ChessBoard chessBoard = new ChessBoard();
      JPanel topPanel = new JPanel();
      topPanel.setPreferredSize(new Dimension(990, 70));
      topPanel.setBackground(Color.BLUE);
      topPanel.add(chessBoard.blackTimer);
      
      JPanel bottomPanel = new JPanel();
      bottomPanel.setPreferredSize(new Dimension(990, 70));
      bottomPanel.setBackground(Color.RED);
      bottomPanel.add(chessBoard.whiteTimer);
      
      
      panel.setPreferredSize(new Dimension(1000, 1000));
      panel.setBackground(Color.PINK);

      this.setTitle("Chess");
      this.setLayout(new GridBagLayout());
      
      panel.add(topPanel);
      panel.add(chessBoard);
      panel.add(bottomPanel);
      this.add(panel);
      this.getContentPane().setBackground(Color.YELLOW);
      this.setSize(new Dimension(1100, 1070));
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLocationRelativeTo(null);
      this.setVisible(true);
   }

}
