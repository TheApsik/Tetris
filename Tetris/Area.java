package Tetris;

import javax.swing.*;
import java.awt.*;


public class Area extends JPanel{
    private int x;
    private int y;

    public Area(int posY, int posX, int size){
        x = posX;
        y = posY;
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

    public void setColor(Color col){
        if(x%2==1){
            if(y%2 == 1)
                setBackground(col.darker());
            else
                setBackground(col);
        }else{
            if(y%2 == 1)
                setBackground(col);
            else
                setBackground(col.brighter());
        }
    }
}