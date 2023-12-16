import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;

public class PlayerTimer extends JLabel implements Runnable {
   Thread timerThread;
   private int seconds;
   private boolean isPaused = false;
   private final Object lock = new Object();

   public PlayerTimer(int minutes) {
      this.seconds = minutes * 60;
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
      this.setFont(poppinsFontBold.deriveFont(30f));
      this.setText(getTime());
      timerThread = new Thread(this);
      timerThread.start();
   }

   @Override
   public void run() {
      while (seconds > 0) {
         synchronized (lock) {
            if (isPaused) {
               try {
                  lock.wait();
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
            }
         }
         try {
            Thread.sleep(1000);
         } catch (InterruptedException e) {
            e.printStackTrace();
         }
         seconds--;
         this.setText(getTime()); // Update the label text
      }
   }

   public void pauseTimer() {
      isPaused = true;
   }

   public void resumeTimer() {
      synchronized (lock) {
         isPaused = false;
         lock.notifyAll();
      }
   }

   public String getTime() {
      int minutes = this.seconds / 60;
      int seconds = this.seconds % 60;
      return String.format("%02d:%02d", minutes, seconds);
   }

   public boolean isRunning() {
      return !isPaused;
   }
}