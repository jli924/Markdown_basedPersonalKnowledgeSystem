package cs3500.pa01;

import cs3500.pa01.Model.FileFormatter;
import cs3500.pa01.Model.FileIo;
import cs3500.pa01.Model.MarkDownFileVisitor;
import cs3500.pa01.OrderingFlag.Created;
import cs3500.pa01.OrderingFlag.FileName;
import cs3500.pa01.OrderingFlag.Modified;
import cs3500.pa01.OrderingFlag.OrderingFlag;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * The Driver class that brings everything together that
 * is used by the main method
 */
public class Driver {
  // these first three are converted input from user
  Path notesroot;
  OrderingFlag orderingflag;
  Path output;
  // where MarkDownFileVisitor will store the md files
  ArrayList<File> files = new ArrayList<>();
  MarkDownFileVisitor mdfv = new MarkDownFileVisitor();
  FileIo fio;
  FileFormatter ff;

  // these were made for TESTING REASONS
  OrderingFlag fn;
  OrderingFlag mod;
  OrderingFlag cr;

  /**
   * Constructor to use in main
   */
  public Driver(String args0, String args1, String args2) {
    notesroot = Paths.get(args0);
    orderingflag = toOrderingFlag(args1);
    output = Paths.get(args2);
    mdfv = new MarkDownFileVisitor(files, orderingflag);
    fio = new FileIo();
    ff = new FileFormatter();
    fn = new FileName();
    mod = new Modified();
    cr = new Created();

  }

  /**
   * Constructor to initialize data
   */
  public Driver(Path nr, OrderingFlag of, Path o) {
    notesroot = nr;
    orderingflag = of;
    output = o;
    mdfv = new MarkDownFileVisitor(files, orderingflag);
    fio = new FileIo();
    ff = new FileFormatter();
    fn = new FileName();
    mod = new Modified();
    cr = new Created();
  }

  /**
   * Coverts a string to an ordering flag if the string is
   * a valid ordering flag, otherwise throws an IllegalArgumentException
   *
   * @param s a string representing one of three ordering flags
   * @return the ordering flag that corresponds with the string
   */
  public OrderingFlag toOrderingFlag(String s) {
    if (s.compareToIgnoreCase("filename") == 0) {
      return fn;
    } else if (s.compareToIgnoreCase("modified") == 0) {
      return mod;
    } else if (s.compareToIgnoreCase("created") == 0) {
      return cr;
    } else {
      throw new IllegalArgumentException("That's not a valid ordering flag...");
    }
  }

  /**
   * Walks the file tree
   *
   * @param dir a path reference to a directory
   */
  public void walkFileTree(Path dir) {
    try {
      Files.walkFileTree(dir, mdfv);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Using the MarkDownFileVisitor (mdfv) and FileIO (fio),
   * retrieves all the contents of the sorted file list as an array list
   * of contents (which is an array list of strings, for each file)
   *
   * @return an array list of array list of string (all the sorted files' contents)
   */
  public ArrayList<ArrayList<String>> listOfFileContents() throws FileNotFoundException {
    ArrayList<ArrayList<String>> allcontents = new ArrayList<>();
    for (File f : mdfv.getSortedList()) {
      allcontents.add(fio.fileContents(f));
    }
    return allcontents;
  }

  /**
   * Using the FileFormatter, formats all the files' contents properly
   * (as described in the assignment). Mutates the array list of array list
   * of files' contents.
   */
  public ArrayList<ArrayList<String>> formatFilesContents(
      ArrayList<ArrayList<String>> filecontents) {
    for (ArrayList<String> contents : filecontents) {
      ff.formatContents(contents);
    }
    return filecontents;
  }

  /**
   * Turns the array list of array list of file contents (or strings) into
   * a giant formatted string with the contents of all the sorted and formatted
   * files
   *
   * @param filecontents an array list of an array list of all the contents of the files
   *
   * @return a giant string with the contents from all .md files
   */
  public String listToFormattedString(ArrayList<ArrayList<String>> filecontents) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < filecontents.size(); i++) {
      for (int j = 0; j < filecontents.get(i).size(); j++) {
        if (i == 0 && j == 0) {
          sb.append(filecontents.get(i).get(j));
        } else if (filecontents.get(i).get(j).startsWith("#")) {
          sb.append("\n").append(filecontents.get(i).get(j));
        } else if (!filecontents.get(i).get(j).equals("")) {
          sb.append(filecontents.get(i).get(j)).append("\n");
        }
      }
    }
    return sb.toString();
  }

  /**
   * Using FileIO, writes the giant string of contents from
   * listToString onto the output file, or final study guide
   *
   * @param file a reference to the final study guide file
   */
  public void createFinalStudyGuide(Path file) throws FileNotFoundException {
    ArrayList<ArrayList<String>> arrayLists = listOfFileContents();
    String allformattedcontent = listToFormattedString(formatFilesContents(arrayLists));
    fio.writeToFile(file.toFile(), allformattedcontent);
  }
}
