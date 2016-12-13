package Tetris;


public class Block {
    public int x;
    public int y;
    public Figure figure;

    public Block(Figure figure, int posX, int posY){
        this.figure = figure;
        x = posX;
        y = posY;
    }
}
