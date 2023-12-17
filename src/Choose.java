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

public class Choose extends JFrame {

   Choose() {
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

      rightPanel.setPreferredSize(new Dimension(500, 870));
      rightPanel.setBackground(Color.decode("#262522"));
      rightPanel.setLayout(new GridBagLayout());
      JPanel resignPanel = new JPanel();
      resignPanel.setPreferredSize(new Dimension(450, 57));
      resignPanel.setBackground(Color.decode("#262522"));
      resignPanel.setLayout(new GridBagLayout());
      JPanel drawPanel = new JPanel();
      drawPanel.setPreferredSize(new Dimension(450, 57));
      drawPanel.setBackground(Color.decode("#262522"));
      drawPanel.setLayout(new GridBagLayout());

      RoundedButton resignButton = createButton("Play", 15, 1);
      resignButton.setFont(poppinsFontBold.deriveFont(20f));
      resignButton.setPreferredSize(new Dimension(420, 55));
      resignButton.setBackground(Color.decode("#42413f"));
      resignButton.setForeground(Color.WHITE);
      resignPanel.add(resignButton);

      RoundedButton drawButton = createButton("Play with Friends", 15, 2);
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
      
      rightPanel.add(innerPanel);

      panel.setPreferredSize(new Dimension(1325, 900));
      panel.setBackground(Color.decode("#262522"));

      this.setTitle("Chess");
      panel.setLayout(new GridBagLayout());
      panel.add(rightPanel);
      this.add(panel);
      this.setLayout(new GridBagLayout());
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
