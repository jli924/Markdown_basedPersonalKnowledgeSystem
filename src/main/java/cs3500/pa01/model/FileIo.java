package cs3500.pa01.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File input output: to manage reading from and
 * writing to a file
 */
public class FileIo {
  /**
   * Constructor
   */

  public FileIo() {
  }

  /**
   * Reads the contents from a file to an ArrayList of Strings
   *
   * @param file A file object which corresponds to a path in
   *             the file system and some information at that path
   * @return the contents of the file as an arraylist of strings
   */

  public ArrayList<String> fileContents(File file) throws FileNotFoundException {
    Scanner sc;
    sc = new Scanner(new FileInputStream(file));

    ArrayList<String> content = new ArrayList<>();
    StringBuilder line = new StringBuilder();
    while (sc.hasNextLine()) {
      line.append(sc.nextLine()).append("\n");
      content.add(line.toString());
      line = new StringBuilder();
    }
    return content;
  }

  /**
   * Writes the given String to the given filepath.
   *
   * @param file     where to write the contents
   * @param contents contents to write to the file
   */

  public void writeToFile(File file, String contents) {
    Path path = Path.of(file.toPath().toUri());
    byte[] data = contents.getBytes();
    try {
      Files.write(path, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
