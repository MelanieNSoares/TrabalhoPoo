package trabalho.canon;

import trabalho.pieces.*;
import trabalho.table.Table;



public class CanonLauncher implements ICanonLauncher{
  
  public Piece qCanon[] = new Piece[2];
  Piece temp;
  Randomizer randomizer = new Randomizer();



  public void setCanon(Table table, int lvl){
    qCanon[0] = randomizer.getPiece(lvl);
    qCanon[1] = randomizer.getPiece(lvl);

    while(qCanon[0].register < 5 && table.colors[qCanon[0].register] == 0){
      qCanon[0] = randomizer.getPiece(lvl);
    }
    while(qCanon[0].register < 5 && table.colors[qCanon[1].register] == 0){
      qCanon[1] = randomizer.getPiece(lvl);
    }
  }
  
  public void updateCanon(Table table, int lvl){

    temp = qCanon[0];
    qCanon[1] = temp;
    qCanon[0] = randomizer.getPiece(lvl);

    while(qCanon[0].register < 5 && table.colors[qCanon[0].register] == 0){
      qCanon[0] = randomizer.getPiece(lvl);
    }

  }

  public void showCanon(){
    for(int i = 0; i < 2; i++){
      System.out.print(qCanon[i].register + " ");

    }
    System.out.print("\n");
  }
 
}