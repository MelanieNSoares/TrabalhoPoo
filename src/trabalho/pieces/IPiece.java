package trabalho.pieces;
import trabalho.scoreboard.*;
import trabalho.table.*;

public interface IPiece {
 public void calculateMovement(Table table,int position,Scoreboard scoreboard);
 public int checkNeighbors(int i,int j, int combo, Table table);
 public void deletePieces(Table table, Scoreboard scoreboard);
 public int deletePieces(Table table);
 public void ifCombo(int i, int j, Table table, Scoreboard scoreboard);
 public boolean checkCombo(int x1, int y1);
 }