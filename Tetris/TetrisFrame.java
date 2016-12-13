package Tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisFrame extends JFrame {
    private Area[][] areas;

    public TetrisFrame(int Height, int Width, int size){
        setLayout(new GridLayout(Height,Width));

        areas = new Area[Height][Width];
        for(int h= 0; h <Height; h++){
            for(int w = 0; w<Width; w++){
                areas[h][w] = new Area(h, w, size);
                add(areas[h][w]);
            }
        }
        pack();
    }

    public void setBlock(Block block){
        int [][] net = block.figure.getNet();
    }
}
