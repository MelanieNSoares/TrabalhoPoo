package trabalho.table.Levels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVTableReader {
	private String dataSource;
	private String[] commands;
  
  public CSVTableReader() {
    this.commands = null;
    this.dataSource = null;
  }

  public String getDataSource() {
    return dataSource;
  }

  public void setDataSource(String dataSource) {
    this.dataSource = dataSource;
    if (dataSource == null) {
      commands = null;
    } else
      readCSV();
  }

  
  public String[] requestCommands() {
    return commands;
  }
  
  private void readCSV() {
    try {
      BufferedReader file = new BufferedReader(new FileReader(dataSource));
        
      String line = file.readLine();
      if (line != null) {
        commands = line.split(",");
        line = file.readLine();
      }
      file.close();
    } catch (IOException erro) {
      erro.printStackTrace();
    }
  }
  
}