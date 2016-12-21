package Tetris;


public class Block {
    public int x;
    public int y;
    public Figure figure;

    public Block(){
        this.figure = Figure.O;
        x = 0;
        y = 0;
    }
}
