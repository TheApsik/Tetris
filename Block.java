import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by uczen on 29.11.2016.
 */
public class Block {
    int x;
    int y;

    public enum Figure{
        I(new int[][]{
                {1,1,1,1}
        }, true, 4,1),
        T(new int[][]{
                {1,1,1},
                {0,1,0}
        }, true,3,2),
        O(new int[][]{
                {1,1},
                {1,1}
        }, false,2,2),
        L(new int[][]{
                {1,1,1},
                {0,0,1}
        }, true,3,2),
        J(new int[][]{
                {0,0,1},
                {1,1,1}
        }, true,3,2),
        S(new int[][]{
                {1,1,0},
                {0,1,1}
        }, true,3,2),
        Z(new int[][]{
                {0,1,1},
                {1,1,0}
        }, true,3,2);

        int[][] figure;
        boolean rotate;
        int sizeX;
        int sizeY;
        Figure(int[][] figure, boolean rotate, int sixeX, int sizeY){
            this.figure = figure;
            this.rotate = rotate;
            this.sizeX = sixeX;
            this.sizeY = sizeY;
        }

        public void rotate(){
            System.out.println(rotate);
            if(rotate){
                int [][] rotated = new int[sizeY][sizeX];
                for(int y =0; y<sizeY; y++){
                    for(int x= 0; x<sizeX; x++){
                        rotated[y][x] = figure[x][y];
                    }
                }
                System.out.println(Arrays.deepToString(rotated));
                figure = rotated;
            }
        }

        public void getWalue(){
            System.out.println(Arrays.deepToString(this.figure));
            System.out.println(this.rotate);
        }

    }

    public Block(){

    }
}
