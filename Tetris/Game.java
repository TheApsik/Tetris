package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends TetrisFrame implements KeyListener {
    public Game(int Height, int Width, int size){
        super (Height, Width, size);

        Runnable start = new Runnable() {
            Block block = new Block();
            @Override
            public void run(){
                spawnNewBlock(block);
                int wait = 1000;
                long time = 0;
                while(true) {
                    if (time < System.currentTimeMillis()) {
                        time = System.currentTimeMillis() + wait;
                        moveDown(block);
                    }
                }
            }
        };
        new Thread(start, "Start").start();
    }
    private void putBlock(Block block){
        int [][] net = block.figure.getNet();
        for(int x=0; x<net.length; x++){
            for(int y=0; y<net[x].length; y++){
                int posX = block.x + x;
                int posY = block.y - net[x].length + y;

                if(posY > -1 && posY<height && posX>-1 && posX < width && net[x][y] == 1)
                    areas[posY][posX].isBlock = true;
            }
        }
    }

    private void spawnNewBlock(Block block){
        block.figure = Figure.randomFigure();
        block.y = 0;
        block.x = width/2-1;
        setBlock(block);
    }

    private void moveDown(Block block){
        clearBlock(block);
        int [][] net = block.figure.getNet();
        for(int x=0; x<net.length; x++) {
            for (int y = 0; y < net[x].length; y++) {
                int posX = block.x + x;
                int posY = block.y + 1 - net[x].length + y;
                if((posY > -1 && posY<height && areas[posY][posX].isBlock && net[x][y] == 1) || height<=posY) {
                    setBlock(block);
                    putBlock(block);
                    spawnNewBlock(block);
                    System.out.println("kolizja");
                    return;
                }
            }
        }

        block.y++;
        setBlock(block);
    }

    public static void main(String... arg) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game frame = new Game(15,10,20);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
