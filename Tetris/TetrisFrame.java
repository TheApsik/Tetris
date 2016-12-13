package Tetris;

import javax.swing.*;
import java.awt.*;

public class TetrisFrame extends JFrame {
    protected Area[][] areas;
    protected int height;
    protected int width;

    public TetrisFrame(int Height, int Width, int size){
        setLayout(new GridLayout(Height,Width));

        width = Width;
        height = Height;

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
        for(int x=0; x<net.length; x++){
            for(int y=0; y<net[x].length; y++){
                int posX = block.x + x;
                int posY = block.y - net[x].length + y;

                if(posY > -1 && posY<height && posX>-1 && posX < width && net[x][y] == 1)
                    areas[posY][posX].setColor(block.figure.getColor());
            }
        }
    }

    public void clearBlock(Block block){
        int [][] net = block.figure.getNet();
        for(int x=0; x<net.length; x++){
            for(int y=0; y<net[x].length; y++){
                int posX = block.x + x;
                int posY = block.y - net[x].length + y;

                if(posY > -1 && posY<height && posX>-1 && posX < width && net[x][y] == 1)
                    areas[posY][posX].setDefaultColor();
            }
        }
    }
}
