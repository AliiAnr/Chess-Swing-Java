import java.util.Random;

public class Food {

    private final int posX;
    private final int posY;

    public Food(){
        
        posX = generatePos(SnakeGraphic.WIDTH);
        posY = generatePos(SnakeGraphic.HEIGHT);

    }

    private int generatePos(int size){
        Random random = new Random();
        return random.nextInt(size / SnakeGraphic.TICK_SIZE)* SnakeGraphic.TICK_SIZE;
    }

    public int getPosX() {
        return posX;
    
    }

    public int getPosY(){
        return posY;
    }

    
}
