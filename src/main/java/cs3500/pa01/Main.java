package cs3500.pa01;

import cs3500.pa01.controller.Controller;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This is the main driver of this project.
 */
public class Main {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) throws FileNotFoundException {
    Controller controller = new Controller();
    if (args.length == 0) {
      controller.run();
    } else if (args.length == 3) {
      Path i;
      Path o;
      // to ensure that args 0 and 2 are valid paths
      try {
        i = Paths.get(args[0]);
      } catch (InvalidPathException exc) {
        throw new IllegalArgumentException("That's not a path...");
      }
      try {
        o = Paths.get(args[2]);
      } catch (InvalidPathException exc) {
        throw new IllegalArgumentException("That's not a path...");
      }
      Driver driver = new Driver(args[0], args[1], args[2]);
      driver.walkFileTree(i);
      driver.createFinalStudyGuide(o);
    } else {
      throw new IllegalArgumentException("Invalid number of arguments.");
    }
  }
}