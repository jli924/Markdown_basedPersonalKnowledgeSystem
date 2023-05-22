package cs3500.pa01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Handles functionality
 */
public class Model {
  FileIo fileIo = new FileIo();
  FileFormatter fileFormatter = new FileFormatter();
  QuestionSet questionSet = new QuestionSet();

  public void setQuestionsToStudy(int toStudy) {
    questionSet.questionsToStudy = toStudy;
  }

  /**
   * Read a .sr file (using a method written in PA01)
   *
   * @param file the file to read
   *
   * @return the string of contents read
   */
  public ArrayList<String> readSrFile(File file) {
    try {
      return fileIo.fileContents(file);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Formats file contents, allocating data to the right places:
   * Questions and user data
   *
   * @param file the file to read & format
   */
  public void organizeData(File file) {
    ArrayList<String> contents = readSrFile(file);
    fileFormatter.formatContents(contents);
  }
}
