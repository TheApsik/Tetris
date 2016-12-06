import javax.swing.*;
import java.awt.*;

/**
 * Created by uczen on 29.11.2016.
 */
public class TetrisArea extends JPanel{
    private int x;
    private int y;

    public TetrisArea(int posY, int posX){
        x = posX;
        y = posY;
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
}
