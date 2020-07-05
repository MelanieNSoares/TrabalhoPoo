package trabalho.pieces;

import trabalho.scoreboard.*;
import trabalho.table.*;

public class White extends Piece{
    
    public White(){
        register = 2;
    }
    
    
    public void deletePieces(Table table, Scoreboard scoreboard){

        int deleted = 0;
        for(int m = 0; m<vCombo.size(); m++){ 
            int i = vCombo.get(m).x;
            int j = vCombo.get(m).y;
            
            for(int s = 0; s < 9; s++){
                if(table.vTable.get(i)[s] == null){
                    table.vTable.get(i)[s] = randomizer.getPiece();
                    table.colors[table.vTable.get(i)[s].register]++;
                    deleted++;
                }
            }
            
            table.colors[table.vTable.get(i)[j].register]--;
            table.vTable.get(i)[j] = null;
            scoreboard.updateCount(1);
    
        }

        scoreboard.updateCount(deleted * -1);
        scoreboard.updatePoints(deleted*2);

        
    
        
        
    }
    
        

                
    
    
    
}