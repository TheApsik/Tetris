package Tetris;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public enum Figure{
    I(new int[][]{
            {1,1,1,1}
    }, true, 1, 4, new Color(200,0,0)),
    T(new int[][]{
            {1,1,1},
            {0,1,0}
    }, true, 2,3, new Color(0,200,0)),
    O(new int[][]{
            {1,1},
            {1,1}
    }, false,2,2,  new Color(0,0,200)),
    L(new int[][]{
            {1,1,1},
            {0,0,1}
    }, true,2,3,  new Color(200,200,0)),
    J(new int[][]{
            {0,0,1},
            {1,1,1}
    }, true,2,3, new Color(0,200,200)),
    S(new int[][]{
            {1,1,0},
            {0,1,1}
    }, true,2,3, new Color(200,0,200)),
    Z(new int[][]{
            {0,1,1},
            {1,1,0}
    }, true,2,3, new Color(200, 100, 0));

    private int[][] figure;
    private Color color;
    private boolean rotate;
    private int sizeX;
    private int sizeY;

    Figure(int[][] figure, boolean rotate, int sixeX, int sizeY, Color col){
        this.figure = figure;
        this.rotate = rotate;
        this.sizeX = sixeX;
        this.sizeY = sizeY;
        this.color = col;
    }

    public void rotate(){
        if(rotate){
            int [][] rotated = new int[sizeY][sizeX];// TODO: REPAIR
            for(int y =0; y<sizeY; y++){
                for(int x= 0; x<sizeX; x++){
                    rotated[sizeX - x - 1][y] = figure[y][x];
                }
            }
            int sX= sizeX;
            sizeX = sizeY;
            sizeY = sX;
            figure = rotated;
        }
    }

    public int getSizeX(){
        return sizeX;
    }

    public Color getColor(){
        return color;
    }
    public int[][] getNet(){
        return figure;
    }

    private static final Figure[] VALUES = values();
    private static final int SIZE = VALUES.length;
    private static final Random RANDOM = new Random();

    public static Figure randomFigure()  {
        return VALUES[RANDOM.nextInt(SIZE)];
    }
}
