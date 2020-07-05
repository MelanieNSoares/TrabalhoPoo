package trabalho.table;
import java.util.ArrayList;
import trabalho.pieces.*;
import trabalho.table.Levels.*;

public class Table implements ITable{

  Randomizer randomizer = new Randomizer();
  public ArrayList<Piece[]> vTable = new ArrayList<Piece[]>();
  public int colors[];
  
  public int setTable(int level){

    int x,y, i = 0;
    colors = new int[5];
    int count = 0;

    for(i=0; i<10; i++){
      vTable.add(new Piece[9]);
    }

    CSVTableReader csv = new CSVTableReader();
    String commands[] = null;
    
    
	  if(level == 1){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table1.csv");
    }
    else if(level == 2){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table2.csv");
    }
    else if(level == 3){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table3.csv");
    }
    else if(level == 4){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table4.csv");
    }
    else if(level == 5){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table5.csv");
    }
    else if(level == 6){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table6.csv");
    }
    else if(level == 7){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table7.csv");
    }
    else if(level == 8){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table8.csv");
    }
    else if(level == 9){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table9.csv");
    }
    else if(level == 10){
      csv.setDataSource("/home/msface96/trabalho/trabalho/table/Levels/Table10.csv");
    }
    
    commands = csv.requestCommands();
    i = 0;
    Piece piece = randomizer.getPiece();
    while(i < commands.length){
        if(commands[i].equals("switch")){
            piece = randomizer.getPiece();
            while(colors[piece.register] != 0){
              piece = randomizer.getPiece();
            }
            i++;
            continue;
        }
        
        x = (int)commands[i].charAt(0) - 48;
        y = (int)commands[i].charAt(1) - 48;
        
        (vTable.get(x))[y] = piece;
        colors[piece.register]++;
        i++;
        count++;
        }
        System.out.println("initial count is: " + count);

    return count;

        
      
 }
  
  public void updateTable(int level, int moves){
    if(level <= 5 && moves % 6 == 0){
      //update vTable
      int length = vTable.size() - 1;
      vTable.remove(length);
      if(GameOver()){
          //game over;
      }
      
    }
  }
       
  public boolean GameOver(){
      int length = vTable.size();
      for(int j = 0; j < 9; j++){
          if(vTable.get(length-1)[j] != null){
              return true;
          }
        }
      return false;
  }
       
  public void showTable(){
    for(int i = 0; i < vTable.size(); i++){
      for(int j= 0; j < 9; j++){
        if(vTable.get(i)[j]!= null){
            System.out.print(vTable.get(i)[j].register +" ");
        }
        else{
          System.out.print("* ");
        }

      }
      System.out.print("\n");
    }

  }

  public int checkDangling(){
    int deleted = 0;
    int temp;

    for(int i=0; i< vTable.size(); i++){
      for(int j = 0; j < 9; j++){
        if(vTable.get(i)[j] != null){
          temp = vTable.get(i)[j].register;
          if(i != 0 && (i-1 < 0 || vTable.get(i-1)[j] == null) && (j-1 < 0 || vTable.get(i)[j-1] == null) && (j+1 > 8 || vTable.get(i)[j+1] == null)){
            colors[temp]--;
            deleted++;
            vTable.get(i)[j] = null;
            System.out.println("dangling piece 1");
          }
        }
      }
    }
    return deleted;
  }
}

