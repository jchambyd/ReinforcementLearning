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
    
    public void mxMarkState(State toState, Graphics toGraphics)
    {
        Graphics2D loGraphics = (Graphics2D)toGraphics;
        int lnX = toState.pnY * this.pnLenSquare, lnY = toState.pnX * this.pnLenSquare;
        
        loGraphics.setColor (Color.blue);
        
        loGraphics.fillOval(lnX + this.pnBorder + this.pnLenSquare / 2 - 10,
                            lnY + this.pnBorder + this.pnLenSquare / 2 - 10,
                            20,
                            20);        
    }
    public void mxMarkGoal(State toState, Graphics toGraphics)
    {
        Graphics2D loGraphics = (Graphics2D)toGraphics;
        int lnX = toState.pnY * this.pnLenSquare, lnY = toState.pnX * this.pnLenSquare;
        
        loGraphics.setColor (Color.red);
        
        loGraphics.fillRect(lnX + this.pnBorder,
                            lnY + this.pnBorder, 
                            this.pnLenSquare, 
                            this.pnLenSquare);
        
        loGraphics.setColor (Color.black);        
        loGraphics.setStroke(new BasicStroke(2));        
        
        loGraphics.drawRect(lnX + this.pnBorder,
                            lnY + this.pnBorder, 
                            this.pnLenSquare, 
                            this.pnLenSquare);
    }
    
    public void mxMarkRisk(State toState, Graphics toGraphics)
    {
        Graphics2D loGraphics = (Graphics2D)toGraphics;
        int lnX = toState.pnY * this.pnLenSquare, lnY = toState.pnX * this.pnLenSquare;
        
        loGraphics.setColor (Color.blue);
        
        loGraphics.fillRect(lnX + this.pnBorder,
                            lnY + this.pnBorder, 
                            this.pnLenSquare, 
                            this.pnLenSquare);
        
        loGraphics.setColor (Color.black);        
        loGraphics.setStroke(new BasicStroke(2));        
        
        loGraphics.drawRect(lnX + this.pnBorder,
                            lnY + this.pnBorder, 
                            this.pnLenSquare, 
                            this.pnLenSquare);
    }
    
    private void mxDrawPart(int tnX, int tnY, int tnColor, int tnPos, Graphics2D loGraphics)
    {
        Color loColor = Color.white;
        int lnX1, lnX2, lnY1, lnY2;
        
        switch(tnColor)
        {
            case 0:
                loColor = Color.white;
                break;
            case 1:
                loColor = Color.green;
                break;
            case 2:
                loColor = Color.gray;
                break;
        }
        
        lnX1 = tnX + this.pnBorder;
        lnX2 = tnX + this.pnBorder;
        lnY1 = tnY + this.pnBorder;
        lnY2 = tnY + this.pnBorder;
        
        switch(tnPos)
        {
            case 0:
                lnX2 += this.pnLenSquare;
                break;
            case 1:
                lnX2 += this.pnLenSquare;
                lnY1 += this.pnLenSquare;
                lnY2 += this.pnLenSquare;
                break;
            case 2:
                lnY2 += this.pnLenSquare;
                break;
            case 3:
                lnX1 += this.pnLenSquare;
                lnX2 += this.pnLenSquare;
                lnY2 += this.pnLenSquare;
                break;
        }
        
        int [] laVectorX = {lnX1, 
                            lnX2, 
                            tnX + this.pnBorder + (this.pnLenSquare / 2)};
        int [] laVectorY = {lnY1,
                            lnY2, 
                            tnY + this.pnBorder + (this.pnLenSquare / 2)};
        
        loGraphics.setColor(loColor);
        loGraphics.fillPolygon(laVectorX, laVectorY, 3);
        loGraphics.setColor (Color.black);
    }
    
    
    public void mxDrawState(State toState, float tnUp, float tnDown, float tnLeft, float tnRight, Graphics2D toGraphics)
    {
        DecimalFormat loDecFor = new DecimalFormat("0.000");
        int lnX = toState.pnY * this.pnLenSquare, lnY = toState.pnX * this.pnLenSquare;
        
        float lnLow = tnUp;        
        lnLow = (lnLow >= tnDown) ? lnLow : tnDown;
        lnLow = (lnLow >= tnLeft) ? lnLow : tnLeft;
        lnLow = (lnLow >= tnRight) ? lnLow : tnRight;        
        
        
        mxDrawPart(lnX, lnY, (tnUp == 0) ? 2 : (tnUp == lnLow ? 1 : 0), 0, toGraphics);
        
        mxDrawPart(lnX, lnY, (tnDown == 0) ? 2 : (tnDown == lnLow ? 1 : 0), 1, toGraphics);

        mxDrawPart(lnX, lnY, (tnLeft == 0) ? 2 : (tnLeft == lnLow ? 1 : 0), 2, toGraphics);
        
        mxDrawPart(lnX, lnY, (tnRight == 0) ? 2 : (tnRight == lnLow ? 1 : 0), 3, toGraphics);
        
        toGraphics.setStroke(new BasicStroke(2));        
        
        toGraphics.drawRect(lnX + this.pnBorder,
                            lnY + this.pnBorder, 
                            this.pnLenSquare, 
                            this.pnLenSquare);
        
        toGraphics.setStroke(new BasicStroke(1));
                
        toGraphics.drawLine(lnX + this.pnBorder, 
                                    lnY + this.pnBorder, 
                                    lnX + this.pnBorder + this.pnLenSquare, 
                                    lnY + this.pnBorder + this.pnLenSquare);
        toGraphics.drawLine(lnX + this.pnBorder + this.pnLenSquare, 
                                    lnY + this.pnBorder, 
                                    lnX + this.pnBorder, 
                                    lnY + this.pnBorder + this.pnLenSquare);
        
        
        toGraphics.setFont(new Font("TimesRoman", Font.PLAIN, 12)); 
        
        //UP
        toGraphics.drawString(loDecFor.format(tnUp), 
                              lnX + this.pnBorder + this.pnLenSquare / 4 + 6,
                              lnY + this.pnBorder + this.pnLenSquare / 4 - 5);
        //DOWN
        toGraphics.drawString(loDecFor.format(tnDown), 
                                      lnX + this.pnBorder + this.pnLenSquare / 4 + 6,
                                      lnY + this.pnBorder + this.pnLenSquare - 12 );
        //LEFT
        toGraphics.drawString(loDecFor.format(tnLeft), 
                                      lnX + this.pnBorder + 4,
                                      lnY + this.pnBorder + this.pnLenSquare / 2 + 6);
        //RIGTH
        toGraphics.drawString(loDecFor.format(tnRight), 
                                      lnX + this.pnBorder + this.pnLenSquare / 2 + 12,
                                      lnY + this.pnBorder + this.pnLenSquare / 2 + 6);
    }
    
    public void mxDrawEnviroment(QLearning toEnviroment, Graphics toGraphics)
    {
        if(toGraphics == null)
            toGraphics = this.getGraphics();        
        
        for(int i = 0; i < toEnviroment.poR.length; i++)
        {
            for(int j = 0; j < toEnviroment.poR[0].length; j++)
            {
                if(!toEnviroment.mxIsPenhasco(i, j))
                    mxDrawState(new State(i, j), toEnviroment.poQ[i][j][0], toEnviroment.poQ[i][j][1], toEnviroment.poQ[i][j][2], toEnviroment.poQ[i][j][3], (Graphics2D)toGraphics);
                else
                    mxMarkRisk(new State(i, j), toGraphics);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        QLearning loLearning = new QLearning();
        mxDrawEnviroment(loLearning, g);
        mxMarkGoal(loLearning.poFinalState, g);        
        for(int i = 1; i < 11; i++)
            mxMarkRisk(new State(3, i), g);        
        mxMarkState(loLearning.poCurrentState, g);
    }
}
