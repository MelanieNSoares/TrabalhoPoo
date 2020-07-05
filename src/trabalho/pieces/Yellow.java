package trabalho.pieces;

import trabalho.scoreboard.*;
import trabalho.table.*;

public class Yellow extends Piece{

    public Yellow(){
        register = 4;
    }
     
    public void deletePieces(Table table,Scoreboard scoreboard){

         
            scoreboard.updateCount(vCombo.size());
            scoreboard.updatePoints(vCombo.size() * 2);

            for(int m = 0; m < vCombo.size(); m++){
            
                int i = vCombo.get(m).x;
                int j = vCombo.get(m).y;
                
                
                if(i-1>= 0 && table.vTable.get(i-1)[j] != null && table.vTable.get(i-1)[j].register != register ){
                    table.colors[table.vTable.get(i-1)[j].register]--;
                    table.vTable.get(i-1)[j] = randomizer.getPiece();
                    table.colors[table.vTable.get(i-1)[j].register]++;
                }

                if(j-1>=0 &&table.vTable.get(i)[j-1] != null && table.vTable.get(i)[j-1].register != register){
                    table.colors[table.vTable.get(i)[j-1].register]--;
                    table.vTable.get(i)[j-1] = randomizer.getPiece();
                    table.colors[table.vTable.get(i)[j-1].register]++;
                }

                if(j+1 <= 8 && table.vTable.get(i)[j+1] != null && table.vTable.get(i)[j+1].register != register){
                    table.colors[table.vTable.get(i)[j+1].register]--;
                    table.vTable.get(i)[j+1] = randomizer.getPiece();
                    table.colors[table.vTable.get(i)[j+1].register]++;
                }

                if(i+1<= table.vTable.size() && table.vTable.get(i+1)[j] != null && table.vTable.get(i+1)[j].register != register){
                    table.colors[table.vTable.get(i+1)[j].register]--;
                    table.vTable.get(i+1)[j] = randomizer.getPiece();
                    table.colors[table.vTable.get(i+1)[j].register]++;
                }


                table.colors[table.vTable.get(i)[j].register]--;
                table.vTable.get(i)[j] = null; 
                
            }
    }
        
        
}

