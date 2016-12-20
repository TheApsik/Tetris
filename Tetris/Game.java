package Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends TetrisFrame{
    int speed = 50;
    int normal = 300;
    int wait = normal;
    public Game(int Height, int Width, int size){
        super (Height, Width, size);

        Runnable start = new Runnable() {
            Block block = new Block();
            private boolean goLeft(){
                int [][] net = block.figure.getNet();
                for(int x=0; x<net.length; x++){
                    for(int y=0; y<net[x].length; y++){
                        int posX = block.x + x - 1;
                        int posY = block.y - net[x].length + y;
                        if(posY > -1 && posY<height && posX>-1 && posX < width && net[x][y] == 1 && getIsBlock(posX, posY) && net[x][y] == 1){
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
                        int posX = block.x + x +1;
                        int posY = block.y - net[x].length + y;
                        if(posY > -1 && posY<height && posX>-1 && posX < width && net[x][y] == 1 && getIsBlock(posX, posY)){
                            return false;
                        }
                    }
                }
                return true;
            }

            private boolean canRotate(){
                int [][] net = block.figure.getRotate();
                for(int x=0; x<net.length; x++) {
                    for (int y = 0; y < net[x].length; y++) {
                        int posX = block.x + x;
                        int posY = block.y - net[x].length + y;
                        if(posY < 0 || posY>=height){
                            continue;
                        }
                        if(posX<0 || posX >= width || (net[x][y] == 1 && getIsBlock(posX, posY))){
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
                                if(canRotate()) {
                                    clearBlock(block);
                                    block.figure.rotate();
                                    setBlock(block);
                                }
                                break;
                            case KeyEvent.VK_LEFT:
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
                                wait = speed;
                                break;


                            case KeyEvent.VK_SPACE:
                                putDown(block);
                                break;

                        }
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_DOWN:
                                wait = normal;
                                break;
                        }
                    }
                });

                spawnNewBlock(block);
                long time = 0;
                while(true) {
                    if (time+wait < System.currentTimeMillis()) {
                        time = System.currentTimeMillis();
                        moveDown(block);
                    }
                }
            }
        };
        new Thread(start, "Start").start();
    }

    private void putBlock(Block block){
        int hei = block.figure.getSizeY();

        for(int y = block.y; y<block.y - hei; y--){
            for(int i=0; i<width; i++){
                if(getIsBlock(i, y)){
                    continue;
                }
                break;
            }

        }
    }

    private void spawnNewBlock(Block block){
        block.figure = Figure.randomFigure();
        block.y = 0;
        block.x = width/2-1;
        setBlock(block);
    }

    private void slideLines(int from){
        for(;from>0; from--){
            for(int i = 0; i<width; i++) {

            }
        }
    }

    private void chackLine(Block block){
        int [][] net = block.figure.getNet();
        for(int y=0; y<net[0].length;){
            for(int x=0; x<width; x++) {
                int posY = block.y - net[0].length + y;

                if (getIsBlock(x, posY)){
                    continue;
                }
                y++;
                break;
            }
            slideLines(y);
        }
    }

    private void putDown(Block block){
        clearBlock(block);
        while (true) {
            int[][] net = block.figure.getNet();
            for (int x = 0; x < net.length; x++) {
                for (int y = 0; y < net[x].length; y++) {
                    int posX = block.x + x;
                    int posY = block.y + 1 - net[x].length + y;
                    if ((posY > -1 && posY < height && areas[posY][posX].isBlock && net[x][y] == 1) || height <= posY) {
                        setBlock(block);
                        putBlock(block);
                        spawnNewBlock(block);
                        return;
                    }
                }
            }

            block.y++;
        }
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
