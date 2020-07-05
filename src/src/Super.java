package trabalho.pieces;

import trabalho.scoreboard.*;
import trabalho.table.*;

public class Super extends Piece {

    public Super(){
        register = 5;
    }

    public void calculateMovement(Table table, int position,Scoreboard scoreboard){
        int i = table.vTable.size()- 1;
        if(table.vTable.size() == 1 || table.vTable.get(i-1)[position] !=null){
            table.vTable.get(i)[position] = this;
            return;
        }

        while((i-1) >= 0 && table.vTable.get(i-1)[position] == null ){
            i--;
        }

        table.vTable.get(i)[position] = this;
        scoreboard.updateCount(-1);

        int deleted = deletePieces(i, position, table);

        scoreboard.updatePoints(deleted * 3);
        scoreboard.updateCount(deleted);

    }

    public int deletePieces(int i, int j, Table table){
        int deleted = 0;
        
        if(i-1 > -1 && table.vTable.get(i-1)[j] != null)
        {table.colors[table.vTable.get(i-1)[j].register]--;table.vTable.get(i-1)[j] = null;deleted++;}
        
        if(j-1 > -1 && table.vTable.get(i)[j-1] !=null){
            table.colors[table.vTable.get(i)[j-1].register]--;table.vTable.get(i)[j-1] = null; deleted++;}
        
        if(j+1 < 9 && table.vTable.get(i)[j+1] != null){
            table.colors[table.vTable.get(i)[j+1].register]--;table.vTable.get(i)[j+1] = null; deleted++;}
    
        table.vTable.get(i)[j] = null;
        deleted++;

        return deleted;
    }
    
}