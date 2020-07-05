package trabalho.scoreboard;

public class Scoreboard implements IScoreboard{

  private int level, points, count, moves;

  public Scoreboard(int level, int points, int count, int moves){
    this.level = level;
    this.points = points;
    this.count = count;  
    this.moves = moves; 
  }

  public void updatePoints(int points){
    this.points = this.points + points;
  }
  public void updateLevel(int level){
    this.level = this.level + level;
  }
  public void updateCount(int count){
    this.count = this.count - count;
  }
  public void updateMoves(int moves){
    this.moves = this.moves + moves;
  }
  public int getPoints(){
    return points;
  }
  public int getLevel(){
    return level;
  }
  public int getCount(){
    return count;
  }
  public int getMoves(){
    return moves; 
  }
}