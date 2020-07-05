package trabalho;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import trabalho.canon.*;
import trabalho.scoreboard.*;
import trabalho.table.*;

public class Bubbles extends JFrame implements KeyListener {
    /**                                                                                             
     *
     */
    private static final long serialVersionUID = -5896712783488397732L;
    private Container painel;
    private JLabel points, lvl;
    private int nth, comp = 0, size = 10;
    private JLabel canon;
    private Table table;
    private Scoreboard scoreboard;
    private CanonLauncher launcher;

    public Bubbles(Table table, Scoreboard scoreboard, CanonLauncher launcher) {

        this.table = table;
        this.scoreboard = scoreboard;
        this.launcher = launcher;

        setSize(630, 630);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painel = getContentPane();
        painel.setLayout(new GridLayout(11, 9));

        for (int i = 0; i < table.vTable.size() - 1; i++) {
            for (int j = 0; j < 9; j++) {
                JLabel square = new JLabel();
                ImageIcon icon;
                if (table.vTable.get(i)[j] != null) {
                    icon = new ImageIcon(readTable(table.vTable.get(i)[j].register));
                } else {
                    icon = new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/set.png");
                }
                square.setIcon(icon);
                painel.add(square);
            }
        }// sets the jframe with the images

        for (int i = 0; i < 9; i++) {/// sets images in the line where canon moves
            JLabel square = new JLabel();
            ImageIcon icon;
            if (i == 4) {
                icon = new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/canon.png");
                nth = painel.getComponentCount();
                canon = square;
            } else {
                icon = new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/colorred.png");
            }

            square.setIcon(icon);
            painel.add(square);

        }
        
        painel.add(new JLabel("Points: "));
        points = new JLabel("0");
        painel.add(points);

        for (int i = 2; i < 7; i++) {
            JLabel square = new JLabel();
            ImageIcon icon; 
            if (i == 4 || i == 5) {
                icon = new ImageIcon(readTable(launcher.qCanon[(i == 4) ? 1 : 0].register));
            } else {
                icon = new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/set.png");
            }
            square.setIcon(icon);
            painel.add(square);
        }

        painel.add(new JLabel("LEVEL: "));
        lvl = new JLabel("1");
        painel.add(lvl);

        addKeyListener(this);

    }


    public String readTable(int register) {

        if (register == 1) {
            return "/home/msface96/trabalho/trabalho/Graphics/blue.png";
        } else if (register == 2) {
            return "/home/msface96/trabalho/trabalho/Graphics/white.png";
        } else if (register == 3) {
            return "/home/msface96/trabalho/trabalho/Graphics/purple.png";
        } else if (register == 4) {
            return "/home/msface96/trabalho/trabalho/Graphics/yellow.png";
        } else if (register == 5) {
            return "/home/msface96/trabalho/trabalho/Graphics/super.png";
        } else {
            return "/home/msface96/trabalho/trabalho/Graphics/red.png";
        }
    }

