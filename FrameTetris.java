import javax.swing.*;
import java.awt.*;

/**
 * Created by uczen on 29.11.2016.
 */
public class FrameTetris extends JFrame{
    private TetrisArea[][] areas;

    public FrameTetris(int Height, int Width, int size){
        setLayout(new GridLayout(Height,Width));

        areas = new TetrisArea[Height][Width];
        for(int h= 0; h <Height; h++){
            for(int w = 0; w<Width; w++){
                areas[h][w] = new TetrisArea(h,w);
                areas[h][w].setPreferredSize(new Dimension(size,size));
                add(areas[h][w]);
            }
        }
        pack();
    }

    public void setBlock(){

    }
}
