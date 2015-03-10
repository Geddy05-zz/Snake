package geddy.snake;

import java.util.Random;

/**
 * Created by geddyS on 1-3-2015.
 */
public class World {
    static final int WORLD_WIDTH = 10;
    static final int WORDL_HEIGHT = 13;
    static final int SCORE_INCREMENT = 10;
    static final float TICK_INTITAL = 0.4f;
    static final float TICK_DECREMENT = 0.05f;

    public Snake snake;
    public Mouse mouse;
    public boolean gameOver = false;
    public int score = 0;
    public boolean canMove = true;

    boolean fields[][]= new boolean[WORLD_WIDTH][WORDL_HEIGHT];
    Random random = new Random();
    float tickTime = 0;
    float tick = TICK_INTITAL;

    public World(){
        snake = new Snake();
        placeStain();
    }

    public void placeStain(){
        for  (int x =0; x< WORLD_WIDTH; x++){
            for(int y =0; y<WORDL_HEIGHT; y++){
                fields[x][y]=false;
            }
        }

        int len = snake.parts.size();
        for(int i=0; i< len ; i++){
            SnakePart part = snake.parts.get(i);
            fields[part.x][part.y] = true;
        }
        int mouseX = random.nextInt(WORLD_WIDTH);
        int mouseY = random.nextInt(WORDL_HEIGHT);
        while(true){
            if(fields[mouseX][mouseY]== false)
                break;
            mouseX+= 1;
            if (mouseX >= WORLD_WIDTH){
                mouseX =0;
                mouseY+=1;
                if(mouseY >= WORDL_HEIGHT){
                    mouseY =0;
                }
            }
        }
        mouse = new Mouse(mouseX,mouseY,random.nextInt(3));
    }

    public void update(float deltaTime){
        if(gameOver)
            return;

        tickTime+= deltaTime;

        while (tickTime>tick){
            tickTime -= tick;
            snake.advance();

            if(snake.checkBitten()){
                gameOver = true;
                return;
            }

            SnakePart head = snake.parts.get(0);
            if(head.x == mouse.x && head.y == mouse.y){
                score += SCORE_INCREMENT;
                snake.eat();
                if(snake.parts.size()==WORLD_WIDTH * WORDL_HEIGHT){
                    gameOver = true;
                    return;
                }else{
                    placeStain();
                }

                if(score %100 ==0 && tick - TICK_DECREMENT>0){
                    tick -= TICK_DECREMENT;
                }
            }else if(canMove){
                mouse.advance();
                canMove = false;
            }
            else
                canMove = true;
        }
    }
}
