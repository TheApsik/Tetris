package Tetris;

import javax.swing.*;
import java.awt.*;

public class Game {
    private TetrisFrame frame;

    public static void main(String... arg) {
        final TetrisFrame frame = new TetrisFrame(30,20,20);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

        Block block = new Block(0,0);
        Block helper = new Block(0,0);
    }
}
