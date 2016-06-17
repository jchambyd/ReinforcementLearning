/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *
 * @author jorge
 */
public class QLearning {
    private int pnNumRows;
    private int pnNumColumns;
    
    public int[][] poR;
    public float[][][] poQ;
    final private State poInitialState = new State(3, 0);
    final private State poFinalState = new State(3, 11);
    public State poCurrentState = poInitialState;
    
    final int paMoveX [] = {-1, 1, 0, 0};
    final int paMoveY [] = {0, 0, -1, 1};
    final private float pnGamma = 1f;
    
    public int pnNumIterations = 100;
    public int pnNumTrials = 1;
       
    
    public QLearning()
    {
        this.mxInitialize();
    }
    
    private void mxInitialize()
    {
        this.pnNumRows = 4;
        this.pnNumColumns = 12;
        
        this.poR = new int[this.pnNumRows][this.pnNumColumns];
        this.poQ = new float[this.pnNumRows][this.pnNumColumns][4];
        
        
        for(int i = 0; i < pnNumRows; i++)
            for(int j = 0; j < pnNumColumns; j++)
                this.poR[i][j] = -1;                
        
        for(int i = 1; i < 11; i++)
            this.poR[3][i] = -100;
        
        this.poR[this.poFinalState.pnX][this.poFinalState.pnY] = 100;
        
        for(int i = 0; i < pnNumRows; i++)
            for(int j = 0; j < pnNumColumns; j++)
                for(int k = 0; k < 4; k++)
                    poQ[i][j][k] = 0.0f;        
    }
    
    
    public void mxStartQLearning()
    {
        State loStateTmp;
        Random loRandom = new Random();
        int lnAction, lnCont;        
        
        for(int i = 0 ; i < this.pnNumTrials; i++)
        {
            lnCont = 0;
            loStateTmp  = this.poInitialState;

            while((loStateTmp.pnX != this.poFinalState.pnX || loStateTmp.pnY != this.poFinalState.pnY) && lnCont < this.pnNumIterations)
            {   
                lnCont++;
              
                if(loRandom.nextInt(this.pnNumTrials) <= i)
                    lnAction = mxGetMaxAction(loStateTmp);
                else
                    lnAction = loRandom.nextInt(4);

                State loNextState = getNextState(loStateTmp, lnAction);

                this.poQ[loStateTmp.pnX][loStateTmp.pnY][lnAction] += (1f /(float)lnCont) * (poR[loNextState.pnX][loNextState.pnY] + pnGamma * (mxGetQ_Value(loNextState, mxGetMaxAction(loNextState))) - mxGetQ_Value(loStateTmp, lnAction));

                if(mxIsPenhasco(loNextState.pnX, loNextState.pnY))
                    loNextState = this.poInitialState;
                
                loStateTmp = loNextState;
                this.poCurrentState = loStateTmp;
            }
        }
    }
    private void mxPrint(State toState)
    {
        for(int i = 0; i < pnNumRows; i++)
        {
            for(int j = 0; j < pnNumColumns; j++)
            {
                if(toState.pnX == i && toState.pnY == j)
                    System.out.print("X");
                else
                    System.out.print("0");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }
    
    private void mxPrintQ()
    {
        DecimalFormat loDecimalFormat = new DecimalFormat("0.000");        
        
        for(int i = 0; i < pnNumRows; i++)
        {
            for(int k = 0; k < 4 ; k++)
            {
                for(int j = 0; j < pnNumColumns; j++)
                {
                    System.out.print(loDecimalFormat.format(this.poQ[i][j][k]) + " ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }        
        System.out.println("\n");
    }
    
    private float mxGetQ_Value(State toState, int tnAction)
    {
        return this.poQ[toState.pnX][toState.pnY][tnAction];
    }

    private State getNextState(State toState, int tnAction) 
    {
        State loStateTmp;
        int lnX = toState.pnX + this.paMoveX[tnAction];
        int lnY = toState.pnY + this.paMoveY[tnAction];
        
        if(lnX < 0 || lnY < 0 || lnX >= this.pnNumRows || lnY >= this.pnNumColumns)
        {
            loStateTmp = toState;          
        }       
        else
            loStateTmp = new State(lnX, lnY);
        
        return loStateTmp;        
    }
    
    private int mxGetMaxAction(State toState) 
    {
        int lnBestAction = 0;
        float lnBestQValue = -9999999f;
        
        for(int i = 0; i < 4; i++)
        {        
            if(lnBestQValue < mxGetQ_Value(toState, i))
            {
                lnBestAction = i;
                lnBestQValue = mxGetQ_Value(toState, i);                
            }
        }
        return lnBestAction;
    }
    
    private boolean mxIsPenhasco(int tnX, int tnY)
    {   
        return (this.poR[tnX][tnY]) == -100;
    }
}
