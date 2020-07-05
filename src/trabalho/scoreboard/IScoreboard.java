package trabalho.scoreboard;

public interface IScoreboard {

 public void updateMoves(int moves);
 public void updatePoints(int points);
 public void updateLevel(int level);
 public void updateCount(int count);
 public int getPoints();
 public int getLevel();
 public int getCount();
 public int getMoves();
 
}