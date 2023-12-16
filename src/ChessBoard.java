import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.event.MouseListener;

public class ChessBoard extends JPanel {
   public int titleSize = 101;

   int cols = 8;
   int rows = 8;

   ArrayList<Pieces> pieces = new ArrayList<Pieces>();

   public Pieces selectedPiece;

   Input input = new Input(this);

   public int enPassentTile = -1;

   public CheckScanner checkScanner = new CheckScanner(this);

   public boolean isWhitesTurn = true;

   public PlayerTimer whiteTimer;
   public PlayerTimer blackTimer;

   private boolean isWhiteRun = false;
   private boolean isBlackRun = false;

   public ChessBoard() {
      this.setPreferredSize(new Dimension(titleSize * cols, titleSize * rows));
      this.addMouseListener(input);
      this.addMouseMotionListener(input);
      addPieces();
      this.whiteTimer = new PlayerTimer(3);
      this.blackTimer = new PlayerTimer(3);
      blackTimer.pauseTimer();
   }

   public void switchTurn() {
      if (!isWhitesTurn) {
         this.whiteTimer.pauseTimer();
         this.blackTimer.resumeTimer();
      } else {
         this.blackTimer.pauseTimer();
         this.whiteTimer.resumeTimer();
      }
   }

   public Pieces getPiece(int col, int row) {
      for (Pieces piece : pieces) {
         if (piece.col == col && piece.row == row) {
            return piece;
         }
      }
      return null;
   }

   public void makeMove(Move move) {
      if (move.getPiece().isWhite == isWhitesTurn) {

         if (move.getPiece().name.equals("Pawn")) {
            movePawn(move);
         } else if (move.getPiece().name.equals("King")) {
            moveKing(move);
         }
         move.getPiece().col = move.getNewCol();
         move.getPiece().row = move.getNewRow();
         move.getPiece().xPos = move.getNewCol() * titleSize;
         move.getPiece().yPos = move.getNewRow() * titleSize;

         move.getPiece().isFirstMove = false;

         capture(move);
         isWhitesTurn = !isWhitesTurn;
         switchTurn();
      }

   }

   private void moveKing(Move move) {
      if (Math.abs(move.getPiece().col - move.getNewCol()) == 2) {
         Pieces rook;
         if (move.getPiece().col < move.getNewCol()) {
            rook = getPiece(7, move.getPiece().row);
            rook.col = 5;
         } else {
            rook = getPiece(0, move.getPiece().row);
            rook.col = 3;
         }
         rook.xPos = rook.col * titleSize;
      }
   }

   private void movePawn(Move move) {

      int colorIndex = move.getPiece().isWhite ? 1 : -1;

      if (getTileNum(move.getNewCol(), move.getNewRow()) == enPassentTile) {
         move.setCapture(getPiece(move.getNewCol(), move.getNewRow() + colorIndex));
      }

      if (Math.abs(move.getPiece().row - move.getNewRow()) == 2) {
         enPassentTile = getTileNum(move.getNewCol(), move.getNewRow() + colorIndex);
      } else {
         enPassentTile = -1;

      }

      colorIndex = move.getPiece().isWhite ? 0 : 7;

      if (move.getNewRow() == colorIndex) {
         promotionPawn(move);
      }

   }

   private void promotionPawn(Move move) {
      JFrame frame = new JFrame("Pawn Promotion");
      JPanel panel = new JPanel();
      
      String color = move.getPiece().isWhite ? "white" : "black";
      JButton queenButton = new JButton(new ImageIcon("image/" + color + "_queen.png"));
      queenButton.addActionListener(e -> {
         pieces.remove(move.getPiece());
         pieces.add(new Queen(this, move.getNewCol(), move.getNewRow(), move.getPiece().isWhite));
         frame.dispose();
         repaint();
      });
      panel.add(queenButton);
      
      JButton rookButton = new JButton(new ImageIcon("image/" + color + "_rook.png"));
      rookButton.addActionListener(e -> {
         pieces.remove(move.getPiece());
         pieces.add(new Rook(this, move.getNewCol(), move.getNewRow(), move.getPiece().isWhite));
         frame.dispose();
         repaint();
      });
      panel.add(rookButton);

      JButton bishopButton = new JButton(new ImageIcon("image/" + color + "_bishop.png"));
      bishopButton.addActionListener(e -> {
         pieces.remove(move.getPiece());
         pieces.add(new Bishop(this, move.getNewCol(), move.getNewRow(), move.getPiece().isWhite));
         frame.dispose();
         repaint();
      });
      panel.add(bishopButton);

      JButton knightButton = new JButton(new ImageIcon("image/" + color + "_knight.png"));
      knightButton.addActionListener(e -> {
         pieces.remove(move.getPiece());
         pieces.add(new Knight(this, move.getNewCol(), move.getNewRow(), move.getPiece().isWhite));
         frame.dispose();
         repaint();
      });
      panel.add(knightButton);
      frame.add(panel);
      
      frame.pack();
      frame.setVisible(true);
   }
   
