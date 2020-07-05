package trabalho.pieces;
import java.util.ArrayList;
import trabalho.table.*;
import trabalho.scoreboard.*;


public class Piece implements IPiece{
    public int register;
    Randomizer randomizer = new Randomizer();

    static ArrayList<Positions> vCombo = new ArrayList<Positions>();

    public void deletePieces(Table table, Scoreboard scoreboard){}
      
    public void calculateMovement(Table table, int position,Scoreboard scoreboard)
    {
        System.out.println("size is " + table.vTable.size());
        int i = table.vTable.size()- 1;
        if(table.vTable.size() == 1 || table.vTable.get(i-1)[position] !=null){
            table.vTable.get(i)[position] = Piece.this;
            return;
        }
        while((i-1) >= 0 && table.vTable.get(i-1)[position] == null ){
            i--;
        }

        table.vTable.get(i)[position] = Piece.this;
        table.colors[Piece.this.register]++;
        scoreboard.updateCount(-1);
        ifCombo(i, position, table, scoreboard);
    }
    
    public void ifCombo(int i, int j, Table table,Scoreboard scoreboard){
        vCombo.add(new Positions(i,j));
        int combo = checkNeighbors(i,j,1,table);

        System.out.println(combo);

        if(combo == 3){
            scoreboard.updateCount(deletePieces(table));
            scoreboard.updatePoints(3);
        }
        
        else if(combo > 3){
            deletePieces(table,scoreboard);
        }
        
        vCombo.clear();
        
    }
    
    public boolean checkCombo(int x1, int y1){
        for(int i = 0; i< vCombo.size(); i++){
            if(vCombo.get(i).x == x1 && vCombo.get(i).y == y1){
                return false;
            }
        }
        return true;
        /*while( vCombo.get(i) != null || i < 49){
            if(vCombo[i].x == x1 && vCombo[i].y == y1){
                return false;
            }
            i++;*/
    }   
      ///this goes to table
    public int checkNeighbors(int i,int j, int combo, Table table)
    {
        if(i-1>=0 && table.vTable.get(i-1)[j] != null && table.vTable.get(i-1)[j].register == register && checkCombo(i-1,j)){
            vCombo.add(new Positions(i-1,j));
            combo = checkNeighbors(i-1,j,combo+1,table);
        }
        if(j-1 >= 0 && table.vTable.get(i)[j-1] != null && table.vTable.get(i)[j-1].register == register && checkCombo(i,j-1)){
            vCombo.add(new Positions(i,j-1));
            combo=checkNeighbors(i,j-1,combo+1,table);
        }
        if(j+1<=8 && table.vTable.get(i)[j+1] != null && table.vTable.get(i)[j+1].register == register && checkCombo(i,j+1)){
            vCombo.add(new Positions(i,j+1));
            combo=checkNeighbors(i,j+1,combo+1,table);
        }

        if(i+1<= table.vTable.size() && table.vTable.get(i+1)[j] != null && table.vTable.get(i+1)[j].register == register && checkCombo(i+1,j)){
            vCombo.add(new Positions(i+1,j));
            combo=checkNeighbors(i+1,j,combo+1,table);
        }
        
        return combo;
    }
           
    public int deletePieces(Table table){
        
        int deleted = 0;
        for(int m =0; m<vCombo.size(); m++){
            table.vTable.get(vCombo.get(m).x)[vCombo.get(m).y] = null;
            deleted ++;
        }

        table.colors[Piece.this.register] = table.colors[Piece.this.register] - 3;
        return deleted;
            
    }

}
    