    public void changeImage() {
        if (table.vTable.size() != size) {
            size = table.vTable.size();
            comp = comp + 9;
            for (int i = comp - 9; i < comp; i++) {
                JLabel square = (JLabel) painel.getComponent(i);
                ImageIcon icon = new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/colorred.png");
                square.setIcon(icon);
                square.paintAll(square.getGraphics());
            }
        }

        int temp = comp;

        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < 9; j++) {
                JLabel square = (JLabel) painel.getComponent(temp);
                ImageIcon icon;
                if (table.vTable.get(i)[j] != null) {
                    icon = new ImageIcon(readTable(table.vTable.get(i)[j].register));
                    square.setIcon(icon);
                } else {
                    icon = new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/set.png");
                    square.setIcon(icon);
                }
                
                temp++;
            }
        }

    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            if (nth > 81) {
                moveCanon(1);
            }
        } else if (key == KeyEvent.VK_D) {
            if (nth < 89) {
                moveCanon(2);
            }
        } else if (key == KeyEvent.VK_SPACE) {
            new Animation(painel, (JLabel) painel.getComponent(nth - 9), nth - 9,new ImageIcon(readTable(launcher.qCanon[1].register)), table,this);
    
        }

    }
    
    public void keyReleased(KeyEvent arg0) {return;}

    public void keyTyped(KeyEvent arg0){return;}

    public void Play(){
        int x = 0 + (nth - 81);
        launcher.qCanon[1].calculateMovement(table,x,scoreboard);
        scoreboard.updateCount(table.checkDangling());
        scoreboard.updateMoves(1);
        
        if(scoreboard.getCount() == 0 ){ //go on to next lvl
            table.vTable.clear();
            scoreboard.updateLevel(1);
            
            if(scoreboard.getLevel()> 10){
                deletePanel();
                return;
            }
            
            scoreboard.updateCount(table.setTable(scoreboard.getLevel()) * -1);
            launcher.setCanon(table,scoreboard.getLevel());
            size = 10;
            comp = 0;
            changeCanonImage();
            changeImage();
            scoreboard.updateMoves(scoreboard.getMoves() * -1);
            lvl.setText(" " + scoreboard.getLevel());
            return;
          }
        
        table.updateTable(scoreboard.getLevel(), scoreboard.getMoves());
        changeImage();
        points.setText(" " + scoreboard.getPoints());
        launcher.updateCanon(table,scoreboard.getLevel());
        changeCanonImage();
          
        if(table.GameOver()){ // gameover
          painel.removeAll();
          painel = getContentPane();
          painel.setLayout(new GridLayout(2,1));
          JLabel gameover = new JLabel();
          gameover.setIcon(new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/gameover.png"));
          painel.add(gameover);
          JLabel points = new JLabel("Points: " + scoreboard.getPoints(),SwingConstants.CENTER);
          painel.add(points);
          painel.validate();
          return;
        }
    }

    public void deletePanel(){
        painel.removeAll();
        painel = getContentPane();
        painel.setLayout(new GridLayout(2,1));
        JLabel win = new JLabel();
        win.setIcon(new ImageIcon("/home/msface96/trabalho/trabalho/Graphics/uWin.png"));
        painel.add(win);
        JLabel points = new JLabel("Points: " + scoreboard.getPoints(),SwingConstants.CENTER);
        painel.add(points);
        painel.validate();
    }

    public void changeCanonImage(){
        JLabel launchPiece,nextPiece;
        launchPiece = (JLabel) painel.getComponent(94);
        nextPiece = (JLabel) painel.getComponent(95);
        
        ImageIcon icon1 = new ImageIcon(readTable(launcher.qCanon[1].register));
        ImageIcon icon2 = new ImageIcon(readTable(launcher.qCanon[0].register));

        launchPiece.setIcon(icon1);
        nextPiece.setIcon(icon2);
    }

    public void moveCanon(int movement){
        JLabel temp;
        ImageIcon iconCanon, iconCanonMove;
        if(movement == 1){
            temp = (JLabel) painel.getComponent(nth - 1);
            iconCanonMove = (ImageIcon) temp.getIcon();
            iconCanon = (ImageIcon) canon.getIcon();

            temp.setIcon(iconCanon);
            canon.setIcon(iconCanonMove);

            canon = temp;
            nth = nth-1;
        }
        else{
            temp = (JLabel) painel.getComponent(nth+1);
            iconCanonMove = (ImageIcon) temp.getIcon();
            iconCanon = (ImageIcon) canon.getIcon();

            temp.setIcon(iconCanon);
            canon.setIcon(iconCanonMove);

            canon = temp;
            nth = nth + 1;

        }


    }

    
    

      
    public static void main(String[] args) {

        Scoreboard scr = new Scoreboard(1,0,11,0);
        Table table = new Table();
        CanonLauncher launcher = new CanonLauncher();
        
        table.setTable(scr.getLevel());
        launcher.setCanon(table,scr.getLevel());
        

        JFrame frame = new Bubbles(table,scr,launcher);
        frame.setVisible(true);
       }
}
