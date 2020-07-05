package trabalho.table;

public interface ITable {

 public int setTable(int level);
 public void updateTable(int level, int moves);
 public void showTable();
 public boolean GameOver();
 public int checkDangling();
 
}