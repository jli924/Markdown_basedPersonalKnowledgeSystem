package cs3500.pa01.OrderingFlag;

import cs3500.pa01.OrderingFlag.OrderingFlag;
import java.io.File;
import java.util.ArrayList;

/**
 * A flag to indicate the study guide should be organized
 * alphabetically by file name
 */
public class FileName implements OrderingFlag {

  /**
   * Constructor
   */

  public FileName() {
  }
  /**
   * Sorts an ArrayList of files alphabetically using the files' names
   *
   * @param files An ArrayList of markdown files
   * @return the sorted ArrayList of markdown files
   */

  @Override
  public ArrayList<File> sort(ArrayList<File> files) {
    for (int i = 0; i < files.size(); i++) {
      String filename = files.get(i).toPath().getFileName().toString();
      File cur = files.get(i);
      for (int j = i + 1; j < files.size(); j++) {
        String nextfilename = files.get(j).toPath().getFileName().toString();
        if (filename.compareTo(nextfilename) > 0) {
          files.set(i, files.get(j));
          files.set(j, cur);
        }
      }
    }
    return files;
  }
}
