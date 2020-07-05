package trabalho.pieces;

import trabalho.scoreboard.*;
import trabalho.table.*;

public class Purple extends Piece{
    
    public Purple(){
        register = 3;
    }
    
    public void deletePieces(Table table, Scoreboard scoreboard){

        for(int m = 0; m<vCombo.size(); m++){
            int i= vCombo.get(m).x;
            int j = vCombo.get(m).y;

            while(table.vTable.get(i)[j].register == 3){
                table.colors[table.vTable.get(i)[j].register]--;
                table.vTable.get(i)[j] = randomizer.getPiece();
                table.colors[table.vTable.get(i)[j].register]++;
            }
            
        }
    }

}