import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class SnakeGraphic extends JPanel implements ActionListener{
    
    static final int WIDTH = 800; 
    static final int HEIGHT = 800; 
    static final int TICK_SIZE = 50;
    static final int BOARD_SIZE = (WIDTH * HEIGHT)/(TICK_SIZE * TICK_SIZE);

    final Font font = new Font("TimesRoman", Font.BOLD, 30);

    int[] snakePosX =  new int[BOARD_SIZE];
    int[] snakePosY =  new int[BOARD_SIZE];
    int snakeLength; 

    Food food;
    int foodEaten;
    final Timer timer = new Timer(150,this);

    char direction = 'R';
    boolean ismoving = false;

    public SnakeGraphic(){
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                if (ismoving) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_LEFT:
                            if (direction!='R') {
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction!='L') {
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction!='D') {
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction!='U') {
                                direction = 'D';
                            }
                            break;
                    }
                }
                else{
                    start();
                }
            }
        });

        start();
    }

    protected void start(){
        snakePosX =  new int[BOARD_SIZE];
        snakePosY =  new int[BOARD_SIZE];
        snakeLength = 5;
        direction = 'R';
        foodEaten = 0;
        ismoving = true;
        spawnFood();
        timer.start();



    }

    @Override
    protected void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        if (ismoving) {
            g.setColor(Color.red);
            g.fillOval(food.getPosX(), food.getPosY(), TICK_SIZE, TICK_SIZE);
            g.setColor(Color.DARK_GRAY);
            for (int i = 0; i < snakeLength; i++) {
                g.fillRect(snakePosX[i], snakePosY[i], TICK_SIZE, TICK_SIZE);
                
            }
        }else{
            String score = String.format("The end..... Score: %d... Press any key to play again!",foodEaten);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(score, (WIDTH - getFontMetrics(g.getFont()).stringWidth(score))/2, HEIGHT/2);
        }
    }

    protected void move(){
        for (int i = snakeLength; i > 0; i--) {
            snakePosX[i] = snakePosX[i-1];
            snakePosY[i] = snakePosY[i-1];

            
        }
        switch (direction) {
            case 'U' -> snakePosY[0] -= TICK_SIZE;
            case 'D' -> snakePosY[0] += TICK_SIZE;
            case 'L' -> snakePosX[0] -= TICK_SIZE;
            case 'R' -> snakePosX[0] += TICK_SIZE;
        }
    }

    protected void spawnFood(){
        food = new Food();
    }

    protected void eatFood(){
        if ((snakePosX[0]==food.getPosX())&& snakePosY[0]==food.getPosY()) {
            snakeLength++;
            foodEaten++;
            spawnFood();
            
        }
    }

    protected void collisionTest(){
        for(int i = snakeLength; i>0;i--){
            if ((snakePosX[0]==snakePosX[i])&&(snakePosY[0]==snakePosY[i])) {
                ismoving = false;
                break;
                
            }
        }
        if (snakePosX[0]<0|| snakePosX[0]>WIDTH-TICK_SIZE|| snakePosY[0]<0|| snakePosY[0]>HEIGHT-TICK_SIZE) {
            ismoving= false;
            
        }

        if (!ismoving) {
            timer.stop();
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (ismoving) {
         move();
         collisionTest();
         eatFood();
       }
       repaint();
    }

    
}
