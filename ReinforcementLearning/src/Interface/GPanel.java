/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author jorge
 */
public class GPanel extends JPanel 
{
    public GPanel() 
    {
    }
    
    
    public void mxDrawLine()
    {        
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 500;
        int y = Math.abs(r.nextInt()) % 300;
        this.getGraphics().drawLine(x, y, x, y);            
    }   
    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int lnWidth = getWidth();
        int lnHeight = getHeight();
        int lnBorder = 25;
        
        //Ejes X e Y        
        g.drawLine(lnBorder, lnBorder, lnBorder, lnHeight - lnBorder);
        g.drawLine(lnBorder, lnHeight - lnBorder, lnWidth - lnBorder, lnHeight - lnBorder);        
    }
}
