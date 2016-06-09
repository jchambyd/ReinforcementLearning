/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;


import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.Random;


class State
{
    int pnX, pnY;
    
    public State(int tnX, int tnY)
    {
        this.pnX = tnX;
        this.pnY = tnY;
    }
}


/**
 *
 * @author jorge
 */
public class MainInterface extends javax.swing.JFrame {

    private Graphics2D poGraphics;
    private Line2D poLineBuffer;
    
    private int pnNumRows;
    private int pnNumColumns;
    
    private int[][] poR;
    private float[][][] poQ;
    private State poInitialState = new State(3, 0);
    private State poFinalState = new State(3, 11);
    
    
    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGTH = 3;
    
    int paMoveX [] = {-1, 1, 0, 0};
    int paMoveY [] = {0, 0, -1, 1};    
    
    private float pnGamma = 1f;
        
    /**
     * Creates new form MainInterface
     */
    public MainInterface() {
        initComponents();
        mxInitial();
        mxQ_Learning();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTable = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlTable.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTableLayout = new javax.swing.GroupLayout(pnlTable);
        pnlTable.setLayout(pnlTableLayout);
        pnlTableLayout.setHorizontalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
        pnlTableLayout.setVerticalGroup(
            pnlTableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(286, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainInterface().setVisible(true);
            }
        });
    }
    
    
    void mxInitial()
    {
        this.pnNumRows = 4;
        this.pnNumColumns = 12;
        
        this.poR = new int[this.pnNumRows][this.pnNumColumns];
        this.poQ = new float[this.pnNumRows][this.pnNumColumns][4];
        
        
        for(int i = 0; i < pnNumRows; i++)
            for(int j = 0; j < pnNumColumns; j++)
                poR[i][j] = -1;                
        
        for(int i = 1; i < 10; i++)
            poR[3][i] = -100;
        
        for(int i = 0; i < pnNumRows; i++)
            for(int j = 0; j < pnNumColumns; j++)
                for(int k = 0; k < 4; k++)
                    poQ[i][j][k] = 0f;
                
    }
    
    void mxQ_Learning()
    {
        State loStateTmp = this.poInitialState;
        Random loRandom = new Random();
        int lnAction = 0, lnCont = 0;    
        
        
        for(int i = 0 ; i < 100; i++)
        {
            System.out.println("Episodio: " + i);
            
            lnCont = 0;
            
            loStateTmp  = this.poInitialState;
            
            while((loStateTmp.pnX != this.poFinalState.pnX || loStateTmp.pnY != this.poFinalState.pnY) && lnCont < 100)
            {   
                lnCont++;

                System.out.println(lnCont);

                mxPrint(loStateTmp);

                mxPrintQ();

                if(lnCont % 2 == 0)
                    lnAction = loRandom.nextInt(4);
                else
                    lnAction = mxGetMaxAction(loStateTmp);

                State loNextState = getNextState(loStateTmp, lnAction);

                this.poQ[loStateTmp.pnX][loStateTmp.pnY][lnAction] += (1f /(float)lnCont) * (poR[loNextState.pnX][loNextState.pnY] + pnGamma * (mxGetQ_Value(loNextState, mxGetMaxAction(loNextState))) - mxGetQ_Value(loStateTmp, lnAction));

                loStateTmp = loNextState;
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
        State loStateTmp = null;
        int lnX = toState.pnX + this.paMoveX[tnAction];
        int lnY = toState.pnY + this.paMoveY[tnAction];
        
        if(lnX < 0 || lnY < 0 || lnX >= this.pnNumRows || lnY >= this.pnNumColumns)
        {
            loStateTmp = toState;          
        }
        else if(mxIsInvalid(lnX, lnY))
        {
            loStateTmp = this.poInitialState;
        }
        else
            loStateTmp = new State(lnX, lnY);
        
        return loStateTmp;        
    }
    
    private int mxGetMaxAction(State toState) 
    {
        int lnX, lnY;
        State loStateTmp;
        int lnBestAction = 0;
        float lnBestQValue = -9999999f;
        
        for(int i = 0; i < 4; i++)
        {
            lnX = toState.pnX + this.paMoveX[i];
            lnY = toState.pnY + this.paMoveY[i];
            
            if(lnX < 0 || lnY < 0 || lnX >= this.pnNumRows || lnY >= this.pnNumColumns)
                continue;
            if(mxIsInvalid(lnX, lnY))
                continue;
            
            if(lnBestQValue < mxGetQ_Value(toState, i))
            {
                lnBestAction = i;
                lnBestQValue = mxGetQ_Value(toState, i);                
            }
        }
        return lnBestAction;
    }
    
    private boolean mxIsInvalid(int tnX, int tnY)
    {   
        return (this.poR[tnX][tnY]) == -100;
    }
    
    //Drawn
    void mxDrawTable()
    {
        if(this.poGraphics == null)
        {
            this.poGraphics = (Graphics2D) this.pnlTable.getGraphics();
        }        
        
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlTable;
    // End of variables declaration//GEN-END:variables
}
