package geddy.snake;

import java.util.Random;

/**
 * Created by geddyS on 1-3-2015.
 */
public class Mouse {

    public static final int TYPE_1 = 0;
    public static final int TYPE_2 = 1;
    public static final int Type_3= 2;
    public int x,y;
    public int type;

    public Mouse(int x, int y, int type){
        this.x=x;
        this.y=y;
        this.type = type;
    }

    // need to implement:  check if there is a snakePart on the same x,y location
    public void advance(){
        Random random = new Random();
        int movement =random.nextInt(6);

        if (movement == 0)
            x-=1;
        if (movement == 1)
            y -=1;
        if (movement == 2)
            x +=1;
        if (movement == 3)
            y+= 1;

        if(x <0)
            x =9;
        if (x>9)
            x=0;
        if (y <0)
            y =12;
        if (y> 12)
            y =0;
    }
}
