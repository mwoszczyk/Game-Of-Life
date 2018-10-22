package pl.mwosz.GameOfLife;

import javax.swing.*;
import java.awt.*;

public class Square extends JComponent {

    private boolean fill = false;

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        super.paintComponent(g);
        g2.setColor(Color.RED);
        if (fill) {
            g2.fillRect(10,10,100,100);
        } else {
            g2.drawRect(10,10,100,100);
        }
    }

    void setFill(boolean a) {
        fill = a;
    }
}

