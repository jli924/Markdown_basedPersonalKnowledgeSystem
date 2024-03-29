package cs3500.pa01.orderingflag;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

/**
 * A flag to indicate the study guide should be organized
 * by a file's last modified date
 */
public class Modified implements OrderingFlag {
  /**
   * Constructor
   */

  public Modified() {

  }
  /**
   * Sorts an ArrayList of files by the files' last modified date
   *
   * @param files An ArrayList of markdown files
   * @return the sorted ArrayList of markdown files
   */

  public ArrayList<File> sort(ArrayList<File> files) {
    for (int i = 0; i < files.size(); i++) {
      BasicFileAttributes bfa;
      try {
        bfa = Files.readAttributes(files.get(i).toPath(), BasicFileAttributes.class);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      FileTime modified = bfa.lastModifiedTime();
      File cur = files.get(i);
      for (int j = i + 1; j < files.size(); j++) {
        BasicFileAttributes bfa2;
        try {
          bfa2 = Files.readAttributes(files.get(j).toPath(), BasicFileAttributes.class);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        FileTime nextmodified = bfa2.lastModifiedTime();
        if (modified.compareTo(nextmodified) < 0) {
          files.set(i, files.get(j));
          files.set(j, cur);
        }
      }
    }
    return files;
  }
}
