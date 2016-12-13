package Tetris;

import javax.swing.*;
import java.awt.*;


public class Area extends JPanel{
    private int x;
    private int y;
    private boolean isBlock;

    public Area(int posY, int posX, int size){
        x = posX;
        y = posY;
        isBlock = false;
        setPreferredSize(new Dimension(size, size));
        setDefaultColor();
    }

    public void setDefaultColor(){
        if(x%2==1){
            if(y%2 == 1)
                setBackground(new Color(100,100,100));
            else
                setBackground(new Color(110,110,110));
        }else{
            if(y%2 == 1)
                setBackground(new Color(110,110,110));
            else
                setBackground(new Color(120,120,120));
        }
    }

    private int range(int value){
        if (value < 0){
            return 0;
        }
        if(value > 255){
            return 255;
        }
        return value;
    }

    public void setColor(Color col){
        int red = col.getRed();
        int green = col.getGreen();
        int blue = col.getBlue();

        if(x%2==1){
            if(y%2 == 1)
                setBackground(new Color(range(red-20), range(green -20), range(blue-20)));
            else
                setBackground(col);
        }else{
            if(y%2 == 1)
                setBackground(col);
            else
                setBackground(new Color(range(red+20), range(green +20), range(blue+20)));
        }
    }
}