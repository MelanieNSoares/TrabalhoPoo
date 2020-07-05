package trabalho.pieces;

import trabalho.scoreboard.*;
import trabalho.table.*;

public class Blue extends Piece{

    public Blue(){
        register = 1;
    }
    
    public void deletePieces(Table table, Scoreboard scoreboard){        
        int deleted = 0;
        for(int i = 0; i < table.vTable.size(); i++){
            for( int j = 0; j < 9; j++){
                if (table.vTable.get(i)[j] != null && table.vTable.get(i)[j].register == 1){
                    table.vTable.get(i)[j] = null;
                    table.colors[1]--; 
                    deleted++;
                }
            }
        }
        
        scoreboard.updateCount(deleted);
        scoreboard.updatePoints(deleted * 2);
    }
    
    
}
