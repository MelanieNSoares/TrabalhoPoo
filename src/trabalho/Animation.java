package trabalho;

import java.util.*;

import trabalho.table.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Container;


public class Animation {
    int nth;
    Container painel;
    Timer t;
    JLabel nth_Component;
    ImageIcon pieceImg;
    int i,j,aux;
    Table table;
    int times = 1;
    Bubbles bubble;



    public Animation(Container painel, JLabel nth_Component,int nth,ImageIcon pieceImg, Table table, Bubbles bubble){
        this.painel = painel;
        this.nth_Component = nth_Component;
        this.nth = nth;
        this.table = table;
        this.pieceImg = pieceImg;
        aux = 72;
        j = 0 + (nth-aux);
        i = table.vTable.size() - 2;
        this.bubble = bubble;
        t = new Timer();
        t.schedule(new AnimationTask(),0,50);
        
    }

    class AnimationTask extends TimerTask{
        public void run(){
            if(makeStop()){
                return;
            }

            System.out.println("times is " + times);
            times++;

            System.out.println("nth is" + nth);

            nth_Component.setIcon(pieceImg);
            System.out.println("set");
            nth_Component.paintAll(nth_Component.getGraphics());

            if(nth < 72){
                JLabel temp =  (JLabel)painel.getComponent(nth + 9);
                temp.setIcon(new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/set.png"));
                temp.paintAll(temp.getGraphics());
            }

            if(nth<= 9 && nth >= 1){
                bubble.Play();
                t.cancel();
                return;
            }

            nth_Component = (JLabel)painel.getComponent(nth - 9);
            nth = nth - 9;
            aux = aux -9;
            i = i - 1;


        }

        public Boolean makeStop(){
            if(i >= 0 && table.vTable.get(i)[j] == null){
                return false;
            }
            System.out.println("time to stop");
            t.cancel();
            bubble.Play();
            return true;
        }
    }
    




    

}