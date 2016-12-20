package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends TetrisFrame{
    public Game(int Height, int Width, int size){
        super (Height, Width, size);

        Runnable start = new Runnable() {
            Block block = new Block();
            private boolean goLeft(){
                int [][] net = block.figure.getNet();
                for(int x=0; x<net.length; x++){
                    for(int y=0; y<net[x].length; y++){
                        if(getIsBlock(block.x + x -1, block.y - net[x].length + y) && net[x][y] == 1){
                            return false;
                        }
                    }
                }
                return true;
            }

            private boolean goRight(){
                int [][] net = block.figure.getNet();
                for(int x=0; x<net.length; x++){
                    for(int y=0; y<net[x].length; y++){
                        if(getIsBlock(block.x + x +1, block.y - net[x].length + y) && net[x][y] == 1){
                            return false;
                        }
                    }
                }
                return true;
            }
            @Override
            public void run(){
                addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {}

                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                                clearBlock(block);
                                block.figure.rotate();
                                setBlock(block);
                                break;
                            case KeyEvent.VK_LEFT:
                                goLeft();
                                if(block.x > 0 && goLeft()) {
                                    clearBlock(block);
                                    block.x--;
                                    setBlock(block);
                                }
                                break;
                            case KeyEvent.VK_RIGHT:
                                if(block.x+block.figure.getSizeX() < width && goRight()) {
                                    clearBlock(block);
                                    block.x++;
                                    setBlock(block);
                                }
                                break;

                            case KeyEvent.VK_DOWN:
                                moveDown(block);
                                break;


                            case KeyEvent.VK_SPACE:
                                setBlock(block);
                                putBlock(block);
                                spawnNewBlock(block);
                                break;

                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {}
                });

                spawnNewBlock(block);
                int wait = 300;
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
