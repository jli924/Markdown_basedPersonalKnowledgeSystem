package cs3500.pa01.model;

import static java.nio.file.FileVisitResult.CONTINUE;

import cs3500.pa01.orderingflag.OrderingFlag;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

/**
 * A FileVisitor that only visits .md files
 */
public class MarkDownFileVisitor implements FileVisitor<Path> {

  public ArrayList<File> files;
  OrderingFlag orderingflag;

  public MarkDownFileVisitor() {

  }

  /**
   * Constructor
   */
  public MarkDownFileVisitor(ArrayList<File> f, OrderingFlag of) {
    files = f;
    orderingflag = of;
  }

  /**
   * Sorts an array list of files by one of three OrderingFlags
   *
   * @return the sorted files as an array list
   */
  public ArrayList<File> getSortedList() {
    return orderingflag.sort(files);
  }

  /**
   * Invoked for a directory before entries in the directory are visited.
   * (from java docs)
   *
   * @param dir a reference to the directory
   * @param attrs the attributes of the directory
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
    return CONTINUE;
  }

  /**
   * Invoked for a .md file in a directory.
   * (partly from java docs)
   *
   * @param file a reference to the file
   * @param attrs the attributes of the file
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
    String filename = file.getFileName().toString();

    if (filename.endsWith(".md")) {
      files.add(file.toFile());
    }

    return CONTINUE;
  }

  /**
   * Invoked for a file that could not be visited.
   * (from java docs)
   *
   * @param file a reference to the file
   * @param exc the I/O exception that prevented the file from being visited
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
    throw exc;
  }

  /**
   * Invoked for a directory after entries in the directory,
   * and all of their descendants, have been visited.
   * (from java docs)
   *
   * @param dir a reference to the directory
   * @param exc the I/O exception that caused the
   *            iteration of the directory to complete prematurely
   * @return a FileVisitResult
   */
  @Override
  public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
    return CONTINUE;
  }
}
