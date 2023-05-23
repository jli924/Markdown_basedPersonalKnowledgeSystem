package cs3500.pa01.OrderingFlag;

import java.io.File;
import java.util.ArrayList;

/**
 * Represents an ordering flag
 */
public interface OrderingFlag {
  /**
   * Sorts an ArrayList of files by one of three ordering flags
   *
   * @param files An ArrayList of markdown files
   * @return the sorted ArrayList of markdown files
   */
  ArrayList<File> sort(ArrayList<File> files);
}