   public void capture(Move move) {
      pieces.remove(move.getCapture());
   }

   public boolean isValidMove(Move move) {

      if (sameColor(move.getPiece(), move.getCapture())) {
         return false;
      }

      if (!move.getPiece().isValidMovement(move.getNewCol(), move.getNewRow())) {
         return false;
      }

      if (move.getPiece().moveCollidesWithPiece(move.getNewCol(), move.getNewRow())) {
         return false;
      }

      if (checkScanner.isKingChecked(move)) {
         return false;
      }

      return true;
   }

   public boolean sameColor(Pieces piece1, Pieces piece2) {
      if (piece1 == null || piece2 == null) {
         return false;
      }
      return piece1.isWhite == piece2.isWhite;
   }

   public int getTileNum(int col, int row) {
      return row * rows + col;
   }

   Pieces findKing(boolean isWhite) {
      for (Pieces piece : pieces) {
         if (piece.name.equals("King") && piece.isWhite == isWhite) {
            return piece;
         }
      }
      return null;
   }

   public void addPieces() {
      pieces.add(new Knight(this, 1, 0, false));
      pieces.add(new Knight(this, 6, 0, false));
      pieces.add(new Knight(this, 1, 7, true));
      pieces.add(new Knight(this, 6, 7, true));
      pieces.add(new Bishop(this, 2, 0, false));
      pieces.add(new Bishop(this, 5, 0, false));
      pieces.add(new Bishop(this, 2, 7, true));
      pieces.add(new Bishop(this, 5, 7, true));
      pieces.add(new Rook(this, 0, 0, false));
      pieces.add(new Rook(this, 7, 0, false));
      pieces.add(new Rook(this, 0, 7, true));
      pieces.add(new Rook(this, 7, 7, true));
      pieces.add(new Queen(this, 3, 0, false));
      pieces.add(new Queen(this, 3, 7, true));
      pieces.add(new King(this, 4, 0, false));
      pieces.add(new King(this, 4, 7, true));
      for (int i = 0; i < 8; i++) {
         pieces.add(new Pawn(this, i, 1, false));
         pieces.add(new Pawn(this, i, 6, true));
      }
   }

   public void paintComponent(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      for (int row = 0; row < rows; row++) {
         for (int col = 0; col < cols; col++) {
            if ((row + col) % 2 == 0) {
               g2d.setColor(Color.decode("#e9edcc"));
            } else {
               g2d.setColor(Color.decode("#779954"));
            }

            g2d.fillRect(col * titleSize, row * titleSize, titleSize, titleSize);
         }
      }

      // make highlights
      if (selectedPiece != null && selectedPiece.isWhite == isWhitesTurn) {
         for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
               if (isValidMove(new Move(this, selectedPiece, col, row))) {
                  // Enable anti-aliasing
                  g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                  int x = col * titleSize + titleSize / 2;
                  int y = row * titleSize + titleSize / 2;

                  // Check if there's a piece that can be captured at this position
                  Pieces pieceAtPosition = getPieceAtPosition(col, row);
                  if (pieceAtPosition != null && pieceAtPosition.isWhite != selectedPiece.isWhite) {
                     int circleSize = 93; // Adjust this value to change the size of the circle
                     g2d.setColor(new Color(119, 116, 116, 90));
                     float strokeWidth = 7.0f; // Adjust this value to change the border weight
                     g2d.setStroke(new BasicStroke(strokeWidth));
                     g2d.drawOval(x - circleSize / 2, y - circleSize / 2, circleSize, circleSize);
                  } else {
                     int dotSize = 134 / 4; // Adjust this value to change the size of the dot
                     g2d.setColor(new Color(119, 116, 116, 90));
                     g2d.fillOval(x - dotSize / 2, y - dotSize / 2, dotSize, dotSize);
                  }
               }
            }
         }
      }

      // Draw all pieces except the selected piece
      for (Pieces piece : pieces) {
         if (piece != selectedPiece) {
            piece.draw(g2d);
         }
      }

      // Draw the selected piece
      if (selectedPiece != null) {
         selectedPiece.draw(g2d);
      }

   }

   private Pieces getPieceAtPosition(int col, int row) {
      for (Pieces piece : pieces) {
         if (piece.col == col && piece.row == row) {
            return piece;
         }
      }
      return null;
   }
}
