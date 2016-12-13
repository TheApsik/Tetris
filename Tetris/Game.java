package Tetris;

import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String... arg) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                TetrisFrame frame = new TetrisFrame(30,20,20);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                Figure.T.rotate();
                final Block block = new Block(Figure.T,0,2);
                Block helper = new Block(Figure.I,0,0);
                frame.setBlock(block);

                Runnable start = new Runnable() {
                    @Override
                    public void run() {
                        
                    }
                }
            }
        });
    }
}
