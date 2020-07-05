package trabalho.pieces;

import trabalho.scoreboard.*;
import trabalho.table.*;

public class Red extends Piece{
    
    public Red(){
        register = 0;
    }
        
    public void deletePieces(Table table, Scoreboard scoreboard){
      
        for(int m = 0; m < vCombo.size(); m++){
            
            int deleted = 0;
            
            int i = vCombo.get(m).x;
            int j = vCombo.get(m).y;
            
            
            if(i-1>= 0 && table.vTable.get(i-1)[j] != null && table.vTable.get(i-1)[j].register != register && table.vTable.get(i-1)[j].register != 1){
                table.colors[table.vTable.get(i-1)[j].register]--;
                table.vTable.get(i-1)[j] = null;
                deleted++;
            }
            
            if(j-1 >= 0 && table.vTable.get(i)[j-1] != null && table.vTable.get(i)[j-1].register != register && table.vTable.get(i)[j-1].register != 1){
                table.colors[table.vTable.get(i)[j-1].register]--;
                table.vTable.get(i)[j-1] = null;
                deleted++;
            }
            
            if(j+1 <= 8 && table.vTable.get(i)[j+1] != null && table.vTable.get(i)[j+1].register != register && table.vTable.get(i)[j+1].register != 1){
                table.colors[table.vTable.get(i)[j+1].register]--;
                table.vTable.get(i)[j+1] = null;
                deleted++;
            }
            
            if(i+1<= table.vTable.size() && table.vTable.get(i+1)[j] != null && table.vTable.get(i+1)[j].register != register && table.vTable.get(i+1)[j].register != 1){
                table.colors[table.vTable.get(i+1)[j].register]--;
                table.vTable.get(i+1)[j] = null;
                deleted++;
            }

            
            
            table.colors[table.vTable.get(i)[j].register]--;
            table.vTable.get(i)[j] = null; 
            deleted++;
            
            scoreboard.updateCount(deleted);
            scoreboard.updatePoints(deleted * 2);
            
}
        
    
    }


}