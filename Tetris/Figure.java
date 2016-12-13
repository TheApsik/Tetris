package Tetris;

import java.awt.*;

public enum Figure{
    I(new int[][]{
            {1,1,1,1}
    }, true, 4,1, new Color(200,0,0)),
    T(new int[][]{
            {1,1,1},
            {0,1,0}
    }, true,3,2, new Color(0,200,0)),
    O(new int[][]{
            {1,1},
            {1,1}
    }, false,2,2,  new Color(0,0,200)),
    L(new int[][]{
            {1,1,1},
            {0,0,1}
    }, true,3,2,  new Color(200,200,0)),
    J(new int[][]{
            {0,0,1},
            {1,1,1}
    }, true,3,2, new Color(0,200,200)),
    S(new int[][]{
            {1,1,0},
            {0,1,1}
    }, true,3,2, new Color(200,0,200)),
    Z(new int[][]{
            {0,1,1},
            {1,1,0}
    }, true,3,2, new Color(200, 100, 0));

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
            int [][] rotated = new int[sizeX][sizeY];
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

    public Color getColor(){
        return color;
    }
    public int[][] getNet(){
        return figure;
    }
}
