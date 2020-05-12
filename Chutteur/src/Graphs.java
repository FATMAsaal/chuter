import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.RenderingHints;
import java.util.List;
import java.lang.*;

import javax.swing.JComponent;

public class Graphs extends JComponent {
    private static final long serialVersionUID = 1L;
    int PAD = 20;

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        g2.drawLine(PAD, PAD, PAD, h-PAD);
        g2.drawLine(PAD, h-PAD, w-PAD, h-PAD);

        double xScale = (w - 2*PAD)/(ChutteurApp.SOUND.size() + 1);
        double maxValue = 1000.0;
        double yScale = (h - 2*PAD)/maxValue;

        int x0 = PAD;
        int y0 = h-PAD;

        if(ChutteurApp.SELECTLIGHT){
            for(int j = 0; j < ChutteurApp.LIGHT.size() - 1; j++) {
                int x1 = x0 + (int)(xScale * (j+1));
                int y1 = y0 - (int)(yScale * ChutteurApp.LIGHT.get(j));
                
                int x2 = x0 + (int)(xScale * (j+2));
                int y2 = y0 - (int)(yScale * ChutteurApp.LIGHT.get(j+1));
                
                g2.setPaint(Color.black);
                g2.drawLine(x1,y1,x2,y2);
                g2.setPaint(Color.red);
                g2.fillOval(x1-2,y1-2,4,4);
                g2.fillOval(x2-2,y2-2,4,4);
            }
        }

        if(ChutteurApp.SELECTTEMP){
            for(int j = 0; j < ChutteurApp.CELSIUS.size() - 1; j++) {
                int x1 = x0 + (int)(xScale * (j+1));
                int y1 = y0 - (int)(yScale * ChutteurApp.CELSIUS.get(j));
                
                int x2 = x0 + (int)(xScale * (j+2));
                int y2 = y0 - (int)(yScale * ChutteurApp.CELSIUS.get(j+1));
                
                g2.setPaint(Color.black);
                g2.drawLine(x1,y1,x2,y2);
                g2.setPaint(Color.red);
                g2.fillOval(x1-2,y1-2,4,4);
                g2.fillOval(x2-2,y2-2,4,4);
            }
        }

        if(ChutteurApp.SELECTHUMIDITY){
            for(int j = 0; j < ChutteurApp.HUMIDITY.size() - 1; j++) {
                int x1 = x0 + (int)(xScale * (j+1));
                int y1 = y0 - (int)(yScale * ChutteurApp.HUMIDITY.get(j));
                
                int x2 = x0 + (int)(xScale * (j+2));
                int y2 = y0 - (int)(yScale * ChutteurApp.HUMIDITY.get(j+1));
                
                g2.setPaint(Color.black);
                g2.drawLine(x1,y1,x2,y2);
                g2.setPaint(Color.red);
                g2.fillOval(x1-2,y1-2,4,4);
                g2.fillOval(x2-2,y2-2,4,4);
            }
        }

        for(int j = 0; j < ChutteurApp.SOUND.size() - 1; j++) {
            int x1 = x0 + (int)(xScale * (j+1));
            int y1 = y0 - (int)(yScale * ChutteurApp.SOUND.get(j));
            
            int x2 = x0 + (int)(xScale * (j+2));
            int y2 = y0 - (int)(yScale * ChutteurApp.SOUND.get(j+1));
            
            g2.setPaint(Color.black);
            g2.drawLine(x1,y1,x2,y2);
            g2.setPaint(Color.red);
            g2.fillOval(x1-2,y1-2,4,4);
            g2.fillOval(x2-2,y2-2,4,4);
        }
    }
}