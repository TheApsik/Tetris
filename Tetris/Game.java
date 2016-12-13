package Tetris;

import javax.swing.*;
import java.awt.*;

public class Game extends TetrisFrame {
    public Game(int Height, int Width, int size){
        super (Height, Width, size);

        Runnable start = new Runnable() {
            Block block = new Block(Figure.T,0,7);
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
                int wait = 1000;
                long time = 0;
                while(time < System.currentTimeMillis()) {
                    System.out.print
                    time = System.currentTimeMillis() + wait;
                    moveDown(block);
                }
            }
        };
        new Thread(start).start();
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

    private void moveDown(Block block){
        System.out.println("dziala");
        clearBlock(block);
        int [][] net = block.figure.getNet();
        block.y++;
        for(int x=0; x<net.length; x++) {
            for (int y = 0; y < net[x].length; y++) {
                int posX = block.x + x;
                int posY = block.y - net[x].length + y;

                if(height<block.y){
                    block.y--;
                    setBlock(block);
                    putBlock(block);
                    block.y = 0;
                }

                if(areas[posY][posX].isBlock && net[x][y] == 1) {
                    block.y--;
                    System.out.print("kolizja");
                }

                setBlock(block);
            }
        }
    }

    public static void main(String... arg) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game frame = new Game(30,20,20);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
