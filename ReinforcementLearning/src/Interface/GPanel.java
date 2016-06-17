/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import Logic.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.DecimalFormat;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author jorge
 */
public class GPanel extends JPanel 
{
    final int pnLenSquare = 100;
    final int pnBorder = 10;
    
    
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
    
    public void mxMarkState(State toState)
    {
        Graphics2D loGraphics = (Graphics2D)this.getGraphics();
        int lnX = toState.pnY * this.pnLenSquare, lnY = toState.pnX * this.pnLenSquare;
        
        loGraphics.setColor (Color.blue);
        
        loGraphics.fillOval(lnX + this.pnBorder + this.pnLenSquare / 2 - 10,
                            lnY + this.pnBorder + this.pnLenSquare / 2 - 10,
                            20,
                            20);        
    }
    public void mxDrawState(State toState, float tnUp, float tnDown, float tnLeft, float tnRight)
    {
        Graphics2D loGraphics = (Graphics2D)this.getGraphics();
        
        DecimalFormat loDecFor = new DecimalFormat("0.000");
        int lnX = toState.pnY * this.pnLenSquare, lnY = toState.pnX * this.pnLenSquare;
        
        float lnLow = tnUp;        
        lnLow = (lnLow >= tnDown) ? lnLow : tnDown;
        lnLow = (lnLow >= tnLeft) ? lnLow : tnLeft;
        lnLow = (lnLow >= tnRight) ? lnLow : tnRight;        
        
        int [] laVectorX1 = {lnX + this.pnBorder, 
                             lnX + this.pnBorder + this.pnLenSquare, 
                             lnX + this.pnBorder + (this.pnLenSquare / 2)};
        int [] laVectorY1 = {lnY + this.pnBorder,
                             lnY + this.pnBorder, 
                             lnY + this.pnBorder + (this.pnLenSquare / 2)};
        
        if(lnLow == tnUp)
        {
            loGraphics.setColor(Color.GREEN);
            loGraphics.fillPolygon(laVectorX1, laVectorY1, 3);
            loGraphics.setColor (Color.black);
        }
        else
            loGraphics.drawPolygon(laVectorX1, laVectorY1, 3);
        
        int [] laVectorX2 = {lnX + this.pnBorder, 
                             lnX + this.pnBorder, 
                             lnX + this.pnBorder + (this.pnLenSquare / 2)};
        int [] laVectorY2 = {lnY + this.pnBorder,
                             lnY + this.pnBorder + this.pnLenSquare, 
                             lnY + this.pnBorder + (this.pnLenSquare / 2)};
        
        if(lnLow == tnLeft)
        {
            loGraphics.setColor(Color.GREEN);
            loGraphics.fillPolygon(laVectorX2, laVectorY2, 3);
            loGraphics.setColor (Color.black);
        }
        else
            loGraphics.drawPolygon(laVectorX2, laVectorY2, 3);
        
        int [] laVectorX3 = {lnX + this.pnBorder, 
                             lnX + this.pnBorder + this.pnLenSquare, 
                             lnX + this.pnBorder + (this.pnLenSquare / 2)};
        int [] laVectorY3 = {lnY + this.pnBorder + this.pnLenSquare,
                             lnY + this.pnBorder + this.pnLenSquare, 
                             lnY + this.pnBorder + (this.pnLenSquare / 2)};
        
        if(lnLow == tnDown)
        {
            loGraphics.setColor(Color.GREEN);
            loGraphics.fillPolygon(laVectorX3, laVectorY3, 3);
            loGraphics.setColor (Color.black);
        }
        else
            loGraphics.drawPolygon(laVectorX3, laVectorY3, 3);
        
        
        int [] laVectorX4 = {lnX + this.pnBorder + this.pnLenSquare, 
                             lnX + this.pnBorder + this.pnLenSquare, 
                             lnX + this.pnBorder + (this.pnLenSquare / 2)};
        int [] laVectorY4 = {lnY + this.pnBorder,
                             lnY + this.pnBorder + this.pnLenSquare, 
                             lnY + this.pnBorder + (this.pnLenSquare / 2)};
        
        if(lnLow == tnRight)
        {
            loGraphics.setColor(Color.GREEN);
            loGraphics.fillPolygon(laVectorX4, laVectorY4, 3);
            loGraphics.setColor (Color.black);
        }
        else
            loGraphics.drawPolygon(laVectorX4, laVectorY4, 3);
        
        
        loGraphics.setStroke(new BasicStroke(2));        
        
        loGraphics.drawRect(lnX + this.pnBorder,
                            lnY + this.pnBorder, 
                            this.pnLenSquare, 
                            this.pnLenSquare);
        
        loGraphics.setStroke(new BasicStroke(1));
                
        loGraphics.drawLine(lnX + this.pnBorder, 
                                    lnY + this.pnBorder, 
                                    lnX + this.pnBorder + this.pnLenSquare, 
                                    lnY + this.pnBorder + this.pnLenSquare);
        loGraphics.drawLine(lnX + this.pnBorder + this.pnLenSquare, 
                                    lnY + this.pnBorder, 
                                    lnX + this.pnBorder, 
                                    lnY + this.pnBorder + this.pnLenSquare);
        
        
        loGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
        
        //UP
        loGraphics.drawString(loDecFor.format(tnUp), 
                              lnX + this.pnBorder + this.pnLenSquare / 4 + 6,
                              lnY + this.pnBorder + this.pnLenSquare / 4 - 5);
        //DOWN
        loGraphics.drawString(loDecFor.format(tnDown), 
                                      lnX + this.pnBorder + this.pnLenSquare / 4 + 6,
                                      lnY + this.pnBorder + this.pnLenSquare - 12 );
        //LEFT
        loGraphics.drawString(loDecFor.format(tnLeft), 
                                      lnX + this.pnBorder + 4,
                                      lnY + this.pnBorder + this.pnLenSquare / 2 + 6);
        //RIGTH
        loGraphics.drawString(loDecFor.format(tnRight), 
                                      lnX + this.pnBorder + this.pnLenSquare / 2 + 12,
                                      lnY + this.pnBorder + this.pnLenSquare / 2 + 6);
    }
    
    public void mxDrawEnviroment(QLearning toEnviroment)
    {
        for(int i = 0; i < toEnviroment.poR.length; i++)
        {
            for(int j = 0; j < toEnviroment.poR[0].length; j++)
            {
                mxDrawState(new State(i, j), toEnviroment.poQ[i][j][0], toEnviroment.poQ[i][j][1], toEnviroment.poQ[i][j][2], toEnviroment.poQ[i][j][3]);
            }
        }                
    }    

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
