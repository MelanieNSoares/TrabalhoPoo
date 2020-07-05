package trabalho.pieces;

import java.util.Random;

public class Randomizer {

    Random random = new Random();
    int int_random;
    Piece rand;

    /*public Piece getPiece(int upperbound, Table table){
        ///get a rando Piece yoooooo///
          int_random = random.nextInt(upperbound);
          
          rand = interpretPiece(table.piece_in_table.get(int_random)-1);
    
        return rand;
    
      }*/

      public Piece getPiece(){
        ///get a rando Piece yoooooo///
          int_random = random.nextInt(5);
          
          rand = interpretPiece(int_random);
    
        return rand;
    
      }

      public Piece getPiece(int lvl){
        if(lvl >= 5){
          int_random = random.nextInt(6);
          rand = interpretPiece(int_random);
        }
        else{
          int_random = random.nextInt(5);
          rand = interpretPiece(int_random);
        }

        return rand;
      }

      public Piece interpretPiece(int random){
        if(random == 2){
          return new White();
        }
        else if(random ==1){
          return new Blue();
        }
        else if(random == 0){
          return new Red();
        }
        else if(random == 4){
          return new Yellow();
        }
        else if(random == 3){
          return new Purple();
        }
        if(random == 5){
          return new Super();
        }
        //if(random = 7){
          //return new Star();
        //}
        /*if(random = 0){
          return new  Green();
        }*/ 
        else{return null;}
      }  
}